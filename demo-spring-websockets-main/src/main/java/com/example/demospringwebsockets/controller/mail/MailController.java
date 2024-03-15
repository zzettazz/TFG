package com.example.demospringwebsockets.controller.mail;
import java.io.UnsupportedEncodingException;
/*
Creado por José Manuel Alarcón, de campusMVP.es
Artículo con la explicación completa: https://www.campusmvp.es/recursos/post/como-enviar-correo-electronico-con-java-a-traves-de-gmail.aspx
*/
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.Session;
import javax.mail.Transport;

class Mailcontroller {
  public static void main(String[] args) {
    // ANTES DE EJECUTARLO CAMBIA ESTOS VALORES
    // Y CAMBIA EL REMITENTE Y LA CLAVE DE APLICACIÓN EN LA FUNCIÓN enviarConGMail

    // A quién le quieres escribir.
    String destinatario = "junquerdavid@gmail.com";
    String asunto = "Holiii";
    String cuerpo = "Que tal estamos";
    // Envio de correo
    enviarConGMail(destinatario, asunto, cuerpo);
    System.out.println("¡Correo enviado!");
  }

  private static void enviarConGMail(String destinatario, String asunto, String cuerpo) {
    // La dirección de correo de envío
    String remitente = "junquerdaviddaw@gmail.com";
    // La clave de aplicación obtenida según se explica aquí:
    // https://www.campusmvp.es/recursos/post/como-enviar-correo-electronico-con-java-a-traves-de-gmail.aspx
    String claveemail = "hnor ysui rocd fqrz";

    Properties props = System.getProperties();

    /*
    props.put("mail.smtp.host", "smtp.gmail.com"); // El servidor SMTP de Google
    props.put("mail.smtp.user", remitente);
    props.put("mail.smtp.clave", claveemail); // La clave de la cuenta
    props.put("mail.smtp.auth", "true"); // Usar autenticación mediante usuario y clave
    props.put("mail.smtp.starttls.enable", "true"); // Para conectar de manera segura al servidor SMTP
    props.put("mail.smtp.port", "587"); // El puerto SMTP seguro de Google
    */

    props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.port", "465");
    props.put("mail.smtp.socketFactory.port", "465");
    props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
    props.put("mail.smtp.ssl.protocols", "TLSv1.2");

    Session session = Session.getDefaultInstance(props);
    MimeMessage message = new MimeMessage(session);

    try {
      message.setFrom(new InternetAddress(remitente, "Pepito"));
      message.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario)); // Se podrían añadir varios de
                                                                                         // la misma manera
      message.setSubject(asunto);
      message.setText(cuerpo);
      Transport transport = session.getTransport("smtp");
      transport.connect("smtp.gmail.com", remitente, claveemail);
      transport.sendMessage(message, message.getAllRecipients());
      transport.close();
    } catch (MessagingException | UnsupportedEncodingException me) {
      me.printStackTrace(); // Si se produce un error
    }
  }
}