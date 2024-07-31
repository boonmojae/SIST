package kr.spring.ch05.vo;

public class SearchVO {
	private int type;
	private String query;
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	//필드에 값을 출력하는게 목적
	@Override
	public String toString() {
		return "SearchVO [type=" + type + ", query=" + query + "]";
	}

}
