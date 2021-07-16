<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
table, th, td {
  border-style: solid;
  border-width: 1px;
  text-align: center;
}

ul {
  list-style-type: none;
}

li {
  display: inline-block;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script>
	$(function(){
		$("#findBtn").click(function(){
			$.ajax({
				url : "findpw",
				type : "POST",
				data : {
					customerName : $("#name").val(),
					customerId : $("#email").val()
				},
				success : function(result) {
					alert(result);
				},
			})
		});
	})
</script>
<title>계정정보 찾기</title>
</head>
<body>
<section>
	<h1>
      <a href="../main/Coupang"><img src="../resources/image/main.png"></a>
	</h1>
	
	<h2>계정정보 찾기</h2>
	<nav>
		<ul>
			<li>
				<a href="accountFind">아이디(이메일) 찾기</a>
			</li>
			<li>
				<a href="findPassword">비밀번호 찾기</a>
			</li>
		</ul>
	</nav>
	<section>
		<div>
			<form action="login">
				<table>
					<tbody>
						<tr>
							<th scope="row">이름</th>
							<td>
							<input class="w3-input" type="text" id="name" name="name" required style="border:none">
							</td>
						</tr>
						<tr>
							<th scope="row">아이디(이메일)</th>
							<td>
							<input class="w3-input" type="email" id="email" name="email" required style="border:none">
							<p style="font-size: 9px">쿠팡에 가입된 계정 이메일을 정확히 기입해주시길 바랍니다.</p> 
							</td>
						</tr>
					</tbody>
				</table>
				<input type="button" id="findBtn" value="비밀번호 검색">
				<br>
				
				<a href="login"> <!-- login -->
    			<input type="button" value="로그인">
  				</a>
			</form>
		</div>
	</section>
</section>
</body>
</html>