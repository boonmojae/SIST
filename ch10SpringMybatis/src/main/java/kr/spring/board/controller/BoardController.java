package kr.spring.board.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

//org.slf4j
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.board.service.BoardService;
import kr.spring.board.vo.BoardVO;
import kr.spring.util.PagingUtil;
import oracle.jdbc.proxy.annotation.Post;

@Controller
public class BoardController {
	@Autowired
	private BoardService boardService;
	//로그 처리(로그 대상 지정)
	private static final Logger log = LoggerFactory.getLogger(BoardController.class);
	
	//유효성 체크를 위한 자바빈(VO) 초기화
	@ModelAttribute//속성명을 안적는건 가능하지만 여기서는 필수로 적어야됨
	public BoardVO initCommand() {
		return new BoardVO();
	}
	
	//글쓰기 폼 호출
	@GetMapping("/insert.do")
	public String form() {
		return "insertForm";
	}
	//전송된 데이터 처리
	@PostMapping("/insert.do")
	public String submit(@Valid BoardVO vo,BindingResult result) {
		log.debug("<<BoardVO>> : " + vo);
		
		//유효성 체크 결과 오류가 있으면 폼 호출
		if(result.hasErrors()) {
			return form();
		}
		//글쓰기
		boardService.insertBoard(vo);
		
		return "redirect:/list.do";//뷰 안만드려고 redirect사용
	}
	
	
	//목록
	@RequestMapping("/list.do")
	public ModelAndView getList(@RequestParam(value="pageNum",defaultValue="1")int currentPage) {
		
		//총레코드 수
		int count = boardService.selectBoardCount();
		//페이지 처리
		PagingUtil page = new PagingUtil(currentPage,count,20,10,"list.do");
		//목록 호출
		List<BoardVO> list = null;
		if(count > 0) {
			//start,end를 map에 담아서 보낸거니까
			Map<String,Integer> map = new HashMap<String,Integer>();
			map.put("start", page.getStartRow());
			map.put("end", page.getEndRow());
			list = boardService.selectBoardList(map);
		}
		
		ModelAndView mav = new ModelAndView();
		//뷰 이름 설정
		mav.setViewName("selectList");
		//데이터 저장
		mav.addObject("count",count);
		mav.addObject("list",list);
		mav.addObject("page",page.getPage());
		
		return mav;
	}
	//글 상세
	@RequestMapping("/detail.do")
	public ModelAndView detail(int num) {
		BoardVO board = boardService.selectBoard(num);
		return new ModelAndView("selectDetail","board",board);//뷰 이름,속성명,속성값
	}
	//수정폼 호출
	@GetMapping("/update.do")
	public String formUpdate(int num, Model model) {//프라이머리키 num값 받음,한건의 데이터 읽고 request에 저장
		model.addAttribute("boardVO", boardService.selectBoard(num));
		return "updateForm";
	}
	//전송된 데이터 처리
	@PostMapping("/update.do")
	public String submitUpdate(@Valid BoardVO vo,BindingResult result) {
		log.debug("<<BoardVO>> : " + vo);
		
		//유효성 체크 결과 오류가 있으면 폼 호출
		if(result.hasErrors()) {
			return "updateForm";
		}
		
		//비밀번호 일치 여부 체크
		//DB에 저장된 비밀번호 구하기
		BoardVO db_board = boardService.selectBoard(vo.getNum());//한건의 데이터 읽어옴
		//비밀번호 체크
		if(!db_board.getPasswd().equals(vo.getPasswd())) {
			result.rejectValue("passwd", "invalidPassword");
			return "updateForm";
		}
		//글 수정
		boardService.updateBoard(vo);
		
		return "redirect:/list.do";
	}
	//글 삭제 폼 호출
	@GetMapping("/delete.do")
	public String formDelete(BoardVO vo) {
		return "deleteForm";
	}
	//글 삭제 처리
	@PostMapping("/delete.do")
	public String submitDelete(@Valid BoardVO vo,BindingResult result) {//num은 처음에 없을 수 있으니까 유효성 체크 부분 없음/비밀번호만 유효성 체크
		//정상적으로 데이터가 담겨서 오는지 확인
		log.debug("<<BoardVO>> : " + vo);
		
		//유효성 체크 결과 오류가 있으면 폼 호출
		//비밀번호 전송 여부만 체크
		if(result.hasFieldErrors("passwd")) {
			return "deleteForm";
		}
		//비밀번호 일치 여부 체크
		//DB에 저장된 비밀번호 구하기
		BoardVO db_board = boardService.selectBoard(vo.getNum());
		//비밀번호 체크
		if(!db_board.getPasswd().equals(vo.getPasswd())) {
			result.rejectValue("passwd", "invalidPassword");
			return "deleteForm";
		}
		//글 삭제
		boardService.deleteBoard(vo.getNum());
		return "redirect:/list.do";
	}
}

