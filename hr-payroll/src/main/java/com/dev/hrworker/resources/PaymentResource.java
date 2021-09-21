package com.dev.hrworker.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.hrworker.entities.Payment;
import com.dev.hrworker.services.PaymentService;

@RestController
@RequestMapping(value = "/payments")
public class PaymentResource {

	@Autowired
	private PaymentService service;
	
	@GetMapping(value = "/{workedId}/days/{days}")
	public ResponseEntity<Payment> getPayment(@PathVariable Long workedId, @PathVariable Integer days) {
		Payment payment = service.getPayment(workedId, days);
		return ResponseEntity.ok(payment);
	}
}
