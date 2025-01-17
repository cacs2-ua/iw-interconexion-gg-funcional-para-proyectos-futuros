package tpvv.dto;

import java.util.Objects;

/**
 * Ahora 'fecha' será String y 'importe' será String.
 * Se quitan los imports de Date y double, ya no se usan aquí.
 */
public class PedidoCompletoRequest {

    private Long id;
    private Long pagoId;
    private Long pedidoId;
    private String ticketExt;

    private String fecha;

    private String fechaPedido;

    private String importe;

    private String estadoPago;
    public String razonEstadoPago;
    private String comercioNombre;
    private String cvcTarjeta;
    private String fechaCaducidadTarjeta;
    private String nombreTarjeta;
    private String numeroTarjeta;

    // Constructor vacío
    public PedidoCompletoRequest() {}

    // Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPagoId() {
        return pagoId;
    }

    public void setPagoId(Long pagoId) {
        this.pagoId = pagoId;
    }

    public Long getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(Long pedidoId) {
        this.pedidoId = pedidoId;
    }

    public String getTicketExt() {
        return ticketExt;
    }

    public void setTicketExt(String ticketExt) {
        this.ticketExt = ticketExt;
    }

    // MODIFICADO: ahora es String
    public String getFecha() {
        return fecha;
    }

    // MODIFICADO: ahora recibe String
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(String fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    // MODIFICADO: ahora es String
    public String getImporte() {
        return importe;
    }

    // MODIFICADO: ahora recibe String
    public void setImporte(String importe) {
        this.importe = importe;
    }

    public String getEstadoPago() {
        return estadoPago;
    }

    public void setEstadoPago(String estadoPago) {
        this.estadoPago = estadoPago;
    }

    public String getRazonEstadoPago() {
        return razonEstadoPago;
    }

    public void setRazonEstadoPago(String razonEstadoPago) {
        this.razonEstadoPago = razonEstadoPago;
    }

    public String getComercioNombre() {
        return comercioNombre;
    }

    public void setComercioNombre(String comercioNombre) {
        this.comercioNombre = comercioNombre;
    }

    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public void setNumeroTarjeta(String numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    public String getCvcTarjeta() {
        return cvcTarjeta;
    }

    public void setCvcTarjeta(String cvcTarjeta) {
        this.cvcTarjeta = cvcTarjeta;
    }

    public String getFechaCaducidadTarjeta() {
        return fechaCaducidadTarjeta;
    }

    public void setFechaCaducidadTarjeta(String fechaCaducidadTarjeta) {
        this.fechaCaducidadTarjeta = fechaCaducidadTarjeta;
    }

    public String getNombreTarjeta() {
        return nombreTarjeta;
    }

    public void setNombreTarjeta(String nombreTarjeta) {
        this.nombreTarjeta = nombreTarjeta;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PedidoCompletoRequest that)) return false;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public String toString() {
        return "PedidoCompletoRequest{" +
                "id=" + id +
                ", pagoId=" + pagoId +
                ", pedidoId=" + pedidoId +
                ", ticketExt='" + ticketExt + '\'' +
                ", fecha='" + fecha + '\'' +                 // MODIFICADO en toString()
                ", importe='" + importe + '\'' +             // MODIFICADO en toString()
                ", estadoPago='" + estadoPago + '\'' +
                ", comercioNombre='" + comercioNombre + '\'' +
                ", numeroTarjeta='" + numeroTarjeta + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
