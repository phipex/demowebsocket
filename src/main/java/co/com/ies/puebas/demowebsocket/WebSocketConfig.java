package co.com.ies.puebas.demowebsocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {


    private final BinarySocketHandler binarySocketHandler;

    private final TextSocketHandler textSocketHandler;
    public WebSocketConfig(TextSocketHandler textSocketHandler, BinarySocketHandler binarySocketHandler) {
        this.textSocketHandler = textSocketHandler;
        this.binarySocketHandler = binarySocketHandler;
    }

    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(binarySocketHandler, "/name");
        registry.addHandler(textSocketHandler, "/name2");

    }
}
