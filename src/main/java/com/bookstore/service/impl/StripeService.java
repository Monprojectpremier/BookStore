package com.bookstore.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.stripe.Stripe;
import com.stripe.model.Charge;
import com.stripe.model.Coupon;
import com.stripe.model.Customer;
import com.stripe.model.Refund;
import com.stripe.model.Subscription;





@Service
public class StripeService {

	@Value("${stripe.key.secret}")
	private String API_SECET_KEY;
	
	

	public StripeService() {

	}


	
	public String createCharge(String name, String email, String token, int amount) {
		
		String chargeId = null;
		
		try {
			Stripe.apiKey = API_SECET_KEY;
			
			Map<String, Object> chargeParams = new HashMap<>();
			chargeParams.put("description","Charge for "+email);
			chargeParams.put("currency","usd");
			chargeParams.put("amount",amount);
			chargeParams.put("source",token);
			
			Charge charge = Charge.create(chargeParams);
			
		    chargeId = charge.getId();
		    
		    
		} catch (Exception e) {
			e.printStackTrace();
		}
		return chargeId;
	}


}
