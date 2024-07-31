package kr.spring.ch11.view;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import kr.spring.ch11.vo.PageRank;

public class PageRanksView extends AbstractXlsView{//엑셀 처리할수 있는 별도의 상속(스프링에서 지원)

	@Override						//컨트롤러에서 저장된 데이터를 Map에서 속성명,속성값 뽑아냄
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//컨트롤러에서 전달한 List받기(바로 받거나 시트 생성해서 받기)
		//시트 생성
		HSSFSheet sheet = createFirstSheet((HSSFWorkbook)workbook);//부모 타입으로 넘어와서 자식타입으로 다운캐스팅
		//열이름 생성
		createColumnLabel(sheet);
		
		//시트에 데이터 표시하기
		//Map에 담겨있는 List정보 뽑아내기
		List<PageRank> pageRanks = (List<PageRank>)model.get("pageRanks");
		int rowNum = 1;//0은 이미 사용했기 때문에(사용안했으면 1부터 시작 안해도됨)
		for(PageRank rank : pageRanks) {
			createPageRankRow(sheet,rank,rowNum++);//rowNum을 증가시켜야 2,다음에 3../++rowNum이면 rowNum이 0이어야함
		}//엑셀파일 만드는거 stream만들어서 전송하는거 안해도됨(메서드가 끝나면서 stream처리)/원하는 파일명으로 처리하고싶으면 헤더 처리 해야됨
		
		//HTTP 응답 메시지 헤더 설정
		String fileName = "pageRanks2024.xls";
		response.setHeader("Content-Disposition", "attachment;filename=\""+fileName+"\";");
		response.setHeader("Content-Transfer-Encoding", "binary");
	
	}
	
	//시트 생성
	private HSSFSheet createFirstSheet(HSSFWorkbook workbook) {
		HSSFSheet sheet = workbook.createSheet();//시트 객체 생생
							//sheet index,이름
		workbook.setSheetName(0, "페이지 순위");
		//특정 컬럼에 넓이 지정
						//column Index,width(넓이 지정안하면 기본으로 들어가서 값 넣음)
		sheet.setColumnWidth(1, 256*20);
		//시트 반환
		return sheet;
	}
	//열이름 생성
	private void createColumnLabel(HSSFSheet sheet) {//시트 받아서 컬럼 레이블 만들게
		HSSFRow firstRow = sheet.createRow(0);//첫번째 행 정보
		HSSFCell cell = firstRow.createCell(0);
		cell.setCellValue("순위");
		
		cell = firstRow.createCell(1);
		cell.setCellValue("페이지");
	}
	//표시할 데이터 생성						
	private void createPageRankRow(HSSFSheet sheet,PageRank rank,int rowNum) {//첫번째(0)는 명시X(순위,페이지가 들어가 있음),두번째부터 명시해야하니까 루프돌릴때 순서값 받아야됨
		HSSFRow row = sheet.createRow(rowNum);
		HSSFCell cell = row.createCell(0);
		cell.setCellValue(rank.getRank());
		
		//두번째 방
		cell = row.createCell(1);
		cell.setCellValue(rank.getPage());
	}
}
