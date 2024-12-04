package com.destaxa.api.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.destaxa.api.domain.PaymentRequest;
import com.destaxa.api.domain.PaymentResponse;
import com.destaxa.api.infrastructure.protocol.ISO8583Request0200;
import com.destaxa.api.infrastructure.protocol.ISO8583Response0210;
import com.destaxa.api.port.out.PaymentSocket;

@Service
public class PaymentService {

    @Autowired
    private PaymentSocket paymentSocket;

    public PaymentResponse processPayment(PaymentRequest paymentRequest) {
        // Convertendo PaymentRequest para ISO8583Request0200
        ISO8583Request0200 isoRequest = convertToISO8583Request(paymentRequest);
        
        // Enviando a solicitação via PaymentSocket
        ISO8583Response0210 isoResponse = paymentSocket.sendPaymentRequest(isoRequest);
        
        // Convertendo ISO8583Response0210 para PaymentResponse
        return convertToPaymentResponse(isoResponse);
    }

    private ISO8583Request0200 convertToISO8583Request(PaymentRequest paymentRequest) {
        ISO8583Request0200 isoRequest = new ISO8583Request0200();
        isoRequest.setNumeroCartao(paymentRequest.getCardNumber());
        isoRequest.setValorTransacao(String.valueOf(paymentRequest.getValue()));
        isoRequest.setNumeroParcelas(String.valueOf(paymentRequest.getInstallments()));
        isoRequest.setCodigoSeguranca(paymentRequest.getCvv());
        isoRequest.setDataVencimentoCartao(paymentRequest.getExpMonth() + paymentRequest.getExpYear());
        return isoRequest;
    }

    private PaymentResponse convertToPaymentResponse(ISO8583Response0210 isoResponse) {
        PaymentResponse paymentResponse = new PaymentResponse();
        paymentResponse.setPaymentId(UUID.randomUUID().toString());
        paymentResponse.setValue(Double.parseDouble(isoResponse.getValorTransacao()));
        paymentResponse.setResponseCode(isoResponse.getCodigoResposta());
        paymentResponse.setAuthorizationCode(isoResponse.getCodigoAutorizacao());
        paymentResponse.setTransactionDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        paymentResponse.setTransactionHour(LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        return paymentResponse;
    }
}
