<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<style type="text/css">
table.design {
  border-collapse: separate;
  border-spacing: 0;
  text-align: left;
  line-height: 1.5;
  border-top: 1px solid #ccc;
  margin : 20px 10px;
}
table.design th.th {
  width: 150px;
  padding: 10px;
  font-weight: bold;
  vertical-align: top;
  border-bottom: 1px solid #ccc;
  border-top: 1px solid #fff;
  border-left: 1px solid #fff;
  background: #eee;
}
table.design td.td {
  width: 350px;
  padding: 10px;
  vertical-align: top;
  border-bottom: 1px solid #ccc;
}
tr.delete td.delete{
border-right:none;
border-left:none;
border-bottom:none;
}

.no_enter{
display: inline-block;
}

.password {
  vertical-align : top;
}
</style>
<script type="text/javascript">
function name_change() {
	$('#name_change_btn').hide();
	$('#name_change_cancel').show();
	document.getElementById("name_change").style.display="block";
}
function name_cancel() {
	$('#name_change_btn').show();
	$('#name_change_cancel').hide();
	document.getElementById("name_change").style.display="none";
}

function update_name() {
	const updateName = document.getElementById('updateName').value;
	console.log(updateName);
	$.ajax({
		type : "post",
		url : "update_name",
		data : {
			updateName : updateName
		},
		success : function (updateName) {
			console.log(updateName);
				alert("이름이 수정되었습니다.");
				/* $('.name').text('${vo.customerName}'); */
				document.getElementById('updateName').value = '';
				document.getElementById('Name').value = 'updateName';
				location.reload();
		},
        error: function (XMLHttpRequest, textStatus, errorThrown){
        	alert(XMLHttpRequest + ", " + textStatus + ", " + errorThrown + '정보를 다시 입력해주시길 바랍니다.' );
			location.reload();
        }
	}); // end ajax()
} // end update_name()

function phone_change() {
	$('#phone_change_btn').hide();
	$('#phone_change_cancel').show();
	document.getElementById("phone_change").style.display="block";
}
function phone_cancel() {
	$('#phone_change_btn').show();
	$('#phone_change_cancel').hide();
	document.getElementById("phone_change").style.display="none";
}

function update_phone() {
	const updatePhone = document.getElementById('updatePhone').value;
	console.log(updatePhone);
	$.ajax({
		type : "post",
		url : "update_phone",
		data : {
			updatePhone : updatePhone
		},
		success : function (updatePhone) {
			console.log(updatePhone);
				alert("전화번호가 수정되었습니다.");
				document.getElementById('updatePhone').value = '';
				document.getElementById('Phone').value = 'updatePhone';
				location.reload();
		},
        error: function (XMLHttpRequest, textStatus, errorThrown){
        	alert(XMLHttpRequest + ", " + textStatus + ", " + errorThrown + '정보를 다시 입력해주시길 바랍니다.' );
			location.reload();
        }
	}); // end ajax()
} // end update_phone()

