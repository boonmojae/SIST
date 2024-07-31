package kr.spring.ch11.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.ch11.vo.PageRank;

@Controller
public class PageRanksController {
	//엑셀 다운로드
	@RequestMapping("/pageRanksExcel.do")
	public ModelAndView handle() {
		//데이터 여러개 만들어서 ArrayList에
		List<PageRank> pageRanks = new ArrayList<PageRank>();
		pageRanks.add(new PageRank(1,"/board/list.do"));
		pageRanks.add(new PageRank(2,"/member/login.do"));
		pageRanks.add(new PageRank(3,"/cart/list.do"));
		
		//ModelAndView 생성해서 객체 반환/	뷰이름			속성명		   속성값
		return new ModelAndView("pageRanks","pageRanks",pageRanks);//뷰 이름과 빈 이름이 동일
	}
	
	//JSON 문자열 처리
	@RequestMapping("/pageJson.do")
	@ResponseBody//내가 직접 뷰 지정할 필요없고 이 어노테이션이 자동으로 뷰를 만들어줌(key,value쌍으로 만들어야됨) list,Map,자바빈이면 JSON가능? 
	public List<PageRank> parseJson(){
		List<PageRank> pageRanks = new ArrayList<PageRank>();
		pageRanks.add(new PageRank(1,"/file.do"));
		pageRanks.add(new PageRank(1,"/pageRanksExcel.do"));
		pageRanks.add(new PageRank(3,"/pageJson.do"));
		
		return pageRanks;//List반환하면 컨테이너가 자동반환X 어노테이션 넣어놔야됨 
	}
}
