package proyecto.tfg.controller.sesion;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import proyecto.tfg.config.Config;

@Controller
@RequestMapping("/principal")
public class PrincipalController {
    

    @GetMapping
    public static String iniciar(
        ModelMap m
    )
    {
        m.put("base_url", Config.base_url);
        return "principal";
    }

    @PostMapping("setSession")
    private void setSession(
        @RequestParam("usuario") String usuario,
        HttpSession session
    )
    {
        session.setAttribute("usuarioActual", usuario);
    }
}
