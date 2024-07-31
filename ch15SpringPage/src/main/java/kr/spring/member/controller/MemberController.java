package kr.spring.member.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import kr.spring.member.service.MemberService;
import kr.spring.member.vo.MemberVO;
import kr.spring.util.AuthCheckException;
import kr.spring.util.CaptchaUtil;
import kr.spring.util.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.RequestBody;



@Slf4j
@Controller
public class MemberController {
	@Autowired
	private MemberService memberService;

	/*===========================
	 * 회원가입
    ============================*/
	//자바빈(VO) 초기화
	@ModelAttribute
	public MemberVO initCommand() {
		return new MemberVO();
	}

	//회원가입 폼 호출
	@GetMapping("/member/registerUser")
	public String form() {
		return "memberRegister"; //Tiles 설정명//문장으로 명시
	}

	//전송된 데이터 처리(회원가입)
	@PostMapping("/member/registerUser")
	public String submit(@Valid MemberVO memberVO, BindingResult result, Model model, HttpServletRequest request) {//model 데이터 전송,request 경로
		log.debug("<<회원가입>> : " + memberVO);

		//유효성 체크 결과 오류가 있으면 폼 호출
		if (result.hasErrors()) {
			return form();
		}
		//회원 가입
		memberService.insertMember(memberVO);


		//UI 메시지 처리
		model.addAttribute("accessTitle", "회원 가입");
		model.addAttribute("accessMsg", "회원 가입이 완료되었습니다.");
		model.addAttribute("accessBtn", "홈으로");
		model.addAttribute("accessUrl", request.getContextPath() + "/main/main");

		return "common/resultView";//.do 확장자 넣어도 되고 빼도 되고
	}
	/*===========================
	 * 회원로그인
    ============================*/
	//로그인 폼 호출
	@GetMapping("/member/login")
	public String formLogin() {
		return "memberLogin";//Tiles 설정명->member.xml
	}
	//로그인 폼에서 전송된 데이터 처리
	@PostMapping("/member/login")
	public String submit(@Valid MemberVO memberVO,BindingResult result,HttpSession session,HttpServletResponse response) {//session에 저장한 user이용해서 로그인 체크 하니까 session을 인자로? 

		log.debug("<<회원로그인>> : " + memberVO);

		//유효성 체크 결과 오류가 있으면 폼 호출
		//id와 passwd 필드만 체크
		if(result.hasFieldErrors("id") || result.hasFieldErrors("passwd")) {
			return formLogin();
		}

		//로그인 체크(id,비밀번호 일치 여부 체크)
		MemberVO member = null;
		//예외를 던질꺼면 사용자정의예외 클래스 만들어야됨
		try {
			member = memberService.selectCheckMember(memberVO.getId());
			boolean check = false;
			if(member!=null) {
				//비밀번호 일치 여부 체크
				check = member.ischeckedPassword(memberVO.getPasswd());
			}
			if(check) {//인증 성공
				//======= 자동로그인 체크 시작 ======//
				boolean autoLogin = memberVO.getAuto()!=null && memberVO.getAuto().equals("on");
				if(autoLogin) {
					//자동로그인을 체크한 경우
					String au_id = member.getAu_id();
					if(au_id==null) {
						//자동로그인 체크 식별값 생성
						au_id = UUID.randomUUID().toString();
						log.debug("<<au_id>> : " + au_id);
						member.setAu_id(au_id);
						memberService.updateAu_id(member.getAu_id(),member.getMem_num());
					}
					Cookie auto_cookie = new Cookie("au-log",au_id);
					auto_cookie.setMaxAge(60*60*24*7);
					auto_cookie.setPath("/");
					
					response.addCookie(auto_cookie);
				}
				//======= 자동로그인 체크 끝 ======//

				//인증 성공, 로그인 처리
				session.setAttribute("user", member);

				log.debug("<<인증 성공>>");
				log.debug("<<id>> : " + member.getId());	
				log.debug("<<auth>> : " + member.getAuth());	
				log.debug("<<au_id>> : " + member.getAu_id());	

				if(member.getAuth() == 9) {//관리자
					return "redirect:/main/admin";
				}else {
					return "redirect:/main/main";
				}
			}
			//인증 실패(check=false)
			throw new AuthCheckException();
		}catch(AuthCheckException e) {
			//인증 실패로 로그인 폼 호출
			if(member!=null && member.getAuth()==1) {//정지회원 메시지 표시
				//reject=필드가 없음,rejectValue는 필드 있음
				result.reject("noAuthority");
			}else {
				result.reject("invalidIdOrPassword");
			}
			log.debug("<<인증 실패>>");
			return formLogin();
		}
	}

