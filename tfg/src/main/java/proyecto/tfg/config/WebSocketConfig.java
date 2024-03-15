package proyecto.tfg.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import proyecto.tfg.service.ChatHandler;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

	@Autowired
	private ChatHandler chatHandler;

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		// PATH: ENLACE DE ENVIO DEL MENSAJE DESDE JS (SI EN JS PONE ejemplo/chatejemplo aquí se pone /chatejemplo)
		registry.addHandler(chatHandler, "/chatmsg");
	}

}
