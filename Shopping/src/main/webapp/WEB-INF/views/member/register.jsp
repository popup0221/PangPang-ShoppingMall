<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script>
    function check_pw(){

        var pw = document.getElementById('pw').value;
		
        // 비밀번호의 문자길이를 제한
        if(pw.length < 6 || pw.length>16){
            window.alert('비밀번호는 6글자 이상, 16글자 이하만 이용 가능합니다.');
            document.getElementById('pw').value='';
        }

        // 비밀번호와 비밀번호 확인 박스가 공백이 아니면
        if(document.getElementById('pw').value !='' && document.getElementById('pw2').value!=''){
        	// 비밀번호와 비밀번호 확인 값이 같으면
            if(document.getElementById('pw').value==document.getElementById('pw2').value){
            	// 비밀번호가 일치한다는 텍스트를 띄워준다.
                document.getElementById('check_pw').innerHTML='<br/>비밀번호가 일치합니다.';
                document.getElementById('check_pw').style.color='blue';
            }
        	// 값이 다르면
            else{
            	// 일치하지 않는다는 텍스트를 띄워준다.
                document.getElementById('check_pw').innerHTML='<br/>비밀번호가 일치하지 않습니다.';
                document.getElementById('check_pw').style.color='red';
            }
        }
    } // end check_pw
    
    function check_id(customerId){
    	console.log(customerId);
    	$.ajax({
    		type : "post",
    		url : "check_id",
    		data : {
    			customerId : customerId
    		},
    		success : function(result){
    			console.log(result);
    			if(result == 1){
                document.getElementById('check_id').innerHTML='<br/>이미 가입된 아이디(이메일)입니다.';
                document.getElementById('check_id').style.color='red';
                document.getElementById('id').value='';
    			}else{
	                document.getElementById('check_id').innerHTML='';
    			}
    		}
    	}); // end ajax
    } // end check_id()
    
    function check_tel(customerPhone){
        console.log(customerPhone);
        $.ajax({
           type : "post",
           url : "check_tel",
           data : {
              customerPhone : customerPhone
           },
           success : function(result){
              console.log(result);
              if(result == 1){
                 document.getElementById('check_phone').innerHTML='이미 가입된 전화번호입니다.<br/>';
                 document.getElementById('check_phone').style.color='red';
                 document.getElementById('tel').value='';
              }else{
                    document.getElementById('check_phone').innerHTML='';
              }
           }
        }); // end ajax
     } // end check_tel()
    
   
</script>
<body style="text-align: center;">
	<h1>
      <a href="../main/Coupang"><img src="../resources/image/main.png"></a>
	</h1>
	
	<p>회원정보를 입력해주세요</p> 
	<!-- 
		회원가입 페이지
		! 전화번호 tel 타입이 적용이 안됨 safari에서만 가능
	 -->
	<form action="register" method="post">
		<input type="email" name="customerId" id="id" onchange="check_id(this.value)" placeholder="아이디(이메일)" required autofocus>
		<span id="check_id"></span>
		<br>
		<input type="password" name="customerPw" id="pw" placeholder="비밀번호" required>
		<br>
		<input type="password" name="customerPw2" id="pw2" onkeyup="check_pw()" placeholder="비밀번호 확인" required>
		<span id="check_pw"></span>
		<br>
		<input type="text" name="customerName" placeholder="이름" required>
		<br>
		<input type="tel" name="customerPhone" id="tel" onchange="check_tel(this.value)" placeholder="휴대폰 번호" required >
		<span id="check_phone"></span>
		<br>
		<input type="checkbox" name="ch" required>
		Coupang_Clone 서비스약관에 동의하시겠습니까? 
		<br>
		<input type="submit" value="동의하고 가입하기" name="btn">
	</form>
</body>
</html>