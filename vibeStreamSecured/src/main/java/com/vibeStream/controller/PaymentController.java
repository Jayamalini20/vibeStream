package com.vibeStream.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vibeStream.entities.Users;
import com.vibeStream.services.UserService;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.razorpay.Utils;

import jakarta.servlet.http.HttpSession;

@Controller
public class PaymentController {
	@Autowired
	UserService service;
	
	@GetMapping("/pay")
	public String pay()
	{
		System.out.println("Inside pay");
		return "pay";
	}

	@GetMapping("/payment-success")
	public String paymentSuccess() {
		System.out.println("Inside payment-success");
		Users u = service.getUser();
		u.setPremium(true);
		service.updateUser(u);
		return "login";
	}
	
	@GetMapping("/payment-failure")
	public String paymentFailure() {
		System.out.println("Inside payment-failure");
		return "customerHome";
	}

	
	@SuppressWarnings("finally")
	@PostMapping("/createOrder")
	@ResponseBody
	public String createOrder(HttpSession session)
	{
		System.out.println("Inside createOrder");
		int amount=799;
		Order order=null;
		try
		{
			RazorpayClient razorpay=new RazorpayClient("rzp_test_8KUbTHsvy5MlXr", "4RrE8hRsjD2QtwiEG6qSazE3");
			
			JSONObject orderRequest=new JSONObject();
			orderRequest.put("amount", amount*100);
			orderRequest.put("currency", "INR");
			orderRequest.put("receipt", "order_rcptid_11");
			
			order=razorpay.orders.create(orderRequest);
			
			

		} catch (RazorpayException e) {
			e.printStackTrace();
		}
		finally {
			return order.toString();
		}
		
	}
	
	
	@PostMapping("/verify")
	@ResponseBody
	public boolean verifyPayment(@RequestParam  String orderId, @RequestParam String paymentId, @RequestParam String signature) {
	    System.out.println("Inside verify");
		try {
	        // Initialize Razorpay client with your API key and secret
	        //RazorpayClient razorpayClient = new RazorpayClient("rzp_test_8KUbTHsvy5MlXr", "4RrE8hRsjD2QtwiEG6qSazE3");
	        // Create a signature verification data string
	        String verificationData = orderId + "|" + paymentId;

	        // Use Razorpay's utility function to verify the signature
	       
	        boolean isValidSignature = Utils.verifySignature(verificationData, signature, "4RrE8hRsjD2QtwiEG6qSazE3");

	        return isValidSignature;
	    } catch (RazorpayException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
}
