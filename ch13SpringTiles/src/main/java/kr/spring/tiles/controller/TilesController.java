package kr.spring.tiles.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller//부트는 무조건 autoscane->자동스캔으로 컨테이너에 저장되고 동작됨
public class TilesController {
//루트로 진입하면 redirect하게 만들거임
	@RequestMapping("/")
	public String init() {
		return "redirect:/main.do";
	}
	@RequestMapping("/main.do")
	public String viewMain() {
		return "main";//Tiles가 조합해줘서 tiles 식별자를 명시/main이라 명시되어있으면 def.xml가서 main이 있는지 찾음
	}
	
	@RequestMapping("/company.do")
	public String viewCompany() {
		return "company";//Tiles 식별자
	}
	@RequestMapping("/product.do")
	public String viewProduct() {
		return "product";//Tiles 식별자
	}
}
