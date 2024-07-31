package kr.spring.board.controller;

import java.util.List;

import javax.validation.Valid;

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
//생성순 controller->BoardDAO->BoardDAOImpl->BoardService->BoardServiceImple|BImple->SImple->controller->jsp순으로 진행->
@Controller
public class BoardController {//로그 대상이 되게 설정
	
	//주입받아서 메서드사용가능(인터페이스 타입사용)
	@Autowired
	private BoardService boardService;
	
	//로그 처리 (로그 대상 지정)
	private static final Logger log =LoggerFactory.getLogger(BoardController.class);
	
	/*
	 *  로그 레벨
	 *  FATAL : 가장 심각한 오류 +프로그램동작이 안됨(fatal,error)
	 *  ERROR : 일반적인 오류
	 *  WARN : 주의를 요하는 경우	+에러는 아님(방식을 바꾸면 상관없음)
	 *  INFO : 런타임시 관심있는 경우	+일반적으로 info,debuge 사용/info를 하면 그 위에 다 나옴
	 *  DEBUG : 시스템 흐름과 관련된 상세 정보		+debuge를 하면 그 위에 다 나옴(보통 개발할때는 debuge사용)//근데 이렇게 하면 로그가 많이 쌓여서 상용에는 error사용
	 *  TRACE : 가장 상세한 정보
	 *  
	 */
	
	//유효성 체크를 위한 폼 초기화
	@ModelAttribute//속성명 지정안해서 boardVO로 자동 지정
	public BoardVO initCommand() {
		return new BoardVO();
	}
	
	//폼 호출
	@GetMapping("/insert.do")
	public String form() {
		return "insertForm";
	}
	//전송된 데이터 처리
	@PostMapping("/insert.do")
	public String submit(@Valid BoardVO vo,BindingResult result) {
		log.debug("<<BoardVO>> : " + vo);//<<BoardVO>>콘솔 구별하기 쉽게 넣음/스프링에서는 syso보단 log처리 하는게 더 좋음
		
		//유효성 체크 결과 오류가 있으면 폼을 호출
		if(result.hasErrors()) {//인자에서 에러메세지가 저장된걸 가져옴 @Valid로 Binding,vo연계시킴
			return form();
		}
		
		//글 등록 
		boardService.insertBoard(vo);
		
		return "redirect:/list.do";
	}
	
	//목록
	@RequestMapping("/list.do")//pageNum(밖) currentPage(안)사용해서 value로 명시함
	public ModelAndView process(@RequestParam(value="pageNum", defaultValue="1")int currentPage) {//@RequestParam은 1을 기본,int로 변환 두가지 작업
		
		int count = boardService.getBoardCount();
		
		log.debug("<<count>> : " + count);
		
		//페이지 처리
		PagingUtil page = new PagingUtil(currentPage,count,20,10,"list.do");//한페이지에 20,10,호출URL명시
		//목록 호출
		List<BoardVO> list = null;
		if(count > 0) {
			list = boardService.getBoardList(page.getStartRow(), page.getEndRow());
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("selectList");
		//데이터 실어서 보냄
		mav.addObject("count",count);//뷰에서 사용할 수 있음
		mav.addObject("list",list);
		mav.addObject("page",page.getPage());
		
		return mav;
	}
	
	//글 상세
	@RequestMapping("/detail.do")
	public ModelAndView detail(int num) {
		log.debug("<<num>> : " + num);
		
		//한건의 데이터 읽어와 매핑
		BoardVO board = boardService.getBoard(num);//num값 안써도 자동으로 파싱돼서 넘어감
		//ModelAndView반환
								//뷰이름,			속성명,	속성값
		return new ModelAndView("selectDetail","board",board);
	}
	
	//수정 폼
	@GetMapping("/update.do")
	public String formUpdate(int num,Model model) {//Model 은request에 저장하는 용도 view정보 저장X,데이터만 가능
		model.addAttribute("boardVO",boardService.getBoard(num));//모델은 반환안함,셋팅만하면 request에 저장하는구조/andview는 반환 
		return "updateForm";//이미 dao,service작업 되어있음/유효성체크로 자바빈 물고있어서 따로 명시 안해도 되는듯?
	}
	
	@PostMapping("/update.do")
	//전송된 데이터 처리
	public String submitUpdate(@Valid BoardVO vo,BindingResult result) {//유효성체크하면서 처리
		log.debug("<<BoardVO>> : " + vo);
		
		//유효성 체크 결과 오류가 있으면 폼을 호출
		if(result.hasErrors()) {
			return "updateForm";//BoardVO가 있어서 폼만 호출해도됨/메서드가 있으면 메서드 형태로 해도 됨(63은 가능한데 int num,Model model로 복잡해서 사용안함)
		}
		
		//DB에 저장된 비밀번호 구하기
		BoardVO db_board = boardService.getBoard(vo.getNum());
		//비밀번호 일치 여부 체크
		if(!db_board.getPasswd().equals(vo.getPasswd())) {
			//필드가 있는경우에는 rejectValue사용
			result.rejectValue("passwd", "invalidPassword");//필드,에러코드
			return "updateForm";
		}
		
		//글 수정
		boardService.updateBoard(vo);
		//업데이트가 정상적으로 호출됐을때
		return "redirect:/list.do";//뷰 안만들고 redirect
	}
	//삭제 폼 호출
	@GetMapping("/delete.do")
	public String formDelete(BoardVO vo) {//유효성 체크할거라 자바빈에 담아서 처리.인자에 자바빈 명시(유효성체크 하려면 자바빈 물고있어야됨)/BoardVO에 num값이 담김.num입력안하거나 세밀하게 할꺼면 num받아서 해도됨
		return "deleteForm";
	}
	//전송된 데이터 처리
	@PostMapping("/delete.do")
	public String submitDelete(@Valid BoardVO vo, BindingResult result) {//유효성 체크
		log.debug("<<BoardVO>> : " + vo);//비밀번호만 체크할 수 없음(VO에서 나머지 전달안하면 null이어서 에러로 인식)/그래서 나머지는 체크 안하고 비밀번호 한개만 체크하는 메서드 사용  
		//유효성 체크(아무것도 입력하지 않았을때, 비밀번호 입력이 틀렸을때)
		//비밀번호만 유효성 체크 결과 오류가 있으면 폼 호출(특정 필드만 체크)
		if(result.hasFieldErrors("passwd")) {//이렇게 안하면 VO 따로 만들어야됨
			return "deleteForm";
		}
		//DB에 저장된 비밀번호 구하기(정상적으로 됐다면 인증 받아야됨)
		BoardVO db_board = boardService.getBoard(vo.getNum());//한건의 데이터 읽어옴
		//비밀번호 일치 여부 체크
		if(!db_board.getPasswd().equals(vo.getPasswd())) {
			//안으로 진입하면 비밀번호 잘못된것
			result.rejectValue("passwd", "invalidPassword");
			return "deleteForm";
		}
		//비밀번호가 일치하면 삭제
		boardService.deleteBoard(vo.getNum());
		
		return "redirect:/list.do";//정상적이면 목록으로(뷰 안만듦)
	}
}
