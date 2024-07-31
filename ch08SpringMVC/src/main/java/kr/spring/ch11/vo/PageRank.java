package kr.spring.ch11.vo;

public class PageRank {
	//자주 이용하는 페이지 순위 읽어오게
	private int rank;
	private String page;
	
	//기본 생성자를 만들어야 생성자,set둘 다 사용할수 있음 
	public PageRank() {}
	
	//생성자(인자가 있는)
	public PageRank(int rank, String page) {
		this.rank = rank;
		this.page = page;
	}
	
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	@Override
	public String toString() {
		return "PageRank [rank=" + rank + ", page=" + page + "]";
	}
	
}
