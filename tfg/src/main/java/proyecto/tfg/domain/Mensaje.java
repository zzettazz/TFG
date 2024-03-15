package proyecto.tfg.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Mensaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long idEmisor;
    private Long idReceptor;
    private String contenido;

    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date fecha;

    public Mensaje(Long idEmisor, Long idReceptor, String contenido, Date fecha) {
        this.idEmisor = idEmisor;
        this.idReceptor = idReceptor;
        this.contenido = contenido;
        this.fecha = fecha;
    }

    public Mensaje() {}

    public Long getId() {
        return id;
    }

    public Long getIdEmisor() {
        return idEmisor;
    }

    public void setIdEmisor(Long idEmisor) {
        this.idEmisor = idEmisor;
    }

    public Long getIdReceptor() {
        return idReceptor;
    }

    public void setIdReceptor(Long idReceptor) {
        this.idReceptor = idReceptor;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
    
    
}
