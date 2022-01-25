<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<a href="updateBoard?no=${b.no }">수정</a>
<h2>상세보기</h2>
	글번호 	${b.no } <br>
	글제목 	${b.title }<br>
	글작성자 	${b.writer}<br>
	내용 		
	<br>
	<textarea rows="10" cols="80" readonly="readonly">${b.content }</textarea> 
<br>
	
	작성일 	${b.regdate }<br>
	조회수 	${b.hit}<br>
	첨부파일명:  ${b.fname } <br>
	
</body>
</html>