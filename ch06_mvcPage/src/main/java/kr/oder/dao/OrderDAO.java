package kr.oder.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.oder.vo.OrderVO;
import kr.util.DBUtil;

public class OrderDAO {
	//싱글턴 패턴
	private static OrderDAO instance = new OrderDAO();
	
	public static OrderDAO getInstance() {
		return instance;
	}
	private OrderDAO() {}
	
	//주문 등록
	//관리자 - 전체 주문 개수/검색 주문 개수
	//관리자 - 전체 주문 목록/검색 주문 목록
	//사용자 - 전체 주문 개수/검색 주문 개수
	public int getOrderCountByMem_num(String keyfield,String keyword,int mem_num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		String sub_sql = "";
		int count = 0;
		try {
			//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			if(keyword != null && !"".equals(keyword)) {
				//검색글 개수
				if(keyfield.equals("1")) sub_sql += "AND item_name LIKE '%' || ? || '%'";//상품명으로 검색
				else if(keyfield.equals("2")) sub_sql += "AND order_num=?";//주문 번호
			}
			//SQL문 작성
			sql = "SELECT COUNT(*) FROM zorder JOIN (SELECT order_num,"
					+ "LISTAGG(item_name,',') WITHIN GROUP (ORDER BY item_name) "
					+ "item_name FROM zorder_detail GROUP BY order_num) "
					+ "USING (order_num) WHERE mem_num=? " + sub_sql;
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(1, mem_num);//like검색
			if(keyword != null && !"".equals(keyword)) {//equals로
				pstmt.setString(2, keyword);
			}
			//SQL문 실행
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, null, null);
		}
		
		
		return count;
	}
	//사용자 - 전체 주문 목록/검색 주문 목록
	public List<OrderVO> getListOrderByMem_num(int start,int end,String keyfield,String keyword,int mem_num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<OrderVO> list = null;
		String sql = null;
		String sub_sql = "";
		int cnt = 0;
		try {
			//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			if(keyword != null && !"".equals(keyword)) {
				//검색글 목록
				if(keyfield.equals("1")) sub_sql += "AND item_name LIKE '%' || ? || '%'";//상품명으로 검색
				else if(keyfield.equals("2")) sub_sql += "AND order_num=?";//주문 번호
			}
			//SQL문 작성
			sql = "SELECT * FROM (SELECT a.*, rownum rnum FROM ("
				+ "SELECT * FROM zorder JOIN (SELECT order_num,"
				+ "LISTAGG(item_name,',') WITHIN GROUP (ORDER BY item_name) "
				+ "item_name FROM zorder_detail GROUP BY order_num) "
				+ "USING (order_num) WHERE mem_num=? " + sub_sql
				+ " ORDER BY order_num DESC)a) WHERE rnum>=? AND rnum<=?";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(++cnt, mem_num);//like검색
			if(keyword != null && !"".equals(keyword)) {//equals로
				pstmt.setString(++cnt, keyword);
			}
			pstmt.setInt(++cnt, start);
			pstmt.setInt(++cnt, end);
			
			//SQL문 실행
			rs = pstmt.executeQuery();
			list = new ArrayList<OrderVO>();
			while(rs.next()) {
				OrderVO order = new OrderVO();
				order.setOrder_num(rs.getInt("order_num"));
				order.setOrder_total(rs.getInt("order_total"));
				order.setItem_name(rs.getString("item_name"));
				order.setStatus(rs.getInt("status"));
				order.setReg_date(rs.getDate("reg_date"));
				
				list.add(order);
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, null, null);
		}
		return list;
	}
	//개별 상품 목록
	//주문 삭제(삭제시 재고를 원상 복귀시키지 않음, 주문 취소일 때 재고 수량 원상 복귀)
	//관리자/사용자 - 주문 상세
	//관리자/사용자 - 배송지 정보 수정
	//관리자 - 배송 상태 수정
	//사용자 - 주문 취소
	
}
