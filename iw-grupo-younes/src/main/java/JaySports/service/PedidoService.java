package JaySports.service;

import JaySports.model.*;
import JaySports.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private CarritoService carritoService;

    @Transactional
    public Pedido crearPedidoDesdeCarrito(Usuario usuario, Carrito carrito) {
        // Verificar si ya existe un pedido pendiente

        Pedido pedidoExistente = pedidoRepository.findPendingByUsuario(usuario).orElse(null);

        Pedido pedido = new Pedido();

        // Si el pedido ya existe, entonces devolver el pedido ya existente
        if (pedidoExistente != null) {
            pedido = pedidoExistente;
        }

        if (pedidoExistente == null) {
            pedido.setUsuario(usuario);
            pedido.setFechaPedido(LocalDateTime.now());
            pedido.setEstado("PENDIENTE");
            pedido.setNumeroPedido(generarNumeroPedido());
        }

        pedido.setTotal(carrito.getPrecioTotal());


        // Crear ProductoPedido para cada ProductoCarrito
        List<ProductoPedido> productosPedido = new ArrayList<>();
        for (ProductoCarrito pc : carrito.getProductosCarrito()) {
            ProductoPedido pp = new ProductoPedido();
            pp.setPedido(pedido);
            pp.setProducto(pc.getProducto());
            pp.setCantidad(pc.getCantidad());
            pp.setPrecioUnitario(pc.getPrecioUnitario());
            pp.setSubtotal(pc.getSubtotal());
            productosPedido.add(pp);
        }
        pedido.setProductosPedido(productosPedido);

        // Guardar el pedido
        pedido = pedidoRepository.save(pedido);

        // Vaciar el carrito
        // carritoService.vaciarCarrito(carrito);

        return pedido;
    }

    private String generarNumeroPedido() {

        String ticket = "";
        do {
            ticket = "PED-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();

        } while (pedidoRepository.findByNumeroPedido(ticket).isPresent());

        return ticket;
    }
}