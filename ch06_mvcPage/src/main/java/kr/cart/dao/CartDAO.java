package kr.cart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.cart.vo.CartVO;
import kr.item.vo.ItemVO;
import kr.util.DBUtil;

public class CartDAO {
	//싱글턴 패턴
	private static CartDAO instance = new CartDAO();
	
	public static CartDAO getInstance() {
		return instance;
	}
	private CartDAO() {}
	
	//장바구니 등록
	public void insertCart(CartVO cart)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "INSERT INTO zcart(cart_num,item_num,order_quantity,mem_num) VALUES(zcart_seq.nextval,?,?,?)";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(1, cart.getItem_num());
			pstmt.setInt(2, cart.getOrder_quantity());
			pstmt.setInt(3, cart.getMem_num());
			//SQL문 실행
			pstmt.executeUpdate();//ajax통신해서 전달받은 데이터 insert할거임
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	//회원번호별 총 구매액
	//장바구니 목록
	public List<CartVO> getListCart(int mem_num)throws Exception{
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<CartVO> list = null;
		String sql = null;
		try {
			//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "SELECT * FROM zcart c JOIN zitem i USING(item_num)"
					+ "WHERE mem_num=? ORDER BY c.reg_date DESC";//reg_date가 둘 다 있어서 알리아스로 가져올 정보 명시
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(1, mem_num);
			//SQL문 실행
			rs = pstmt.executeQuery();
			list = new ArrayList<CartVO>();
			while(rs.next()) {
				CartVO cart = new CartVO();
				cart.setCart_num(rs.getInt("cart_num"));
				cart.setItem_num(rs.getInt("item_num"));
				cart.setOrder_quantity(rs.getInt("order_quantity"));
				cart.setMem_num(rs.getInt("mem_num"));
				
				//상품 정보를 담기위해 ItemVO 객체 생성
				ItemVO item = new ItemVO();
				item.setName(rs.getString("name"));
				item.setPrice(rs.getInt("price"));
				item.setPhoto1(rs.getString("photo1"));
				item.setQuantity(rs.getInt("quantity"));
				item.setStatus(rs.getInt("status"));//이걸 호출하려면 cart에 담아서 호출하고 싶음/cartVO에 itemVO있음
				
				//ItemVO를 CartVO에 저장
				cart.setItemVO(item);
				
				//동일 상품(item_num이 같은 상품)의 총구매 금액 구하기
				cart.setSub_total(cart.getOrder_quantity()*item.getPrice());//가격이 변동될수도 있어 데이터 베이스에 저장안하고 VO에만 생성
				
				list.add(cart);
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		
		return list;
	}
	//장바구니 상세
	public CartVO getCart(CartVO cart)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CartVO cartSaved = null;
		String sql = null;
		try {
			//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "SELECT * FROM zcart WHERE item_num=? AND mem_num=?";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(1, cart.getItem_num());
			pstmt.setInt(2, cart.getMem_num());
			//SQL문 실행
			rs = pstmt.executeQuery();
			if(rs.next()) {//여러개가 아니라 하나의 데이터로 관리
				cartSaved = new CartVO();
				cartSaved.setCart_num(rs.getInt("cart_num"));
				cartSaved.setItem_num(rs.getInt("item_num"));
				cartSaved.setOrder_quantity(rs.getInt("order_quantity"));
				
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		
		return cartSaved;
	}
	
	//장바구니 수정 (개별 상품 수량 수정)
	public void updateCartByItem_num(CartVO cart)throws Exception{
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "UPDATE zcart SET order_quantity=? WHERE item_num=? AND mem_num=?";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(1, cart.getOrder_quantity());
			pstmt.setInt(2, cart.getItem_num());
			pstmt.setInt(3, cart.getMem_num());
			//SQL문 실행
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	//장바구니 수정 (상품번호와 회원번호별 수정)
	//장바구니 삭제
	
}
