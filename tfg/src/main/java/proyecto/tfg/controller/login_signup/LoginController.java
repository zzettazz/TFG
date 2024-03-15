package proyecto.tfg.controller.login_signup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import proyecto.tfg.config.Config;
import proyecto.tfg.domain.Usuario;
import proyecto.tfg.repository.UsuarioRepository;

@RequestMapping("/login")
@Controller
public class LoginController {

    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @GetMapping
    public String home(ModelMap m)
    {
        m.put("base_url",Config.base_url);
        return "login_signup/login";
    }

    @PostMapping("hacerLogin")
    public String hacerLogin(
        @RequestParam("usuario") String usuarioIntroducido,
        @RequestParam("contraseña") String contraseñaIntroducida,
        ModelMap m,
        HttpSession sesion
    )
    {
        Usuario usuario = usuarioRepository.findByNomUsuario(usuarioIntroducido);
        
        if ( usuario != null )
        {
            BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
            if (bcrypt.matches(contraseñaIntroducida, usuario.getContraseña()))
            {
                sesion.setAttribute("usuarioActual", usuarioIntroducido);
                m.put("usuarioActual",sesion.getAttribute("usuarioActual"));
                m.put("ws_url",Config.ws_url);
                m.put("base_url", Config.base_url);
                
                return "chat/chat";
            }
            else
            {
                m.put("mensaje", "Contraseña errónea");
                return "alerts/aviso";
            }
        }
        else
        {
            m.put("mensaje", "Usuario no encontrado");
            return "alerts/aviso";
        }
    }
}
