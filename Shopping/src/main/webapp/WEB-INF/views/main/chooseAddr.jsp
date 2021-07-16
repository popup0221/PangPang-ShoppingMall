<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
$(document).ready(function () {
	$('.chooseBtn').click(function () {
		console.log(this);
		var addressNo = $(this).prevAll('#addressNo').val();
		var addressName = $(this).prevAll('#addressName').val();
		var addressDestination = $(this).prevAll('#addressDestination').val();
		var addressDetail = $(this).prevAll('#addressDetail').val();
		var addressPhone = $(this).prevAll('#addressPhone').val();
		var addressRequest = $(this).prevAll('#addressRequest').val();
		console.log("이름 : " + addressName);
		console.log("배송주소 : " + addressDestination);
		console.log("상세주소 : " + addressDetail);
		console.log("연락처 : " + addressPhone);
		console.log("요청사항 : " + addressRequest);
		
		opener.document.getElementById("addressNo").value = addressNo;
		opener.document.getElementById("addressName").innerHTML = addressName;
		opener.document.getElementById("addressDestination").innerHTML = addressDestination;
		opener.document.getElementById("addressDetail").innerHTML = addressDetail;
		opener.document.getElementById("addressPhone").innerHTML = addressPhone;
		opener.document.getElementById("addressRequest").innerHTML = addressRequest;
		opener.$('#addressTbl').attr('style', "display:'';");
		window.close(); 
	});
});

</script>
<title>Insert title here</title>
</head>
<body>
	배송지를 선택하세요.
	
	<c:forEach var="list" items="${list}">
		<div style="border: 1px solid; padding: 10px;">
			<div>
		    	<p>${list.addressName}</p>
			</div>
			<div>
				${list.addressDestination}, ${list.addressDetail}
			</div>
			<div>
				${list.addressPhone}
			</div>
			<div>
				${list.addressRequest}
			</div>
			<div>
				<input id="addressNo" value="${list.addressNo}" type="hidden">
				<input id="addressName" value="${list.addressName}" type="hidden">
				<input id="addressDestination" value="${list.addressDestination}" type="hidden">
				<input id="addressDetail" value="${list.addressDetail}" type="hidden">
				<input id="addressPhone" value="${list.addressPhone}" type="hidden">
				<input id="addressRequest" value="${list.addressRequest}" type="hidden">
				<input class="chooseBtn" type="button" value="선택하기">
			</div>
		</div>
    </c:forEach>
	
	<div>
		<a href="../member/addressNew"> <!-- addressNew -->
    	<input type="button" value="+배송지 추가">
  		</a>
	</div>
</body>
</html>