	/*===========================
	 * 로그아웃
    ============================*/
	@GetMapping("/member/logout")
	public String processLogout(HttpSession session,HttpServletResponse response) {//session에 있는 속성 지워야됨 ->HttpSession받음
		//로그 아웃
		session.invalidate();
		//자동로그인 하려면 intercepter라는걸 배워야됨
		//======= 자동로그인 시작 ======//
		//클라이언트 쿠키 처리
		Cookie auto_cookie = new Cookie("au-log","");
		auto_cookie.setMaxAge(0);//쿠키 삭제
		auto_cookie.setPath("/");
		
		response.addCookie(auto_cookie);
		//======= 자동로그인 끝 ======//

		return "redirect:/main/main";
	}
	/*===========================
	 * MY페이지
    ============================*/
	@GetMapping("/member/myPage")
	public String process(HttpSession session,Model model) {//session에 자바빈 저장한거라 정보 빼냄,뷰가 이미 존재해서 데이터만 반환하기 위해 model사용
		MemberVO user = (MemberVO)session.getAttribute("user");//user에 담겨있는 mem_num으로 회원정보 읽음(일부정보만 있어서 mem_num으로 전체정보 읽음,처음부터 user에 모든 자바빈 정보 넣어서 사용해도됨)

		//회원정보
		MemberVO member = memberService.selectMember(user.getMem_num());
		log.debug("<<My페이지>> : " + member);

		model.addAttribute("member",member);

		return "myPage"; //Tiles 설정명
	}
	
	/*===========================
	 *  회원정보 수정
    ============================*/
	
	@GetMapping("/member/update")
	public String formUpdate(HttpSession session, Model model) {
		MemberVO user = (MemberVO)session.getAttribute("user");
		MemberVO memberVO = memberService.selectMember(user.getMem_num());
		model.addAttribute("memberVO", memberVO);
		return "memberModify";
	}
	
	@PostMapping("/member/update")
	public String postMethodName(@Valid MemberVO memberVO, BindingResult result,  HttpSession session) {
		log.debug("<<회원 정보 수정>> : " + memberVO);
		
		//유효성 체크 결과 오류가 있으면 폼 호출
		if (result.hasErrors()) {
			return "memberModify";
		}
		

		MemberVO user = (MemberVO)session.getAttribute("user");
		memberVO.setMem_num(user.getMem_num());
		
		// 회원정보 수정
		memberService.updateMember(memberVO);
		
		user.setNick_name(memberVO.getNick_name());
		user.setEmail(memberVO.getEmail());

		return "redirect:/member/myPage";
	}
	
	/*===========================
	 *  프로필 사진 출력
    ============================*/
	
	// 프로필 사진 출력 (로그인 전용)
	@GetMapping("/member/photoView")
	public String getProfile(HttpSession session, HttpServletRequest request, Model model) {
		MemberVO user = (MemberVO)session.getAttribute("user");
		log.debug("<<프로필 사진 출력>> : " + user);
		if(user==null) {
			getBasicProfileImage(request, model);
		}  else { // 로그인이 된 경우
			MemberVO memberVO = memberService.selectMember(user.getMem_num());
			viewProfile(memberVO, request, model);
			
		}
		return "imageView";
	}
	
	//프로필 사진 출력(회원번호 지정)
	@GetMapping("/member/viewProfile")
	public String getProfileByMem_num(long mem_num,HttpServletRequest request,Model model) {
		
		MemberVO memberVO = memberService.selectMember(mem_num);
		viewProfile(memberVO,request,model);
		
		return "imageView";//stream처리해서 전송
	}
	
	
	// 프로필 사진처리를 위한 공통 코드
	public void viewProfile(MemberVO memberVO, HttpServletRequest request, Model model) {
		if(memberVO==null || memberVO.getPhoto_name()==null) {
			// DB에 저장된 프로필 이미지가 없기 때문에 기본 이미지 호출
			getBasicProfileImage(request, model);
		} else {
			// 업로드한 프로필 이미지 읽기
			model.addAttribute("imageFile", memberVO.getPhoto());
			model.addAttribute("filename", memberVO.getPhoto_name());
		}
	}
	
	// 기본 이미지 읽기
	public void  getBasicProfileImage(HttpServletRequest request, Model model) {
																									// 절대 경로
		byte[] readbyte = FileUtil.getBytes(request.getServletContext().getRealPath("/image_bundle/face.png"));
		model.addAttribute("imageFile", readbyte);
		model.addAttribute("filename", "face.png");
	}
	
	/*===========================
	 *  비밀번호 변경
    ============================*/
	
	// 비밀번호 변경 폼 호출
	@GetMapping("/member/changePassword")
	public String formChangePasswd() {
		return "memberChangePassword";
	}
	
