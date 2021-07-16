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
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script>
$(document).on('click','#findId',function(){
	var name = $('#name').val();
 	var tel = $('#tel').val();

 	var postData = {'customerName' : name, 'customerPhone' : tel};

	$.ajax({
        url:'find_id',
        type:'POST',
        data: postData,
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        dataType : "text",

        success:function(result){
        	if(result==''){
        		document.getElementById('customerId').innerHTML='<p style="color: red;">가입된 계정이 아닙니다. 정보를 다시 입력해주시길 바랍니다.</p>';
        	}
        	else{
        		document.getElementById('customerId').innerHTML='회원님의 정보로 등록된 이메일은 : ';
        		document.getElementById('customerId').innerHTML+='<p style="color: blue; display : inline-block;">' + result + '</p>';
        		document.getElementById('customerId').innerHTML+='입니다.';
        	}
        },
        error: function (XMLHttpRequest, textStatus, errorThrown){
        	alert(XMLHttpRequest + ", " + textStatus + ", " + errorThrown + '정보를 다시 입력해주시길 바랍니다.' );
        }
    });
});

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
				<table>
					<tbody>
						<tr>
							<th>이름</th>
							<td>
							<input type="text" name="customerName" id="name" required style="border:none">
							</td>
						</tr>
						<tr>
							<th>등록한 휴대폰 번호</th>
							<td>
							<input type="text" name="customerPhone" id="tel" required style="border:none">
							</td>
						</tr>
					</tbody>
				</table>
				
				<input type="button" id="findId" value="아이디 검색">
				<br>
				
				<a href="login"> <!-- login -->
    			<input type="submit" value="로그인">
  				</a>
				<br>
				
				<span id="customerId"></span>
		</div>
	</section>
</section>
</body>
</html>