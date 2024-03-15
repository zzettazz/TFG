package proyecto.tfg.controller.login_signup;

import java.util.Random;

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
import proyecto.tfg.controller.email.EmailController;
import proyecto.tfg.domain.Usuario;
import proyecto.tfg.repository.UsuarioRepository;

@RequestMapping("/signup")
@Controller
public class SignupController
{
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @GetMapping
    public String home(ModelMap m)
    {
        m.put("base_url",Config.base_url);
        return "login_signup/signup";
    }

    @PostMapping("enviarVerificacion")
    public String signup(
        @RequestParam("nombre") String nombre,
        @RequestParam("apellidos") String apellidos,
        @RequestParam("email") String email,
        @RequestParam("fechaNacimiento") String fechaNacimiento,
        @RequestParam("genero") String genero,
        @RequestParam("idiomaPreferido") String idiomaPreferido,
        @RequestParam("nomUsuario") String nomUsuario,
        @RequestParam("contraseña") String contraseña,
        ModelMap m
    )
    {
        if (usuarioRepository.findByEmail(email) == null)
        {

            boolean codigoExistente = true;
            String codigoActivacion = "NULL";

            do
            {
                codigoActivacion = generarCodigoActivacion(Config.longitud_codigo_activacion);

                if (usuarioRepository.findByCodigoActivacion(codigoActivacion) == null)
                {
                    codigoExistente = false;

                    java.sql.Date fechaBD = java.sql.Date.valueOf(fechaNacimiento);

                    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
                    String contraCrypt = bCryptPasswordEncoder.encode(contraseña);
                    Usuario nuevoUsuario = new Usuario(nombre,apellidos,email,fechaBD,genero,idiomaPreferido,nomUsuario,contraCrypt,codigoActivacion);

                    usuarioRepository.save(nuevoUsuario);
                }
                
            }
            while (codigoExistente);

            EmailController.enviarGmailVerificacion(email, nombre, codigoActivacion);

            m.put("mensaje","Se ha enviado un email al correo facilitado para completar el registro.");
            m.put("base_url",Config.base_url);
            return "alerts/aviso";
        }
        else
        {
            m.put("mensaje","Ya hay un usuario registrado con esos datos");
            m.put("base_url",Config.base_url);
            return "alerts/aviso";
        }
    }

    @GetMapping("validarCuenta")
    public String validarCuenta(
        @RequestParam("email") String email,
        @RequestParam("codigoActivacion") String codigoActivacion,
        ModelMap m,
        HttpSession sesion
    )
    {
        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();

        Usuario usuario = usuarioRepository.findByEmail(email);

        if (usuario != null)
        {
            if ( (bcrypt.matches(usuario.getCodigoActivacion(), codigoActivacion)) && (!usuario.isVerificado()))
            {
                usuario.setVerificado(true);
    
                usuarioRepository.save(usuario);
    
                EmailController.enviarGmailBienvenida(email, usuario.getNombre());
    
                m.put("mensaje","Enhorabuena, la cuenta ha sido verificada");
                m.put("base_url",Config.base_url);
    
                sesion.setAttribute("usuarioActual", usuario.getNomUsuario());
    
                return "alerts/exito";
            }
            else if (usuario.isVerificado())
            {
                m.put("mensaje","La cuenta ya ha sido activada");
                m.put("base_url",Config.base_url);
                return "alerts/aviso";
            }
            else if (!bcrypt.matches(usuario.getCodigoActivacion(), codigoActivacion))
            {
                m.put("mensaje","Código de activación inválido");
                return "alerts/aviso";
            }
            else
            {
                m.put("mensaje","Código o email inválido");
                m.put("base_url",Config.base_url);
                return "alerts/aviso";
            }
        }
        else
        {
            m.put("mensaje","Usuario no encontrado");
            m.put("base_url",Config.base_url);
            return "alerts/aviso";
        }
    }

    public static String generarCodigoActivacion(int longitud) {
        
        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

        StringBuilder cadena = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < longitud; i++) {
            char caracter = caracteres.charAt(random.nextInt(caracteres.length()));
            cadena.append(caracter);
        }

        return cadena.toString();
    }

}
