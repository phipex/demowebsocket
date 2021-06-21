package co.com.ies.puebas.demowebsocket;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class TextSocketHandler extends TextWebSocketHandler {

    List<WebSocketSession> webSocketSessionList = new ArrayList<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        super.afterConnectionEstablished(session);
        System.out.println("session = " + session);
        webSocketSessionList.add(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);
        System.out.println("status = " + status);
        System.out.println("session = " + session);
        webSocketSessionList.remove(session);
    }

    public void sendBroadcast(String mensaje){
        byte[] bytes = mensaje.getBytes();
        sendBroadcast(bytes);
    }

    public void sendBroadcast(byte[] bytes) {
        webSocketSessionList.forEach(webSocketSession -> {
            try {
                System.out.println("session = " + webSocketSession);
                webSocketSession.sendMessage(new TextMessage(bytes));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
