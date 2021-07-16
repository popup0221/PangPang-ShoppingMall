package mall.spring.project.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import mall.spring.project.domain.CartVO;
import mall.spring.project.service.CartService;

@Controller
@RequestMapping(value = "/main")
public class CartController {
	private static final Logger logger = LoggerFactory.getLogger(CartController.class);

	@Autowired
	private CartService cartService;

	@GetMapping("cart")
	public void cartGET(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		String customerId = (String) session.getAttribute("customerId");
		logger.info("로그인된 아이디 : " + customerId);
		
		if (customerId == null) {
			logger.info("로그인 안됌");
			Cookie[] cookies = request.getCookies();

			if (cookies != null) {
				for (int i = 0; i < cookies.length; i++) {
					Cookie c = cookies[i];

					// 쿠키값을 가져온다
					customerId = c.getValue();
					logger.info(customerId);
				}
			}

			model.addAttribute("NotLogin", "NotLogin");
		} else {
			logger.info("로그인 됌");
		}

		List<CartVO> list = (List<CartVO>) cartService.read_cart(customerId);
		logger.info("list : " + list);
		model.addAttribute("list", list);
	}

	@PostMapping("cart")
	public ResponseEntity<Object> cartPOST(@RequestBody CartVO vo, HttpServletRequest request, HttpServletResponse response, String judge) throws IOException{
		logger.info("cartPOST() 호출");
		
		int result = 0;
		
		HttpSession session = request.getSession();
		String customerId = (String) session.getAttribute("customerId");
		logger.info("로그인된 아이디 : " + customerId);

		if (customerId == null) {
			// 로그인 X
			customerId = session.getId();
			logger.info(customerId);

			Cookie cookie = new Cookie("non_mem", customerId);
		}
		vo.setCustomerId(customerId);

		String cartName = vo.getCartName();
		CartVO overlap = cartService.read_cart_check(customerId, cartName);
		
		logger.info("judge : " + judge);
		
		if (overlap == null) {
			logger.info("중복 X -> insert");
			
			if(judge.equals("buy")) {
				logger.info("Go buy : " + vo.toString());
				cartService.create_and_select_seq(vo);
				logger.info("return : " + vo.getCartNo());
				result = vo.getCartNo();
				return new ResponseEntity<Object>(result, HttpStatus.OK);
			} else {
				result = cartService.create_cart(vo);
				logger.info("result : " + result);
				
				return new ResponseEntity<Object>(result, HttpStatus.OK);
			}
		} else {
			logger.info("중복 O -> update");
			int detail_Amount = vo.getCartAmount();
			int addAmount = detail_Amount + overlap.getCartAmount();
			int totalPrice = vo.getCartPrice() * addAmount;
			vo.setCartAmount(addAmount);
			vo.setCartTotalprice(totalPrice);

			result = cartService.update_cart(vo);
				
			if(judge.equals("buy")) {
				
				String Amount = vo.getCartAmount()+"";
				String ProductNo = vo.getProductNo();
				
				Map<String, String> overlap_result = new HashMap<String, String>();
				overlap_result.put("Amount", detail_Amount+"");
				overlap_result.put("ProductNo", ProductNo);
				
				return new ResponseEntity<Object>(overlap_result, HttpStatus.OK);
			} else {
				logger.info("result : " + result);
				return new ResponseEntity<Object>(result, HttpStatus.OK);
			}				
		}
	}
	
	@PostMapping("cart_update")
	public void cart_updatePOST(String name, String id, int amount, int totalPrice) {
		logger.info("cart_updatePOST() 호출");
		logger.info("name : " + name);
		logger.info("id : " + id);
		logger.info("amount : " + amount);
		logger.info("totalPrice : " + totalPrice);
		
		CartVO vo = new CartVO();
		vo.setCartName(name);
		vo.setCustomerId(id);
		vo.setCartAmount(amount);
		vo.setCartTotalprice(totalPrice);
		
		cartService.update_cart(vo);
	}
	
	@PostMapping("cart_delete")
	public void cart_deletePOST(String customerId, String name) {
		logger.info("cart_deletePOST() 호출");
		logger.info("customerId : " + customerId);
		logger.info("name : " + name);
		
		cartService.delete_cart(customerId, name);
	}
	
	@PostMapping("cart_delete_all")
	public void cart_delete_all(String customerId) {
		logger.info("cart_delete_all() 호출");
		logger.info("customerId : " + customerId);
		
		cartService.delete_cart_all(customerId);
	}
}