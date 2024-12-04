package com.destaxa.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.destaxa.api.domain.PaymentRequest;
import com.destaxa.api.domain.PaymentResponse;
import com.destaxa.api.service.PaymentService;

@RestController
@RequestMapping("/api")
public class PaymentController {
	
    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/authorization")
    public PaymentResponse authorizePayment(@RequestBody PaymentRequest paymentRequest) {
    	PaymentResponse response = paymentService.processPayment(paymentRequest);
        return response;
    }

}