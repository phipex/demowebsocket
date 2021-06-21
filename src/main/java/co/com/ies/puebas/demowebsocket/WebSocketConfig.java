package co.com.ies.puebas.demowebsocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {


    private final BinarySocketHandler webSocketHandler;

    private final TextSocketHandler textSocketHandler;
    public WebSocketConfig(TextSocketHandler textSocketHandler) {
        this.textSocketHandler = textSocketHandler;
        webSocketHandler = new BinarySocketHandler();
    }

    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(webSocketHandler, "/name");
        registry.addHandler(textSocketHandler, "/name2");

    }
}
