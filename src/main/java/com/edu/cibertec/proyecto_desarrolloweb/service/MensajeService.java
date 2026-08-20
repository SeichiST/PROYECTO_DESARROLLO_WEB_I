package com.edu.cibertec.proyecto_desarrolloweb.service;

import com.edu.cibertec.proyecto_desarrolloweb.dto.MensajeDto;
import com.edu.cibertec.proyecto_desarrolloweb.model.Mensajes;
import com.edu.cibertec.proyecto_desarrolloweb.repository.MensajesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MensajeService {

    private final MensajesRepository mensajesRepository;

    public List<MensajeDto> listarMensajes(){
        return mensajesRepository.findAll().stream().map(this::convertirDTO).toList();
    }

    private MensajeDto convertirDTO(Mensajes mensajes){
        MensajeDto  dto = new MensajeDto();

        dto.setIdmensaje(mensajes.getIdmensaje());
        dto.setFechaenvio(mensajes.getFechaenvio());
        dto.setTextomensaje(mensajes.getTextomensaje());
        dto.setEstado(mensajes.getEstado());

        if(mensajes.getCliente() != null){
            dto.setNombres(mensajes.getCliente().getNombres());
            dto.setCorreo(mensajes.getCliente().getCorreo());
            dto.setTelefono(mensajes.getCliente().getTelefono());
        }
        return dto;
    }

}
