<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<meta charset="UTF-8">
<title>로그인</title>
</head>
<body style="text-align: center;">
	<h1>
      <a href="../main/Coupang"><img src="../resources/image/main.png"></a>
	</h1>
	<!-- 
		로그인 창
		- 입력한 아이디와 비밀번호가 일치해야 로그인 성공 -> 메인 페이지로 넘어감
		! 실제 db의 id와 pw를 가져와 비교하여 페이징 처리를 해야함
	 -->
	<form action="login" method="post">
		<input type="email" name="customerId" id="customerId" placeholder="아이디(이메일)" required autofocus>
		<br>
		
		<input type="password" name="customerPw" id="customerPw" placeholder="비밀번호" required>
		<br>
		
		<c:if test="${param.loginFail eq 'loginFail'}">
			<p style="font-size: 10px; color: red">
			이메일 또는 비밀번호를 다시 확인하세요. 등록되지 않은 이메일이거나, 이메일 또는 비밀번호를 잘못 입력하셨습니다.
			</p>
   		</c:if>
   		<input type="submit" value="로그인">
   		
   		<br>
   		
	 </form>
	 
		<a href="register"> <!-- register -->
	    	<input type="submit" value="회원가입">
	 	</a>
	 	<br>
		<a href="accountFind" style="text-decoration: none;"> <!-- find -->
	    	아이디(이메일)/비밀번호 찾기>
	 	</a>
</body>
</html>