package co.com.ies.puebas.demowebsocket;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MessageController {

	private final BinarySocketHandler binaryWebSocketHandler;
	private final TextSocketHandler textSocketHandler;

	public MessageController(BinarySocketHandler binaryWebSocketHandler, TextSocketHandler textSocketHandler) {
		this.binaryWebSocketHandler = binaryWebSocketHandler;
		this.textSocketHandler = textSocketHandler;
	}

	@RequestMapping("/")
	public String home() {
		return "index";
	}

	@PostMapping(path = "api/prueba", produces = "application/json")
	public ResponseEntity<Objeto> getBook(@RequestBody String mensaje) {
		String repuesta = "Mensaje Enviado: " + mensaje;
		Objeto payload = new Objeto(repuesta);
		binaryWebSocketHandler.sendBroadcast(mensaje);
		textSocketHandler.sendBroadcast(mensaje);
		return ResponseEntity.of(Optional.of(payload));
	}

	public static class Objeto implements Serializable {
		private String mensaje;
		private Integer numero;

		public Objeto() {
		}

		public Objeto(String mensaje) {
			this.mensaje = mensaje;
			this.numero = 0;
		}

		public Objeto(String mensaje, Integer numero) {
			this.mensaje = mensaje;
			this.numero = numero;
		}

		public String getMensaje() {
			return mensaje;
		}

		public void setMensaje(String mensaje) {
			this.mensaje = mensaje;
		}

		public Integer getNumero() {
			return numero;
		}

		public void setNumero(Integer numero) {
			this.numero = numero;
		}
	}

}
