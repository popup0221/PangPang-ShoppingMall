<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
.thick{
	border: 0;
    height: 3px;
    background: #333333;
}
table{
	width: 590px;
	height: 165px;
	background: #eee;
	border-top: 1px solid #ccc;
	border-bottom: 1px solid #ccc;
}
</style>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<title>결제 완료</title>
</head>
<body>
	<h1>
      <a href="Coupang"><img src="../resources/image/main.png"></a>
    </h1>

    <hr>
    
	    <h1>주문/결제</h1>
	    
	    <hr class="thick">
	    <div style="text-align: center;">
		    <div>
		    	<h2>주문이 완료되었습니다. 감사합니다!</h2>
		    </div>
		    <div>
		    	<span>주문일 ${buyDate}</span> 
		    </div>
		    
		    <br>
		    
		    <div>
		    	<button type="button" id="btn_coupang">계속 쇼핑하기></button>
		    </div>
	    </div>
	    
	    <hr>
	    
	    <div style="display: flex; justify-content: space-around; width: 1300px; margin-left: auto; margin-right: auto;">
			<div>
				<h3>받는사람 정보</h3>
				<table style="text-align: left;">
					<tr>
						<th>이름</th>
						<td>${avo.addressName}</td>
					</tr>
					<tr>
						<th>휴대폰</th>
						<td>${avo.addressPhone}</td>
					</tr>
					<tr>
						<th>배송주소</th>
						<td>${avo.addressDestination}, ${avo.addressDetail}</td>
					</tr>
					<tr>
						<th>배송요청사항</th>
						<td>${avo.addressRequest}</td>
					</tr>
				</table>
			</div>
	
			<div>
				<h3>결제정보</h3>
				<table style="height: 50px;">
                
					<tr>
						<th style="text-align: left;">총 결제금액</th>
						<td style="text-align: right;">${buyTotalPrice}원</td>
					</tr>
				</table>
			</div>
	    </div>
	    
	<script type="text/javascript">
		$('#btn_coupang').click(function(){
			location = encodeURI('/project/main/Coupang');
		}); // end btn_coupang click()
	</script>



	    
</body>
</html>