	// 비밀번호 변경 폼에서 전송된 데이터 처리
	@PostMapping("/member/changePassword")
	public String submitChangePassword(@Valid MemberVO memberVO, BindingResult result, HttpSession session, Model model, HttpServletRequest request) {
		log.debug("<<비밀번호 변경 처리>> : " +  memberVO);
		// 유효성 체크 결과 오류가 있으면 폼 호출
		if(result.hasFieldErrors("now_passwd") || result.hasFieldErrors("passwd") || result.hasFieldErrors("captcha_chars")) {
			return formChangePasswd();
		}
		
		// ------- 캡챠 문자 체크 시작 --------- //
		String code = "1"; // 키 발급 0, 캡챠 이미지 비교시 1로 세팅

		// 캡챠 키 발급시 받아서 세션에 저장해둔 키값
		String key = (String)session.getAttribute("captcha_key");
		// 사용자가 입력한 캡챠 이미지 글자값
		String value = memberVO.getCaptcha_chars();
		String key_apiURL = "https://openapi.naver.com/v1/captcha/nkey?code=" + code + "&key=" + key + "&value=" + value; 
		
		Map<String,String> requestHeaders = new HashMap<String,String>();
		requestHeaders.put("X-Naver-Client-Id",  "pjCvRXCBMUnA7oYsWNKj");
		requestHeaders.put("X-Naver-Client-Secret", "DYu3HKzI4x");
		String responseBody = CaptchaUtil.get(key_apiURL, requestHeaders);
		
		log.debug("<<캡챠 결과>> : " + responseBody);
		JSONObject jObject = new JSONObject(responseBody);
		
		boolean captcha_result = jObject.getBoolean("result");
		if(!captcha_result) {
			result.rejectValue("captcha_chars", "invalidCaptcha");
			return formChangePasswd();
		}
		// ------- 캡챠 문자 체크 끝 --------- //
		
		MemberVO user = (MemberVO)session.getAttribute("user");
		memberVO.setMem_num(user.getMem_num());
		
		MemberVO db_member = memberService.selectMember(memberVO.getMem_num());
		
		// 폼에서 전송한 현재 비밀번호와 DB에서 읽어온 비밀번호가 일치하는지 체크
		if(!db_member.getPasswd().equals(memberVO.getNow_passwd())) {
			result.rejectValue("now_passwd", "invalidPassword");
			return formChangePasswd();
		}
		
		//비밀번호 수정
		memberService.updatePassword(memberVO);
		
		// 자동 로그인 해제 설정
		memberService.deleteAu_id(memberVO.getMem_num());
		
		// View에 표시할 메시지
		model.addAttribute("message", "비밀번호 변경 완료(재접속시 설정되어 있는 자동 로그인 기능 해제");
		model.addAttribute("url",  request.getContextPath() + "/member/myPage");
		return "common/resultAlert";
		
	}
	/*====================
	 * 	네이버 캡챠 API 사용\
	 =====================*/

	//캡챠 이미지 호출

	@GetMapping("/member/getCaptcha")
	public String getCaptcha(Model model, HttpSession session) {

		String code = "0";//키 발급시 0, 캡챠 이미지 비교시 1로 세팅
		String key_apiURL = "https://openapi.naver.com/v1/captcha/nkey?code=" + code;

		Map<String,String> requestHeaders = new HashMap<String,String>();
		requestHeaders.put("X-Naver-Client-Id",  "pjCvRXCBMUnA7oYsWNKj");
		requestHeaders.put("X-Naver-Client-Secret", "DYu3HKzI4x");

		String responseBody = CaptchaUtil.get(key_apiURL, requestHeaders);

		log.debug("<<responseBody>> : " + responseBody);

		JSONObject jObject = new JSONObject(responseBody);
		
		try {
			//https://openapi.naver.com/v1/captcha/nkey 호출로 받은 키값(을 "key"가 읽어옴
			String key = jObject.getString("key");
			session.setAttribute("captcha_key", key);
			
			String apiURL = "https://openapi.naver.com/v1/captcha/ncaptcha.bin?key=" + key;

			byte[] response_byte = CaptchaUtil.getCaptchaImage(apiURL, requestHeaders);
			
			model.addAttribute("imageFile", response_byte);
			model.addAttribute("filename", "captcha.jpg");

		}catch(Exception e) {
			log.error(e.toString());
		}
		return "imageView";
	}
	/*===========================
	 * 임시비밀번호 발송 이메일 처리
    ============================*/
	@GetMapping("/member/sendPassword")
	public String sendPasswordForm() {
		
		return "memberFindPassword";
	}
}
	

