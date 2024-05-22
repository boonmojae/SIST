package kr.news.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.news.dao.NewsDAO;
import kr.news.vo.NewsVO;
import kr.util.PagingUtil;

public class ListAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String pageNum = request.getParameter("pageNum");
		if(pageNum==null) pageNum = "1";

		NewsDAO dao = NewsDAO.getInstance();
		int count = dao.getCount();

		PagingUtil page = new PagingUtil(Integer.parseInt(pageNum),count,20,10,"list.do");
		List<NewsVO> list = null;
		if(count > 0) {
			list = dao.getList(page.getStartRow(), page.getEndRow());
		}
		//request에 데이터 저장		속성명
		request.setAttribute("count", count);//el은 속성명을 찾음
		request.setAttribute("list", list);
		request.setAttribute("page", page.getPage());

		//JSP 경로 반환
		return "/WEB-INF/views/list.jsp";
	}

}
