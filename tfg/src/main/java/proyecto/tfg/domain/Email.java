package proyecto.tfg.domain;

import proyecto.tfg.config.Config;

public final class Email
{
    private String cuerpoMail;
    private String asunto;
    private String destinatario;


    public Email(String cuerpoMail, String asunto, String destinatario) {
        this.cuerpoMail = cuerpoMail;
        this.asunto = asunto;
        this.destinatario = destinatario;
    }


    public String getNOMBRE_EMISOR() {
        return Config.NOMBRE_EMISOR;
    }


    public String getCORREO_EMISOR() {
        return Config.CORREO_EMISOR;
    }


    public String getCLAVE_API() {
        return Config.CLAVE_API;
    }


    public String getCuerpoMail() {
        return cuerpoMail;
    }


    public void setCuerpoMail(String cuerpoMail) {
        this.cuerpoMail = cuerpoMail;
    }


    public String getAsunto() {
        return asunto;
    }


    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }


    public String getDestinatario() {
        return destinatario;
    }


    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    
}
