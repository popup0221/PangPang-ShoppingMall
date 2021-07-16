<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<title>회원정보확인</title>
</head>
<body>
	<h1>
      <a href="../main/Coupang"><img src="../resources/image/main.png"></a>
	</h1>
	
	<h2>회원 탈퇴</h2>
	<form action="deleteCheck" method="post">
	<div>
		<p>
			<strong>보안을 위해 회원님의 이름과 계정 이메일 및 비밀번호를 확인합니다.</strong>
		</p>
		<label>
			<span>이름 : </span>
			<input type="text" readonly value="${vo.customerName}">
		</label>
		<label>
			<span>이메일 : </span>
			<input type="text" readonly value="${vo.customerId}">
		</label>
		<label>
			<span>비밀번호 : </span>
			<input type="password" name="customerPw" placeholder="비밀번호를 입력하세요." required>
		</label>
		<input type="submit" value="본인확인">
	</div>
	</form>
	<span id="fail" style="display: none;">
	<c:if test="${param.Fail eq 'Fail'}">
		fail
 	</c:if>
	</span>
<script type="text/javascript">
	const fail = document.getElementById('fail').innerHTML;
	console.log(fail);
	if(fail.indexOf('fail')!=-1){
		window.onload = function() {
			 alert("비밀번호가 틀렸습니다.");
		}
	}
</script>
</body>
</html>