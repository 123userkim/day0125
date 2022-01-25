<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<a href="updateGoods?no=${g.no}" >수정</a>
<a href="deleteGoods?no=${g.no}" >삭제</a>
	<h2>상품상세</h2>
	<hr>
	상품번호 : ${g.no }<br>
	상품이름 : ${g.name }<br>
	상품가격 : ${g.price }<br>
	상품수량 : ${g.qty }<br>
	<img  src="images/${g.fname }" width="100" height="100">
</body>
</html>