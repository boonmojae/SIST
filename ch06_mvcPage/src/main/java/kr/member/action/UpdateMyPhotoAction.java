package kr.member.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.controller.Action;
import kr.member.dao.MemberDAO;
import kr.util.FileUtil;

public class UpdateMyPhotoAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//로그인(mem_num있을때) 되어있어야 가능
		Map<String,String> mapAjax = new HashMap<String,String>();
		
		//로그인 됐는지 알아내야됨
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num==null) {//로그인이 되지 않은 경우
			mapAjax.put("result", "logout");
		}else {//로그인 된 경우
			//로그인이 된 경우에는 정보 처리
			//전송된 데이터 인코딩 타입 지정
			request.setCharacterEncoding("utf-8");
			//파일 업로드 처리								 파라미터명
			String photo = FileUtil.createFile(request, "photo");//photo를 구함
			
			//로그인 되어있으면 user_num사용할 수 있음/사진이랑 같이 보냄?
			MemberDAO dao = MemberDAO.getInstance();
			//프로필 사진 수정
			dao.updateMyPhoto(photo, user_num);
			
			//새롭게 업로드하면 이전 파일 삭제/이전파일은 loginAction-39에서 저장
			//이전 파일 삭제 처리
			String user_photo = (String)session.getAttribute("user_photo");//session에서 꺼냄
			FileUtil.removeFile(request, user_photo);
			
			//현재 파일로 세션 정보 갱신
			session.setAttribute("user_photo", photo);
			
			mapAjax.put("result", "success");
		}
		
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);//json문자열로 변환
		request.setAttribute("ajaxData", ajaxData);
		
		return "/WEB-INF/views/common/ajax_view.jsp";//모든 json문자열 처리할때 ajax_view를 사용
	}

}
