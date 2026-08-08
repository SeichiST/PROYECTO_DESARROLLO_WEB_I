package com.edu.cibertec.proyecto_desarrolloweb.controller;

import com.edu.cibertec.proyecto_desarrolloweb.dto.JuegosDto;
import com.edu.cibertec.proyecto_desarrolloweb.model.Juegos;
import com.edu.cibertec.proyecto_desarrolloweb.service.JuegosService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/juego")
@RequiredArgsConstructor
public class JuegosController {
    private final JuegosService juegosService;

    @GetMapping
    public ResponseEntity<List<Juegos>> getAllJuegos(){
        return new ResponseEntity<>(
                juegosService.listarJuegos(),
                HttpStatus.OK);
    }
    //localhost:8085/api/v1/juego/1
    @GetMapping("/{id}")
    public ResponseEntity<JuegosDto> getJuegoById(
            @PathVariable Integer id){
        return new ResponseEntity<>(
                juegosService.buscarPorId(id),
                HttpStatus.OK);
    }
    //localhost:8085/api/v1/juego
    @PostMapping
    public ResponseEntity<Map<String, String>> createJuego(
            @RequestBody JuegosDto dto){
        Map<String, String> response = new HashMap<>();
        try{
            juegosService.guardarJuego(dto);
            response.put("mensaje", "Juego creado correctamente");
            return new ResponseEntity<>(response,
                    HttpStatus.CREATED);
        } catch (Exception e) {
            response.put("mensaje", "Ocurrió un error al registrar el juego");
            return new ResponseEntity<>(response,
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //[PATCH] = localhost:8085/api/v1/juego/78
    @PatchMapping("/{id}")
    public ResponseEntity<Map<String, String>> actualizarJuego(
            @PathVariable Integer id,
            @RequestBody JuegosDto dto){
        Map<String, String> response = new HashMap<>();
        try{
            dto.setIdjuegos(id);
            juegosService.actualizarJuego(dto);
            response.put("mensaje", "Juego actualizado correctamente");
            return new ResponseEntity<>(response,
                    HttpStatus.CREATED);
        } catch (Exception e) {
            response.put("mensaje", "Ocurrió un error al actualizar el juego");
            return new ResponseEntity<>(response,
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> eliminarJuego(
            @PathVariable Integer id){
        Map<String, String> response = new HashMap<>();
        try {
            juegosService.eliminarJuego(id);
            response.put("mensaje", "Juegos eliminado correctamente");
            return new ResponseEntity<>(response,
                    HttpStatus.OK);
        } catch (Exception e) {
            response.put("mensaje", "Ocurrió un error al actualizar el juego");
            return new ResponseEntity<>(response,
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
