package kr.news.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.news.dao.NewsDAO;
import kr.news.vo.NewsVO;
import kr.util.FileUtil;

public class ModifyAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//전송된 데이터 인코딩 타입 지정
		request.setCharacterEncoding("utf-8");
		
		int num = Integer.parseInt(request.getParameter("num"));
		String passwd = request.getParameter("passwd");
		
		NewsDAO dao = NewsDAO.getInstance();
		//비밀번호 인증을 위해 한 건의 레코드를 자바빈(VO)에 담아서 반환
		NewsVO db_news = dao.getNews(num);//num을 넣어서 비밀번호가 담긴 데이터 반환 ch04는 newsVO.getNum()방식으로 했는데 뭔차이
		boolean check = false;
		if(db_news!=null) {
			//비밀번호 일치여부 체크 확인
			check = db_news.isCheckedPassword(passwd);
		}
		if(check) {
			NewsVO vo = new NewsVO();
			vo.setNum(num);
			vo.setTitle(request.getParameter("title"));
			vo.setWriter(request.getParameter("writer"));
			vo.setEmail(request.getParameter("email"));
			vo.setArticle(request.getParameter("article"));
			vo.setFilename(FileUtil.createFile(request, "filename"));
			dao.updateNews(vo);
			
			//여기 안에 파일이 있다면 새롭게 없로드,파일이 없다면 교체
			if(vo.getFilename()!=null && !vo.getFilename().isEmpty()) {
				//새 파일로 교체할 때 원래 파일 제거
				FileUtil.removeFile(request, db_news.getFilename());
			}
			//상세페이지로 이동하기 위해 글번호 저장
			request.setAttribute("num", vo.getNum());
		}
		//UI처리를 위해서 체크 저장
		request.setAttribute("check", check);//check=false 비밀번호 틀렸을때 다시 폼으로 되돌아가는 기능을 사용하려고 저장
		//JSP 경로 반환
		return "/WEB-INF/views/modify.jsp";
	}

}
