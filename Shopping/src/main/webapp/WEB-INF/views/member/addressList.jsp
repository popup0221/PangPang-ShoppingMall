<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>배송지 관리</title>
</head>
<body>
	<h1>
      <a href="../main/Coupang"><img src="../resources/image/main.png"></a>
	</h1>
	<h2>배송지 목록</h2>
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
				<form action="addressEdit">
				<input name="addressNo" value="${list.addressNo}" type="hidden">
				<input name="addressName" value="${list.addressName}" type="hidden">
				<input name="addressDestination" value="${list.addressDestination}" type="hidden">
				<input name="addressDetail" value="${list.addressDetail}" type="hidden">
				<input name="addressPhone" value="${list.addressPhone}" type="hidden">
				<input name="addressRequest" value="${list.addressRequest}" type="hidden">
				<button type="submit">수정</button>
				</form>
			</div>
		</div>
    </c:forEach>

	<br>

	<div>
		<a href="addressNew"> <!-- addressNew -->
    	<input type="button" value="+배송지 추가">
  		</a>
	</div>
</body>
</html>