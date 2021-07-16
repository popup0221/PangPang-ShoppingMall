
$(document).ready(function() {
	// 배송 날짜
	var date = new Date();
	var month = date.getMonth() + 1;
	var day = date.getDate() + 1;
	var week = new Array('일요일', '월요일', '화요일', '수요일', '목요일', '금요일', '토요일');
	var deldate = week[date.getDay() + 1] + "(내일) "
		+ month + "월 " + day + "일";
	console.log(deldate);
	$('.deldate').html(deldate + '까지 도착');

	// 최대 수량 10개로 한정
	function check_plus() {
		btn_p = document.getElementById('btn_plus');
		
		// productAmount -> 수량(input박스 value)
		if ($('.productAmount').val() == 10) {
			btn_p.disabled = true;
		} else {
			btn_p.disabled = false;
		}
	}

	// 개수가 1개 이하일떄 minus 버튼 비활성화
	function check_minus() {
		btn_m = document.getElementById('btn_minus');
		
		// productAmount -> 수량(input박스 value)
		if ($('.productAmount').val() == 1) {
			btn_m.disabled = true;
		} else {
			btn_m.disabled = false;
		}
	}

	check_minus();
	var price = parseInt($('.productPrice').html());

	$('.btn_plus').click(function() { // plus 버튼 클릭					
		var amount = parseInt($('.productAmount').val()) + 1;
		console.log(amount);
		document.getElementById("productAmount").value = amount;
		check_minus();

		console.log(price);
		var changePrice = price * amount;
		console.log(changePrice);
		$('#productPrice').text(changePrice + "원");

		check_plus();
	}); // end btn_plus

	$('.btn_minus').click(function() { // minus 버튼 클릭			
		var amount = parseInt($('.productAmount').val()) - 1;
		console.log(amount);
		document.getElementById("productAmount").value = amount;
		check_minus();

		console.log(price);
		var changePrice = price * amount;
		console.log(changePrice);
		$('#productPrice').text(changePrice + "원");

		check_plus();
	}); // end btn_plus


	$('.btn_cart').click(function() {
		var image = $('.productImage').val();
		var name = $('.productName').val();
		var price = parseInt($('.Base_productPrice').val());
		var amount = parseInt($('.productAmount').val());
		var totalPrice = parseInt($('.productPrice').html());
		var key_localStorage = $('.productName').val();

		var url_productNo = $('.hid_no').val();
		var url_category = $('.hid_category').val();

		console.log(url_productNo);
		console.log(url_category);


		var obj = {
			'cartImage': image,
			'cartName': name,
			'cartPrice': price,
			'cartAmount': amount,
			'cartTotalprice': totalPrice,
			'productNo': url_productNo,
			'category': url_category
		}

		console.log(obj);
		var JSONObj = JSON.stringify(obj);

		$.ajax({
			type: 'post',
			url: 'cart?judge=cart',
			headers: {
				'Content-Type': 'application/json',
				'X-HTTP-Method-Override': 'POST'
			},
			data: JSONObj,
			success: function(result) {
				alert('장바구니 등록 성공!');
			}
		}); // end ajax
	}); // end btn_cart

	$('.btn_buy').click(function() {
		var image = $('.productImage').val();
		var name = $('.productName').val();
		var price = parseInt($('.Base_productPrice').val());
		var amount = parseInt($('.productAmount').val());
		var totalPrice = parseInt($('.productPrice').html());
		var key_localStorage = $('.productName').val();

		var url_productNo = $('.hid_no').val();
		var url_category = $('.hid_category').val();

		console.log(url_productNo);
		console.log(url_category);

		var obj = {
			'cartImage': image,
			'cartName': name,
			'cartPrice': price,
			'cartAmount': amount,
			'cartTotalprice': totalPrice,
			'productNo': url_productNo,
			'category': url_category
		};

		console.log(obj);
		var JSONObj = JSON.stringify(obj);

		$.ajax({
			type: 'post',
			url: 'cart?judge=buy',
			headers: {
				'Content-Type': 'application/json',
				'X-HTTP-Method-Override': 'POST'
			},
			data: JSONObj,
			success: function(result) {
				console.log(result);
				if (result.Amount == null && result.ProductNo == null) {
					location.href = "buy?ArrayNo=" + result;
				} else {
					var ProductNo = result.ProductNo;
					var Amount = result.Amount;
					location.href = "buy?ProductNo=" + ProductNo + "&Amount=" + Amount;
				}
			}
		}); // end ajax
	}); // end btn_buy
}); // end document