function check_pw(){
    // 비밀번호와 비밀번호 확인 박스가 공백이 아니면
    if(document.getElementById('updatePw').value !='' && document.getElementById('updatePw2').value!=''){
    	// 비밀번호와 비밀번호 확인 값이 같으면
        if(document.getElementById('updatePw').value==document.getElementById('updatePw2').value){
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

function update_password() {
	const customerPw = document.getElementById('customerPw').value;
	const updatePw = document.getElementById('updatePw').value;
	const updatePw2 = document.getElementById('updatePw2').value;
	console.log(customerPw);
	console.log(updatePw);
	if(customerPw != ''){
	if(updatePw==updatePw2){
	$.ajax({
		type : "post",
		url : "update_password",
		data : {
			customerPw : customerPw,
			updatePw : updatePw		
			// 현재 비밀번호와 변경할 비밀번호를 보낸다.
		},
		success : function (result) {
			console.log("result : " + result);
			if(result==1){
				alert("비밀번호가 수정되었습니다.");
				document.getElementById('customerPw').value = '';
				document.getElementById('updatePw').value = '';
				document.getElementById('updatePw2').value = '';
			}else{
				alert("현재 비밀번호가 틀렸습니다.");
			}
			document.getElementById('check_pw').innerHTML='';
		},
        error: function (XMLHttpRequest, textStatus, errorThrown){
        	alert(XMLHttpRequest + ", " + textStatus + ", " + errorThrown + '정보를 다시 입력해주시길 바랍니다.' );
			location.reload();
        }
	}); // end ajax()
	}else{
		alert('새 비밀번호가 일치하지 않습니다.');
		document.getElementById('updatePw').value = '';
		document.getElementById('updatePw2').value = '';
		document.getElementById('updatePw').focus();
	}
	}else{
		alert('비밀번호를 입력해주세요.');
		document.getElementById('customerPw').focus();
	}
} // end update_password()
</script>
<meta charset="UTF-8">
<title>회원정보수정</title>
</head>
<body>
	<h1>
      <a href="../main/Coupang"><img src="../resources/image/main.png"></a>
	</h1>
	
	<h2>회원정보 수정</h2>
	<table class="design">
		<tr class="tr">
			<th class="th" scope="row">아이디(이메일)</th>
			<td class="td">
				<strong>${customerId}</strong>
			</td>
		</tr>
		<tr class="tr">
			<th class="th" scope="row">이름</th>
			<td class="td">
				<strong id="Name">${vo.customerName}</strong>
				<button onclick="name_change()" style="display: inline-block;" id="name_change_btn">이름 변경</button> 
				<button onclick="name_cancel()" style="display: none;" id="name_change_cancel">이름 변경취소</button>
				<div id="name_change" style="display: none;">
					<input id="updateName" type="text" placeholder="변경하실 이름을 입력해주세요." style="width:190px;">
					<button onclick="update_name()">변경하기</button>
				</div>
			</td>
		</tr>
		<tr class="tr">
			<th class="th" scope="row">휴대폰 번호</th>
			<td class="td">
				<strong id="Phone">${vo.customerPhone}</strong>
				<button onclick="phone_change()" style="display: inline-block;" id="phone_change_btn">전화번호 변경</button> 
				<button onclick="phone_cancel()" style="display: none;" id="phone_change_cancel">전화번호 변경취소</button>
				<div id="phone_change" style="display: none;">
					<input id="updatePhone" type="text" placeholder="변경하실 전화번호를 입력해주세요." style="width:220px;">
					<button onclick="update_phone()">변경하기</button>
				</div>
			</td>
		</tr>
		<tr class="tr">
			<th class="th" scope="row">비밀번호변경</th>
			<td class="td">
				<table>
					<tbody>
						<tr>
							<th>현재 비밀번호</th>
							<td>
								<input type="password" name="customerPw" id="customerPw">
								<span></span>
							</td>
						</tr>
						<tr>
							<th>새 비밀번호</th>
							<td>
								<input type="password" name="updatePw" id="updatePw">
							</td>
						</tr>
						<tr class="password">
							<th>새 비밀번호 확인</th>
							<td>
								<input type="password" name="updatePw2" id="updatePw2" onkeyup="check_pw()">
								<span id="check_pw"></span>
							</td>
						</tr>
						<tr>
							<th></th>
							<td>
								<button onclick="update_password()">비밀번호 변경</button>
							</td>
						</tr>
					</tbody>
				</table>
			</td>
		</tr>
		<tr class="delete">
			<td class="delete" colspan="2" align="right">
				<p class="no_enter">탈퇴를 원하시면 우측의 회원탈퇴 버튼을 눌러주세요.</p>
				<a href="deleteCheck"> <!-- deleteCheck -->
		    		<input type="button" value="회원탈퇴">
		  		</a>
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<button class="no_enter" onclick="location.href='../main/Coupang'">나가기</button>
			</td>
		</tr>
	</table>
</body>
</html>