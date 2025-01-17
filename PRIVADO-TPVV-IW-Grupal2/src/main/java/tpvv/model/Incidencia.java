package tpvv.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Entity
@Table(name = "incidencias")
public class Incidencia implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;

    @NotNull
    private String titulo;

    @NotNull
    private String descripcion;

    private int valoracion;

    private String razon_valoracion;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "usuario_comercio_id", nullable = false)
    private Usuario usuarioComercio;


    @ManyToOne
    @JoinColumn(name = "usuario_tecnico_id")
    private Usuario usuarioTecnico;

    @OneToMany(mappedBy = "incidencia", cascade = CascadeType.ALL, orphanRemoval = true)
    Set<Mensaje> mensajes = new HashSet<>();


    @OneToOne
    @JoinColumn(name = "pago_id")
    private Pago pago;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "estado_id", nullable = false)
    private EstadoIncidencia estado;

    public Incidencia() {}

    public Incidencia(String titulo) {
        this.titulo = titulo;
        this.descripcion = "default";
        Usuario usuario_comercio = new Usuario("email", "nombre", "contrasenya", new Comercio("nombre"));
        this.setUsuarioComercio(usuario_comercio);
        Usuario usuario_tecnico = new Usuario("email", "nombre", "contrasenya", new Comercio("nombre"));
        this.setUsuarioTecnico(usuario_tecnico);
        this.fecha = Date.from(LocalDate.of(2000, 12, 12).atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public Incidencia(String titulo, String descripcion, Usuario usuarioComercio, Usuario usuarioTecnico) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.setUsuarioComercio(usuarioComercio);
        this.setUsuarioTecnico(usuarioTecnico);
        this.fecha = Date.from(LocalDate.of(2000, 12, 12).atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getValoracion() {
        return valoracion;
    }

    public void setValoracion(int valoracion) {
        this.valoracion = valoracion;
    }

    public String getRazon_valoracion() {
        return razon_valoracion;
    }

    public void setRazon_valoracion(String razon_valoracion) {
        this.razon_valoracion = razon_valoracion;
    }

    public Usuario getUsuarioComercio() {
        return usuarioComercio;
    }


    public void setUsuarioComercio(Usuario usuario_comercio) {
        // Si el nuevo usuario_comercio es el mismo que el actual, no hace nada
        if (this.usuarioComercio == usuario_comercio || usuario_comercio == null) {
            return;
        }

        // Si ya tiene un usuario_comercio, lo desvincula de la lista de incidencias_comercio de ese usuario_comercio
        if (this.usuarioComercio != null) {
            this.usuarioComercio.getIncidencias_comercio().remove(this);
        }

        // Asigna el nuevo usuario_comercio
        this.usuarioComercio = usuario_comercio;

        // Si el usuario_comercio no es nulo, lo añade a la lista de incidencias_comercio de ese usuario_comercio
        if (!usuario_comercio.getIncidencias_comercio().contains(this)) {
            usuario_comercio.addIncidencia_comercio(this);
        }
    }


    public Usuario getUsuarioTecnico() {
        return usuarioTecnico;
    }

    public void setUsuarioTecnico(Usuario usuario_tecnico) {
        // Si el nuevo usuario_tecnico es el mismo que el actual, no hace nada
        if (this.usuarioTecnico == usuario_tecnico || usuario_tecnico == null) {
            return;
        }

        // Si ya tiene un usuario_tecnico, lo desvincula de la lista de incidencias_tecnico de ese usuario_tecnico
        if (this.usuarioTecnico != null) {
            this.usuarioTecnico.getIncidencias_tecnico().remove(this);
        }

        // Asigna el nuevo usuario_tecnico
        this.usuarioTecnico = usuario_tecnico;

        // Si el usuario_tecnico no es nulo, lo añade a la lista de incidencias_tecnico de ese usuario_tecnico
        if (!usuario_tecnico.getIncidencias_tecnico().contains(this)) {
            usuario_tecnico.addIncidencia_tecnico(this);
        }
    }


    public Set<Mensaje> getMensajes() {
        return mensajes;
    }

    public void addMensaje(Mensaje mensaje) {
        if (mensajes.contains(mensaje)) return;
        mensajes.add(mensaje);
        if (mensaje.getIncidencia() != this) {
            mensaje.setIncidencia(this);
        }
    }

    public Pago getPago() {
        return pago;
    }

    public void setPago(Pago pago) {
        if (this.pago == pago) {
            return; // No hacer nada si es el mismo pago
        }

        // Desvincular el pago anterior si existe
        if (this.pago != null) {
            Pago previousPago = this.pago;
            this.pago = null; // Evita la llamada recursiva
            previousPago.setIncidencia(null);
        }

        this.pago = pago;

        // Vincular la relación inversa
        if (pago != null && pago.getIncidencia() != this) {
            pago.setIncidencia(this);
        }
    }

    public EstadoIncidencia getEstado() {
        return estado;
    }

    public void setEstado(EstadoIncidencia estado) {
        if (this.estado == estado || estado == null) {
            return; // No hacer nada si es el mismo estado
        }

        // Desvincular el estado anterior si existe
        if (this.estado != null) {
            this.estado.getIncidencias().remove(this);
        }

        // Asignar el nuevo estado
        this.estado = estado;

        // Vincular la relación inversa
        if (!estado.getIncidencias().contains(this)) {
            estado.addIncidencia(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Incidencia that = (Incidencia) o;

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
