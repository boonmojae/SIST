$(function(){
	let rowCount = 10;
	let currentPage;
	let count;
	/*==============================
	* 댓글 목록
	* =============================*/
	//댓글 목록
	function selectList(pageNum){
		currentPage = pageNum;
		//로딩 이미지 노출
		$('#loaidng').show();
		//서버와 통신
		$.ajax({
			url:'listReply.do',
			type:'post',
			data:{pageNum:pageNum,rowCount:rowCount,board_num:$('#board_num').val()},
			dataType:'json',
			success:function(param){
				//로딩 이미지 감추기
				$('#loading').hide();
				count = param.count;//페이지 처리 해야돼서 갖고있어야됨
				
				if(pageNum==1){
					//처음 호출시는 해당 ID의 div의 내부 내용물을 제거
					$('#output').empty();
				}
				
				//list가 배열형태로 반환됨
				$(param.list).each(function(index,item){//item 하나의 레코드처럼 생각
					let output = '<div class="item">';
					output += '<h4>' + item.id + '</h4>';
					output += '<div class="sub-item">';
					output += '<p>' + item.re_content + '</p>';
					
					if(item.re_modifydate){
						output += '<span class="modify-date">최근 수정일 : ' + item.re_modifydate + '</span>';
					}else{
						output += '<span class="modify-date">최근 등록일 : ' + item.re_date + '</span>';
					}
					
					//로그인한 회원번호와 작성자의 회원번호 일치 여부 체크
					if(param.user_num==item.mem_num){
						output += ' <input type="button" data-renum="' + item.re_num + '" value="수정" class="modify-btn">';
						output += ' <input type="button" data-renum="' + item.re_num + '" value="삭제" class="delete-btn">';
					}
					output += '<hr size="1" noshade width="100%">';
					output += '</div>';
					output += '</div>';
					
					//문서 객체에 추가
					$('#output').append(output);
				});
				
				//page button 처리
				if(currentPage>=Math.ceil(count/rowCount)){
					//다음 페이지가 없음
					$('.paging-button').hide();
				}else{
					//다음 페이지가 존재
					$('.paging-button').show();
				}
				
			},
			error:function(){
				$('#loading').hide();
				alert('네트워크 오류 발생');
			}
		});
	}
	//페이지 처리 이벤트 연결(다음 댓글 보기 버튼 클릭시 데이터 추가)
	$('.paging-button input').click(function(){
		selectList(currentPage + 1);//다음페이지가 존재할 경우 +1증가된 값을 읽어옴/1이 아니면 기존데이터 남겨두고 뒤에 붙임
	});
	
	/*==============================
	* 댓글 등록
	* =============================*/
	//댓글 등록
	$('#re_form').submit(function(event){//이벤트 핸들러 매개변수 받고 ajax로 통신하기 위해 기존 이벤트 제거
		//내용 입력안하면 전송할 수 없으니까 조건 체크
		if($('#re_content').val().trim()==''){
			alert('내용을 입력하세요');
			$('#re_content').val('').focus();
			return false;
		}
		
		//form 이하의 태그에 입력한 데이터를 모두 읽어서 쿼리 스트링(parameter,value가 쌍)으로 반환
		let form_data = $(this).serialize();
		
		//서버와 통신
		$.ajax({
			url:'writeReply.do',
			type:'post',
			data:form_data,
			dataType:'json',
			success:function(param){
				if(param.result == 'logout'){
					alert('로그인 해야 작성할 수 있습니다');
				}else if(param.result == 'success'){
					//폼 초기화
					initForm();
					//댓글 작성이 성공하면 새로 삽입한 글을 포함해서 첫번째 페이지의 게시글 목록을 다시 호출함
					selectList(1);
				}else{
					alert('댓글 등록 오류');
				}
			},
			error:function(){
				alert('네트워크 오류 발생');//이클립스 콘솔 보기,그래도 없으면 브라우저 검사 네트워크 -> 오류메세지 - 실행 주소 보기
			}
		});
		
		//기본 이벤트 제거
		event.preventDefault();
	});
	
	
	//댓글 작성 폼 초기화
	function initForm(){
		$('textarea').val('');
		$('#re_first .letter-count').text('300/300');
	}
	
	/*==============================
	* 댓글 수정
	* =============================*/
	$(document).on('click','.modify-btn',function(){
		//댓글 번호
		let re_num = $(this).attr('data-renum');
		//댓글 내용													g:지정문자열 모두,i:대소문자 무시
		let content = $(this).parent().find('p').html().replace(/<br>/gi,'\n');//br이 대문자면 구별하지말고 다 \n으로 바꿔라/안하면 <br>태그가 보임
		
		//댓글 수정폼 UI
		let modifyUI = '<form id="mre_form">';
		modifyUI += '<input type="hidden" name="re_num" id="mre_num" value="' + re_num + '">';
		modifyUI += '<textarea rows="3" cols="50" name="re_content" id="mre_content" class="rep-content" >' + content + '</textarea>';
	});
	/*==============================
	* 댓글 등록 및 수정 공통
	* =============================*/
	//textarea에 내용 입력시 글자수 체크-댓글을 수정할때 동적으로 처리할것(.on사용)
	$(document).on('keyup','textarea',function(){
		//입력한 글자수 구함		/this=이벤트가 발생한 textarea
		let inputLength = $(this).val().length;
		
		if(inputLength > 300){//300자를 넘어선 경우
			$(this).val($(this).val().substring(0,300));
		}else{//300자 이하
			let remain = 300 - inputLength;//차감되는 글자수
			remain += '/300';// ex)107/300
			if($(this).attr('id') == 're_content'){
				//등록폼 글자수	후손 선택자 사용
				$('#re_first .letter-count').text(remain);
			}else{
				//수정폼 글자수
				$('#mre_first .letter-count').text(remain);
			}
		}
	});
	
	
	/*==============================
	* 댓글 삭제
	* =============================*/
	
	/*==============================
	* 초기 데이터(목록) 호출
	* =============================*/
	selectList(1);
});