package kr.spring.ch08.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import kr.spring.ch08.validator.SubmitReportValidator;
import kr.spring.ch08.vo.SubmitReportVO;

@Controller
public class SubmitReportController {
	
	//업로드 절대경로 알아내야 파일 업로드 가능
	//파일 업로드 경로 읽기
	@Value("${file_path}")//절대경로 읽어와서 path에 넣어줌
	private String path;//읽어오려면 컨테이너에 저장되어있는 파일정보(key,value쌍)에서 key를 넣으면 value를 불러오는 @value 어노테이션으로 path에 주소 저장
	
	//유효성 체크를 위한 자바빈 초기화(하면 커스텀태그 사용 가능)
	@ModelAttribute("report")
	public SubmitReportVO initCommand() {
		return new SubmitReportVO();
	}
	//폼 호출
	@GetMapping("/report/submitReport.do")
	public String form() {
		return "report/submitReportForm";
	}
	//폼에서 전송된 데이터 처리
	@PostMapping("/report/submitReport.do")//위에서 report라고 정의해서 맞춰야됨						
	public String submit(@ModelAttribute("report") SubmitReportVO vo,BindingResult result) throws IllegalStateException, IOException {
		
		//submitReport에 정보가 담겼다면 필수업로드 처리?
		//전송된 데이터 유효성 체크
		new SubmitReportValidator().validate(vo, result);
		//유효성 체크 결과 오류가 있으면 폼을 호출
		if(result.hasErrors()) {
			return form();
		}
		
		//정상적으로 파일이 업로드 되었을 경우
		//파일(경로정보 가지고있음)객체 생성								//파일명 읽고 (50)줄 원하는 경로에 복사해줌
		File file = new File(path + "/" + vo.getReportFile().getOriginalFilename());//getOriginalFilename() 업로드된 파일의 원본 파일명을 반환
		//지정한 경로에 파일 저장
		vo.getReportFile().transferTo(file);//transferTo는 멀티파일이 가지고 있는 경로를 읽은걸 여기에 복사해줌/오류 우클릭 첫번째꺼 생성하면 위에 throws IllegalStateException, IOException
		
		System.out.println(vo);
		
		return "report/submittedReport";
	}
	
}
