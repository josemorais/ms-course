package com.dev.hrpayroll.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.hrpayroll.entities.Payment;
import com.dev.hrpayroll.entities.Worker;
import com.dev.hrpayroll.feignclients.WorkerFeignClient;

@Service
public class PaymentService {

	@Autowired
	private WorkerFeignClient workerFeignClient;

	public Payment getPayment(Long workedId, Integer days) {

		Worker worker = workerFeignClient.findById(workedId).getBody();
		return new Payment(worker.getName(), worker.getDailyIncome(), days);
	}

}
