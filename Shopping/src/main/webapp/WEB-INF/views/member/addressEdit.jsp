<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script type="text/javascript">
function execPostCode() {
    new daum.Postcode({
        oncomplete: function(data) {
           // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

           // 도로명 주소의 노출 규칙에 따라 주소를 조합한다.
           // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
           var fullRoadAddr = data.roadAddress; // 도로명 주소 변수
           var extraRoadAddr = ''; // 도로명 조합형 주소 변수

           // 법정동명이 있을 경우 추가한다. (법정리는 제외)
           // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
           if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
               extraRoadAddr += data.bname;
           }
           // 건물명이 있고, 공동주택일 경우 추가한다.
           if(data.buildingName !== '' && data.apartment === 'Y'){
              extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
           }
           // 도로명, 지번 조합형 주소가 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
           if(extraRoadAddr !== ''){
               extraRoadAddr = ' (' + extraRoadAddr + ')';
           }
           // 도로명, 지번 주소의 유무에 따라 해당 조합형 주소를 추가한다.
           if(fullRoadAddr !== ''){
               fullRoadAddr += extraRoadAddr;
           }

           // 우편번호와 주소 정보를 해당 필드에 넣는다.
           console.log(data.zonecode);
           console.log(fullRoadAddr);
           
           document.getElementById('addr1').value = data.zonecode; //5자리 새우편번호 사용
           document.getElementById('addr2').value = fullRoadAddr;
       }
    }).open();
}
</script>
<meta charset="UTF-8">
<title>배송지 수정</title>
</head>
<body>
	<h1>
      <a href="../main/Coupang"><img src="../resources/image/main.png"></a>
	</h1>
	
	<form action="addressUpdate" method="post">
		<input name="customerId" type="hidden" value="${customerId}">
		<input name="addressNo" type="hidden" value="${addressNo}">
		<div>
			받는 사람 <br>
		    <input name="addressName" type="text" value="${addressName}" required>
		</div>
		<div>                   
		<input style="width: 40%; display: inline;" placeholder="우편번호" name="addr1" id="addr1" type="text" readonly="readonly" required="required">
		    <button type="button" onclick="execPostCode();"><i class="fa fa-search"></i> 우편번호 찾기</button>                               
		</div>
		<div>
		    <input class="form-control" style="top: 5px; width: 250px;" value="${addressDestination}" name="addressDestination" id="addr2" type="text" readonly="readonly" />
		</div>
		<div class="form-group">
		    <input class="form-control" value="${addressDetail}" name="addressDetail" id="addr3" type="text"  />
		</div>
		<div>
			휴대폰 번호 <br>
		    <input name="addressPhone" type="text" value="${addressPhone}" required>
		</div>
		<div>
			배송 요청사항 <br>
		    <input name="addressRequest" type="text"  value="${addressRequest}">
		</div>
		
		<input type="submit" value="저장">
	</form>
	<form action="addressDelete" method="post">
		<input name="addressNo" type="hidden" value="${addressNo}">
    	<input type="submit" value="삭제">
  	</form>
</body>
</html>