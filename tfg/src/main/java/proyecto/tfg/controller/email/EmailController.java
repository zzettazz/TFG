package proyecto.tfg.controller.email;

import java.util.Properties;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import proyecto.tfg.config.Config;
import proyecto.tfg.domain.Email;

import javax.mail.Message;

public class EmailController {

    public static void enviarGmail(String destinatario, String asunto, String cuerpo)
    {
        Email email = new Email(cuerpo,asunto,destinatario);
        Properties props = System.getProperties();

        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");

        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);

        try
        {
            message.setFrom(new InternetAddress(email.getCORREO_EMISOR(), email.getNOMBRE_EMISOR()));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));

            message.setSubject(email.getAsunto());
            message.setContent(email.getCuerpoMail(), "text/html");
            //message.setText(email.getCuerpoMail());
            Transport transport = session.getTransport("smtp");
            transport.connect("smtp.gmail.com", email.getCORREO_EMISOR(), email.getCLAVE_API());
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        }
        catch (Exception e)
        {
            System.out.println("Error encontrado: "+e);
        }
    }

    public static void enviarGmailBienvenida(String emailDestinatario, String nombreDestinatario)
    {
        String asunto = ("¡Bienvenido a " + Config.nombreAPP + "!");

        String cuerpo = "<html>\n" +
                            "<head>\n" +
                            "  <style>\n" +
                            "    body {\n" +
                            "      font-family: Arial, sans-serif;\n" +
                            "      font-size: 2vh;\n" +
                            "    }\n" +
                            "    .container {\n" +
                            "      width: 80%;\n" +
                            "      margin: 0 auto;\n" +
                            "      padding: 20px;\n" +
                            "      border: 1px solid #ddd;\n" +
                            "      border-radius: 5px;\n" +
                            "      background-color: #f9f9f9;\n" +
                            "    }\n" +
                            "    .button {\n" +
                            "      display: inline-block;\n" +
                            "      padding: 10px 20px;\n" +
                            "      margin: 10px 0px;\n" +
                            "      border-radius: 5px;\n" +
                            "      background-color: #4CAF50;\n" +
                            "      color: white;\n" +
                            "      text-decoration: none;\n" +
                            "    }\n" +
                            "    img {"+
                            "       height: 75px;"+
                            "        border-radius: 25px;"+
                            "        position: absolute;"+
                            "        top: 20px;"+
                            "        right: 80px;"+
                            "    }"+
                            "  </style>\n" +
                            "</head>\n" +
                            "<body>\n" +
                            "  <div class=\"container\">\n" +
                            "    <h2>¡Bienvenido "+nombreDestinatario+"!</h2>\n" +
                           "<br/>Estamos encantados de tenerte con nosotros y esperamos que disfrutes de la experiencia que hemos creado para ti.<br/>"+

                            "Gracias por confiar en nosotros y por ser parte de esta emocionante aventura. Estamos aquí para brindarte un espacio único donde puedas conocer, aprender y organizar eventos con otros miembros de la comunidad.<br/><br/>"+
                    
                            "Si tienes alguna pregunta o sugerencia, no dudes en ponerte en contacto con nosotros. Estamos aquí para ayudarte en todo lo que necesites.<br/><br/>"+
                    
                            "¡Gracias de nuevo por unirte a nosotros!<br/><br/>"+
                    
                            "<strong>Atentamente,<br/>"+
                            "El Equipo de "+Config.nombreAPP+".</strong>"+
            "  </div>\n" +
            "</body>\n" +
        "</html>";

        enviarGmail(emailDestinatario, asunto, cuerpo);
    }

    public static void enviarGmailVerificacion(String emailDestinatario, String nombreDestinatario, String codigoActivacion)
    {
        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
        String codAct = bcrypt.encode(codigoActivacion);
        
        String asunto = ("¡Ya casi estás!");
        String cuerpo = "<html>\n" +
                            "<head>\n" +
                            "  <style>\n" +
                            "    body {\n" +
                            "      font-family: Arial, sans-serif;\n" +
                            "      font-size: 2vh;\n" +
                            "    }\n" +
                            "    .container {\n" +
                            "      width: 80%;\n" +
                            "      margin: 0 auto;\n" +
                            "      padding: 20px;\n" +
                            "      border: 1px solid #ddd;\n" +
                            "      border-radius: 5px;\n" +
                            "      background-color: #f9f9f9;\n" +
                            "    }\n" +
                            "    .button {\n" +
                            "      display: inline-block;\n" +
                            "      padding: 10px 20px;\n" +
                            "      margin: 10px 0px;\n" +
                            "      border-radius: 5px;\n" +
                            "      background-color: #4CAF50;\n" +
                            "      color: white;\n" +
                            "      text-decoration: none;\n" +
                            "    }\n" +
                            "  </style>\n" +
                            "</head>\n" +
                            "<body>\n" +
                            "  <div class=\"container\">\n" +
                            "    <h2>Hola "+nombreDestinatario+",</h2>\n" +
                            "    <p>Gracias por darte de alta en nuestra plataforma. Para completar el proceso de registro, haz clic en el botón de abajo para validar tu cuenta:</p>\n" +
                            "    <a href=\""+Config.base_url+"/signup/validarCuenta?codigoActivacion="+codAct+"&email="+emailDestinatario+"\" class=\"button\" style=\"text-decoration: none; color: white;\">Validar cuenta</a>\n" +
                            "    <p>Si no has sido tú quien se ha dado de alta en nuestra plataforma, por favor ignora este correo electrónico.</p>\n" +
                "    <strong><p>Atentamente,</p>\n" +
                "    <p>El equipo de "+Config.nombreAPP+"</p></strong>\n" +
            "  </div>\n" +
            "</body>\n" +
        "</html>";
        
        /*
        String cuerpo = ("<div><h1 style=\"color: blue\";>¡¡Bienvenido "+nombreDestinatario+"!!<h1></div><br/>"+
        "<div style=\"font-size: 1.7vh;\">Ya no te queda nada para poder acceder a <strong>"+ Config.nombreAPP + "</strong>.<br/>"+
        "Si no eres la persona a la que debería ir dirigido este correo o no has solicitado darte de alta en <strong>"+Config.nombreAPP+"</strong>, por favor, elimina este correo.<br/>"+
        "Sino, por favor accede mediante este botón para validar tu cuenta:<br/><br/>"+
        "<a style=\"cursor: pointer;\"href=\""+Config.base_url+"/signup/validarCuenta?codigoActivacion="+codAct+"&email="+emailDestinatario+"\"><button style=\"background-color: green; color: white; font-size: 3vh; border: 3px solid black; border-radius: 15px; padding: 1vh; cursor: pointer;\">Validar cuenta</button></a><br/><br/>"+

        "<form action=\""+Config.base_url+"/signup/validarCuenta\" method=\"POST\">"+
        "<input type=\"hidden\" name=\"email\" value=\""+emailDestinatario+"\">"+
        "<input type=\"hidden\" name=\"codigoActivacion\" value=\""+codAct+"\">"+
        "<input type=\"submit\" value=\"Validar Cuenta\" style=\\\"background-color: green; color: white; font-size: 3vh; border: 3px solid black; border-radius: 15px; padding: 1vh;>"+
        "</form>"+
        
        "Atentamente,<br/>"+
        "El Equipo de <strong>"+Config.nombreAPP+"</strong>.</div>");
        */
        enviarGmail(emailDestinatario, asunto, cuerpo);
    }

}

