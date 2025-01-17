package tpvv.dto;

import java.util.Objects;

public class TarjetaPagoData {

    private Long id;
    private String numeroTarjeta;

    // MODIFICADO: Antes int, ahora String
    private String cvc;

    // MODIFICADO: Antes Date, ahora String
    private String fechaCaducidad;

    private String nombre;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public void setNumeroTarjeta(String numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    // MODIFICADO: Getter/Setter cvc como String
    public String getCvc() {
        return cvc;
    }

    public void setCvc(String cvc) {
        this.cvc = cvc;
    }

    // MODIFICADO: Getter/Setter fechaCaducidad como String
    public String getFechaCaducidad() {
        return fechaCaducidad;
    }

    public void setFechaCaducidad(String fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // equals, hashCode

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TarjetaPagoData that)) return false;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
