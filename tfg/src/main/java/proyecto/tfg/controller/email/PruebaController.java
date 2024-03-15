package proyecto.tfg.controller.email;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/email")
@Controller
public class PruebaController {
    
    @GetMapping("r")
    public String home()
    {
        return "email";
    }

    @PostMapping("enviarCorreo")
    public String enviarCorreo(
        @RequestParam("destinatario") String destinatario,
        @RequestParam("asunto") String asunto,
        @RequestParam("cuerpo") String cuerpo
    )
    {
        EmailController.enviarGmail(destinatario, asunto, cuerpo);
        return "redirect:/email/r";
    }
}
