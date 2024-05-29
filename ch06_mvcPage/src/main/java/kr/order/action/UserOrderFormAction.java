package kr.order.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.cart.dao.CartDAO;
import kr.cart.vo.CartVO;
import kr.controller.Action;
import kr.item.dao.ItemDAO;

public class UserOrderFormAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		
		if(user_num==null) {//로그인이 되지 않은 경우
			return "redirect:/member/loginForm.do";
		}
		//로그인 된 경우
		//POST 방식의 접근만 허용/카트에서 넘어온게 아니라 바로 주소호출하는게 get방식
		if(request.getMethod().toUpperCase().equals("GET")) {
			return "/WEB-INF/views/common/notice.jsp";
		}
		
		CartDAO dao = CartDAO.getInstance();
		int all_total = dao.getTotalByMem_num(user_num);//토탈 구매금액 구하는거
		if(all_total<=0) {//이미 구매했는데 백버튼으로 되돌아와서 구매하는걸 방지 조건체크
			request.setAttribute("notice_msg", "정상적인 주문이 아니거나 상품의 수량이 부족합니다");
			request.setAttribute("notice_url", request.getContextPath()+"/item/itemList.do");
			return "/WEB-INF/views/common/alert_view.jsp";
		}
		
		//데이터 베이스에 담겨있는 정보를 뽑아옴
		//장바구니에 담겨있는 상품 정보 호출
		List<CartVO> cartList = dao.getListCart(user_num);
		ItemDAO itemDAO = ItemDAO.getInstance();
		for(CartVO cart : cartList) {
			
		}
		return null;
	}

}