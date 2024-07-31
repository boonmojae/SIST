package kr.spring.member.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import kr.spring.member.email.Email;
import kr.spring.member.email.EmailSender;
import kr.spring.member.service.MemberService;
import kr.spring.member.vo.MemberVO;
import kr.spring.util.StringUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MemberAjaxController {
	@Autowired
	private EmailSender emailSender;
	
	@Autowired
	private Email email;
	
	@Autowired
	private MemberService memberService;

	@GetMapping("/member/confirmId")
	@ResponseBody						//전달이 안되니까 에러나게 만듦
	public Map<String,String> process(@RequestParam String id){//json문자열을 만들면 map,list,자바빈으로 반환해야됨
		
		log.debug("<<아이디 중복 체크>> : " + id);
		//Map객체 생성
		Map<String,String> mapAjax = new HashMap<String,String>();
		
		MemberVO member = memberService.selectCheckMember(id);
		if(member!=null) {
			//아이디 중복
			mapAjax.put("result", "idDuplicated");
		}else {
			//유효성 체크보다 먼저 일어나서 여기에도 패턴체크 추가/패턴 체크는 자바스크립트에서 해도 됨
			if(!Pattern.matches("^[A-Za-z0-9]{4,12}$", id)) {
				//패턴 불일치
				mapAjax.put("result", "notMatchPattern");
			}else {
				//패턴 일치하면서 아이디 미중복
				mapAjax.put("result", "idNotFound");
			}
		}
		
		return mapAjax;
	}
	
	// form_data에서 받아온 데이터는 Spring MVC 를 이용해서 자동으로 memberVO에 데이터 매핑을 시켜줌
	// 인터셉터는 ajax할 때 쓰면 안 됨
	@PostMapping("/member/updateMyPhoto")
	@ResponseBody
	 public Map<String, String> processProfile(MemberVO memberVO, HttpSession session) {
        Map<String, String> mapAjax = new HashMap<>();
        MemberVO user = (MemberVO)session.getAttribute("user");
	    if(user==null) {
	    	mapAjax.put("result", "logout");
	    } else {
	    	memberVO.setMem_num(user.getMem_num());
	    	memberService.updateProfile(memberVO);
	    	mapAjax.put("result", "success");
	    }

        return mapAjax;
    }
	//비밀번호찾기
	@PostMapping("/member/getPasswordInfo")
	@ResponseBody
	public Map<String, String> sendEmailAction(MemberVO memberVO){//예외발생예방 throws Exception 넣었는데 밑에 try catch있어서 제거
		log.debug("<<비밀번호 찾기>> : " + memberVO);
		
		Map<String, String> mapJson = new HashMap<String, String>();
		MemberVO member = memberService.selectCheckMember(memberVO.getId());
		
		if(member!=null && member.getAuth()>1 && member.getEmail().equals(memberVO.getEmail())) {
			//오류를 대비해서 원래 비밀번호 저장
			String origin_passwd = member.getPasswd();
			//기존 비밀번호를 임시비밀번호로 변경
			String passwd = StringUtil.randomPassword(10);
			member.setPasswd(passwd);
			//변경된 임시 비밀번호를 DB에 저장
			memberService.updateRandomPassword(member);
			
			email.setContent("임시 비밀번호는 " + passwd + " 입니다.");
			email.setReceiver(member.getEmail());
			email.setSubject(member.getId()+"님 비밀번호 찾기 메일입니다.");
			
			try {
				emailSender.sendEmail(email);
				//정상일때
				mapJson.put("result", "success");
			}catch(Exception e) {
				log.error("<<비밀번호 찾기>> : " + e.toString());//어떤 예외인지 알려줌
				//오류 발생시 비밀번호 원상 복구
				member.setPasswd(origin_passwd);
				memberService.updateRandomPassword(member);
				mapJson.put("result", "failure");
			}
			
		}else if(member!=null && member.getAuth()==1) {
			//정지회원
			mapJson.put("result", "noAuthority");
		}else {
			mapJson.put("result", "invalidInfo");
		}
		
		return mapJson;
	}
	
}
