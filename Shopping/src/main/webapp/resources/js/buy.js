$(document).ready(function() {

	var price = $('.totalPrice').text().split('원');
	var cartName = $('input[id=cartName]').eq(0).val();
	console.log(price);
	console.log(cartName);

	var totalPrice = 0;

	for (var i = 0; i < price.length - 1; i++) {
		totalPrice += parseInt(price[i]);
	}
	console.log(totalPrice);
	$('.price').html(totalPrice.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ','));

	// 배송 날짜
var date = new Date();
var year = date.getFullYear(); // 년도
var month = date.getMonth() + 1;
var today = date.getDate();
var day = date.getDate() + 1;
var week = new Array('일요일', '월요일', '화요일', '수요일', '목요일', '금요일', '토요일');
var deldate = week[date.getDay() + 1] + "(내일) "
	+ month + "월 " + day + "일";
var deldate2 = year + '.' + month + '.' + day;
var buyDate = year + '.' + month + '.' + today;

console.log(deldate);
console.log(deldate2);
console.log(buyDate);
$('.deldate').html(deldate + '까지 도착');
$('.deldate2').val(deldate2);
$('.date').val(buyDate);

$('#check_module').on('click', function() {
	// 결제 팝업
	var buyId = "";
	var price = $('.totalPrice').text().split('원');
	var totalPrice = 0;
	for (var i = 0; i < price.length - 1; i++) {
		totalPrice += parseInt(price[i]);
	}
	var cartName = $('input[id=cartName]').eq(0).val();

	var addressName = document.getElementById('addressName').innerHTML;
	if (addressName != '.') {
		var IMP = window.IMP;
		IMP.init('imp14151292'); // 가맹점 식별코드
		IMP.request_pay({
			pg: 'inicis',
			pay_method: 'card',
			merchant_uid: 'merchant_' + new Date().getTime(),
			name: cartName,
			amount: totalPrice,
			buyer_email: '${customerId}',
			buyer_name: '${Cvo.customerName}',
			buyer_tel: '${Cvo.customerPhone}',
			m_redirect_url: 'https://www.yourdomain.com/payments/complete'
		}, function(rsp) {
			console.log(rsp);
			if (rsp.success) {
				var msg = '결제가 완료되었습니다.';
				msg += '고유ID : ' + rsp.imp_uid;
				msg += ', 상점 거래ID : ' + rsp.merchant_uid;
				msg += ', 결제 금액 : ' + rsp.paid_amount + '원';
				buyId = rsp.imp_uid;

				// 결제 이후
				var totData = new Object();
				var data = new Object();
				var dataList = new Array();

				// 사용자 id
				var customerId = $('.customerId').text().trim();

				var item_list = $('.bundle-info__item-list ');
				item_list.each(function(i) {
					console.log(buyId);
					var buyProducts = $('p');
					console.log(buyProducts.eq(i).text().trim());
					var buyAmount = $('.Amount');
					console.log(buyAmount.eq(i).text());
					var buyTotalPrice = $('.Unit_totalPrice');
					console.log(buyTotalPrice.eq(i).text().trim());
					var buyImage = $('.imagedata');
					console.log(buyImage.eq(i).val());
					var addressNo = $('#addressNo').val();
					console.log(addressNo);
					var productNo = $('.productNo');
					console.log(productNo.eq(i).val());
					var data = {
						"buyId": buyId,
						"buyProducts": buyProducts.eq(i).text().trim(),
						"buyDate": buyDate,
						"buyDelDate": deldate2,
						"buyAmount": buyAmount.eq(i).text(),
						"buyTotalPrice": buyTotalPrice.eq(i).text().trim(),
						"buyImage": buyImage.eq(i).val(),
						"addressNo": addressNo,
						"customerId": customerId,
						"productNo": productNo.eq(i).val()
					};
					dataList.push(data);
					totData["buyList"] = dataList;

				}); // end item_list
				var obj = JSON.stringify(totData);
				console.log(obj);

				$.ajax({
					type: 'post',
					url: 'buy',
					headers: {
						'Content-Type': 'application/json',
						'X-HTTP-Method-Override': 'POST'
					},
					data: obj,
					success: function(buyId) {
						location.href = "buyComplete?buyId=" + buyId;
					}
				}); // end ajax

				document.buy.submit();
			} else {
				var msg = '결제에 실패하였습니다.';
				msg += '에러내용 : ' + rsp.error_msg;
			}
			alert(msg);
		});
	} else {
		alert('배송지선택을 해주세요.');
	}

}); // end check_module click

}); // end document 

function chooseAddr() {
	var openWin;
	window.name = "parentForm";
	openWin = window.open("chooseAddr",
		"childForm", "width=500, height=500, resizable = no");
} // end chooseAddr

