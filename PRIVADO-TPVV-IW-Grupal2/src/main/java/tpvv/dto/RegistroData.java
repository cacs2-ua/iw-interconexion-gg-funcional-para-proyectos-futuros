package tpvv.dto;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Objects;

public class RegistroData {

    private Long id;
    private String email;
    private String nombre;
    private String contrasenya;
    private Long comercioId;
    private Timestamp fechaAlta = Timestamp.from(Instant.now());

    // Campo adicional para capturar el tipo de usuario desde el formulario
    private Long tipoId;

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasenya() {
        return contrasenya;
    }

    public void setContrasenya(String contrasenya) {
        this.contrasenya = contrasenya;
    }

    public Long getTipoId() {
        return tipoId;
    }

    public void setTipoId(Long tipoId) {
        this.tipoId = tipoId;
    }

    public Long getComercioId() {
        return comercioId;
    }

    public void setComercioId(Long comercioId) {
        this.comercioId = comercioId;
    }

    public Timestamp getFechaAlta() {return fechaAlta;}

    public void setFechaAlta(Timestamp fechaAlta) {this.fechaAlta = fechaAlta;}


    // equals, hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RegistroData that)) return false;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
