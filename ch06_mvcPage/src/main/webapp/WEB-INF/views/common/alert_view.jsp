<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>   
<script type="text/javascript">
	alert('${notice_msg}');/* 자바스크립트에서 문자열로 처리해야돼서 ''가 있어야됨 */
	location.href='${notice_url}';
</script>