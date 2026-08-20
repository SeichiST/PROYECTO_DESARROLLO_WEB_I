package com.edu.cibertec.proyecto_desarrolloweb.service;

import com.edu.cibertec.proyecto_desarrolloweb.dto.DetalleVentaDto;
import com.edu.cibertec.proyecto_desarrolloweb.dto.VentaDto;
import com.edu.cibertec.proyecto_desarrolloweb.model.*;
import com.edu.cibertec.proyecto_desarrolloweb.repository.ClientesRepository;
import com.edu.cibertec.proyecto_desarrolloweb.repository.DetalleRepository;
import com.edu.cibertec.proyecto_desarrolloweb.repository.JuegosRepository;
import com.edu.cibertec.proyecto_desarrolloweb.repository.VentasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class VentasService {

    @Autowired
    private VentasRepository ventasRepository;

    @Autowired
    private DetalleRepository detalleRepository;

    @Autowired
    private ClientesRepository clientesRepository;

    @Autowired
    private JuegosRepository juegosRepository;

    @Transactional
    public Ventas registrarVenta(VentaDto ventaDto) {
        Ventas nuevaVenta = new Ventas();

        Clientes cliente = clientesRepository.findById(ventaDto.getIdCliente())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        nuevaVenta.setCliente(cliente);

        nuevaVenta.setMontototal(ventaDto.getMontoTotal());
        nuevaVenta.setFechaventa(LocalDateTime.now());
        nuevaVenta.setEstado("1");

        Ventas ventaGuardada = ventasRepository.save(nuevaVenta);

        for (DetalleVentaDto item : ventaDto.getDetalles()) {
            Detalle detalle = new Detalle();

            DetallePK pk = new DetallePK();
            pk.setIdventa(ventaGuardada.getIdventa());
            pk.setIdjuegos(item.getIdJuego());
            detalle.setId(pk);

            detalle.setVenta(ventaGuardada);

            Juegos juego = juegosRepository.findById(item.getIdJuego())
                    .orElseThrow(() -> new RuntimeException("Juego no encontrado"));
            detalle.setJuego(juego);

            detalle.setCantidad(item.getCantidad());
            detalle.setPrecio(item.getPrecio());
            detalle.setEstado("1");

            detalleRepository.save(detalle);
        }

        return ventaGuardada;
    }
}