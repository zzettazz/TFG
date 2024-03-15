package proyecto.tfg.controller.sesion;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/sesion")
@Controller
public class SessionController {

    public static boolean sesionIniciada;

    @PostMapping("/setSession")
    public static String setSession(
        HttpSession session,
        @RequestParam("usuario") String usuarioIntroducido)
    {
        session.setAttribute("usuarioActual", usuarioIntroducido);
        return "redirect:/sesion";
    }

    @GetMapping
    public static String iniciar( HttpSession session, ModelMap m)
    {
        if (session.getAttribute("usuarioActual") == null || session.getAttribute("usuario") == "")
        {
            return "redirect:/principal";
        }
        else
        {
            m.put("usuarioActual" , session.getAttribute("usuarioActual"));
            return "sesion";
        }
    }
}
