package com.edu.cibertec.proyecto_desarrolloweb.controller;

import com.edu.cibertec.proyecto_desarrolloweb.dto.MensajeDto;
import com.edu.cibertec.proyecto_desarrolloweb.service.MensajeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/mensaje")
public class MensajeController {

    private final MensajeService mensajeService;

    @GetMapping
    public List<MensajeDto> listarMensaje(){
        return mensajeService.listarMensajes();
    }
}
