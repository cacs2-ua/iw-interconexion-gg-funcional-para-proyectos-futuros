package tpvv.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "estados_incidencia")
public class EstadoIncidencia implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String nombre;

    @OneToMany(mappedBy = "estado", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    Set<Incidencia> incidencias = new HashSet<>();

    public EstadoIncidencia() {}

    public EstadoIncidencia(String nombre) {
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Set<Incidencia> getIncidencias() {
        return incidencias;
    }

    public void addIncidencia(Incidencia incidencia) {
        if (incidencias.contains(incidencia)) return;
        incidencias.add(incidencia);
        if (incidencia.getEstado() != this) {
            incidencia.setEstado(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EstadoIncidencia that = (EstadoIncidencia) o;

        // Si ambos objetos tienen un ID no nulo, comparamos por ID
        if (this.id != null && that.id != null) {
            return Objects.equals(this.id, that.id);
        }

        // Si no se pueden comparar por ID, consideramos que son diferentes
        return false;
    }

    @Override
    public int hashCode() {
        // Generamos el hashCode basado únicamente en el ID
        return Objects.hash(id);
    }
}
