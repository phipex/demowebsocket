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
        System.out.println("afterConnectionEstablished::session = " + session);
        webSocketSessionList.add(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);
        System.out.println("afterConnectionClosed::status = " + status);
        System.out.println("afterConnectionClosed::session = " + session);
        webSocketSessionList.remove(session);
    }

    public void sendBroadcast(String mensaje){
        byte[] bytes = mensaje.getBytes();
        sendBroadcast(bytes);
    }

    public void sendBroadcast(byte[] bytes) {
        System.out.println(bytes);
        webSocketSessionList.forEach(webSocketSession -> {
            try {
                System.out.println("sendBroadcast::session = " + webSocketSession);
                webSocketSession.sendMessage(new TextMessage(bytes));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
	public void handleTextMessage(WebSocketSession session, TextMessage message)
			throws InterruptedException, IOException {

		String payload = message.getPayload();
		webSocketSessionList.forEach(webSocketSession -> {
            if(!webSocketSession.getId().equals(session.getId())){
                try {
                    
                    System.out.println("sendBroadcast::session = " + webSocketSession);
                    webSocketSession.sendMessage(new TextMessage(payload.getBytes()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
	}
}
