$(document).ready(function() {
	$('#search').keypress(function(e) {
		if (e.which == 13) {
			$('.btn_search').click();
			return false;
		}
	})

	$('#btn_login').click(function() {
		location.href = "../member/login";
	}); // end btn_login_click()
	$('#btn_logout').click(function() {
		location.href = "../member/logout";
	}); // end btn_logout_click()

	$('#btn_myPage').click(function() {
		location.href = "../main/myPage";
	}); // end btn_myPage_click()

	$('#btn_cart').click(function() {
		location.href = "../main/cart";
	}); // end btn_myPage_click()
}); // end document