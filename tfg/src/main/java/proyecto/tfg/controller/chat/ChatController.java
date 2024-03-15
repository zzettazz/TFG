package proyecto.tfg.controller.chat;

import java.util.Map;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;
import proyecto.tfg.domain.Mensaje;
import proyecto.tfg.domain.Usuario;
import proyecto.tfg.repository.MensajeRepository;
import proyecto.tfg.repository.UsuarioRepository;

@RequestMapping("/chat")
@Controller
public class ChatController {

    @Autowired
    private MensajeRepository mensajeRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public String showChatPage(
        HttpSession sesion,
        ModelMap m
    ) {
        if (sesion.getAttribute("usuarioActual") == null)
        {
            m.put("mensaje", "Usuario no definido");
            return "alerts/aviso";
        }
        else
        {
            m.put("usuarioActual", sesion.getAttribute("usuarioActual"));
            return "chat/chat";
        }
    }

    @PostMapping("saveMessage")
    @ResponseBody
    public void saveMessage(
        @RequestBody Map<String, Object> datos
    )
    {
        String nomEmisor = (String) datos.get("emisor");
        String nomReceptor = (String) datos.get("receptor");
        String mensaje = (String) datos.get("mensaje");
        
        Usuario emisor = usuarioRepository.findByNomUsuario(nomEmisor);
        Usuario receptor = usuarioRepository.findByNomUsuario(nomReceptor);

        if (emisor == null || receptor == null || mensaje == null)
        {
            System.err.println("Error en la recepción de los datos");
        }
        else
        {
            Mensaje mens = new Mensaje(emisor.getId(), receptor.getId(), mensaje, new Date());
            mensajeRepository.save(mens);
        }
    }
}
