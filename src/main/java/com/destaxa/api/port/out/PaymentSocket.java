package com.destaxa.api.port.out;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.destaxa.api.exception.PaymentTimeoutException;
import com.destaxa.api.infrastructure.protocol.ISO8583Request0200;
import com.destaxa.api.infrastructure.protocol.ISO8583Response0210;
import org.springframework.stereotype.Component;

@Component
public class PaymentSocket {

    private static final String HOST = "localhost";
    private static final int PORT = 12345;

    public ISO8583Response0210 sendPaymentRequest(ISO8583Request0200 isoRequest) {
    	
    	System.out.println("---PaymentSocket-ISO8583Request0200---------------" + isoRequest.getValorTransacao() + "----------PaymentSocket-ISO8583Request0200------------------");
    	
        ISO8583Response0210 isoResponse = null;
        
        try (Socket socket = new Socket(HOST, PORT);
             ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

            // Enviando a solicitação de pagamento
            out.writeObject(isoRequest);

            // Recebendo a resposta
            isoResponse = (ISO8583Response0210) in.readObject();
            
            System.out.println("---PaymentSocket-ISO8583Response0210---------------" + isoResponse.getCodigoResposta() + "----------PaymentSocket-ISO8583Response0210------------------");

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new PaymentTimeoutException("Error while communicating with the authorizer.");
        }
        return isoResponse;
    }
    
}