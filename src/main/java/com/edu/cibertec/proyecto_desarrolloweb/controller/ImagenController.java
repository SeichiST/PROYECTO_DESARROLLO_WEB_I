package com.edu.cibertec.proyecto_desarrolloweb.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/imagenes")
public class ImagenController {
    @Value("${app.upload.dir}")
    private String uploadDir;

    @PostMapping("/juegos")
    public ResponseEntity<Map<String, String>> subirImagen(@RequestParam("file") MultipartFile file){
        Map<String, String> response = new HashMap<>();
        try {
            // Genera un nombre único para evitar que dos imágenes se sobrescriban
            String nombreArchivo = UUID.randomUUID() + "_" + file.getOriginalFilename();

            Path directorio = Paths.get(uploadDir);
            if (!Files.exists(directorio)) {
                Files.createDirectories(directorio);
            }

            Path rutaCompleta = directorio.resolve(nombreArchivo);
            Files.copy(file.getInputStream(), rutaCompleta);

            response.put("nombreArchivo", nombreArchivo);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (IOException e) {
            response.put("mensaje", "Error al subir la imagen");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
