package kr.spring.ch10.view;
//뷰 클래스
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;
//DownloadView로 뷰 작업하려는데 객체를 호출함 근데 객체는 메서드가 동작하면서 작동하게 되어있는데 그냥 구현하면 메서드 실행 못함 그래서 상속받아서 클래스가 뷰역할로 동작할수 있게 함- renderMerged를 구현하면 자동으로 실행돼서 여기서 작업할 수 있음
public class DownloadView extends AbstractView{//뷰 역할 할수있게 상속받아야됨/추상클래스로 rederMerged반드시 구현함
	//브라우저가 뷰어 형태이면 안됨
	//생성자
	public DownloadView() {
		setContentType("application/download;charset=utf-8");//application/download 텍스트,이미지 무조건 뷰어로 다운로드 처리함
	}
	//stream은 목적지로부터 파일을 읽을때 사용/파일로 부터 정보를 읽을때 fileinputStrim(out도 있음)/자바에서 파일 처리할땐 Stream형식으로 처리해야됨
	//renderMergedOutputModel이 동작될때 map이 속성명과 속성값으로 명시한거로 경로 받음->21보기
	@Override	//request가 아니라 model에서 정보 뽑아냄 key=다운로드 파일 value= 경로	/DownloadController클래스(20)모델앤뷰
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {//throws가 있어서 (42)catch안만듦
		//다운로드하는 파일의 경로 정보가 저장된 File 객체 반환
		File file = (File)model.get("downloadFile");
		
		//컨텐트 타입 지정	생성자를 이용해서 넣었던 컨텐트 타입 넣으면 됨
		response.setContentType(getContentType());
		
		//컨텐트의 용량 지정
		response.setContentLength((int)file.length());
		
		//파일명 구하기
		String fileName = new String(file.getName().getBytes("utf-8"),"iso-8859-1");//전체적으로 지정할 땐 iso-8859-1으로/fileName안에 file.txt들어가 있음 그래서 변수로 넣음
		
		//HTTP 응답 메시지 헤더 셋팅
		response.setHeader("Content-Disposition", "attachment;filename=\""+fileName+"\";");//"file.txt"에 "가 일반문자가 아닌 특수문자로 인식돼 에러/파일명구하기에서 fileName받음
		response.setHeader("Content-Transfer-Encoding", "binary");
		
		//파일을 읽어와서 보내야됨 OutputStream이용해서
		//파일 쓰기
		OutputStream out = response.getOutputStream();//전송 역할 수행
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);//파일 읽기
			//읽기 정보를 쓰기 정보로 변환->목적지(네트워크)
			FileCopyUtils.copy(fis, out);//읽어온 input데이터를->output으로 쉽게 넘길수있게 도와줌fis=읽은정보 out=쓰기정보
		}finally {
			if(fis!=null)try {fis.close();}catch(IOException e) {}
		}
		//파일 전송(클라이언트로 전송)(18)application/download있어서 뷰어로 다운로드 처리함
		out.flush();
	}

}
