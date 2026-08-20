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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/api/v1/imagenes")
public class ImagenController {
    @Value("${app.upload.dir}")
    private String uploadDir;

    @PostMapping("/juegos")
    public ResponseEntity<Map<String, String>> subirImagen(@RequestParam("file") MultipartFile file){
        Map<String, String> response = new HashMap<>();
        try {
            Path directorio = Paths.get(uploadDir);
            if (!Files.exists(directorio)) {
                Files.createDirectories(directorio);
            }


            String nombreOriginal = file.getOriginalFilename();
            String extension = nombreOriginal.substring(nombreOriginal.lastIndexOf("."));


            int siguienteNumero = obtenerSiguienteNumero(directorio);


            String nombreArchivo = String.format("%04d%s", siguienteNumero, extension);

            Path rutaCompleta = directorio.resolve(nombreArchivo);
            Files.copy(file.getInputStream(), rutaCompleta);

            response.put("nombreArchivo", nombreArchivo);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (IOException e) {
            response.put("mensaje", "Error al subir la imagen");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    private int obtenerSiguienteNumero(Path directorio) throws IOException {
        Pattern pattern = Pattern.compile("^(\\d{4})\\..+$");
        int maxNumero = 0;

        try (var stream = Files.list(directorio)) {
            for (Path archivo : stream.toList()) {
                Matcher matcher = pattern.matcher(archivo.getFileName().toString());
                if (matcher.matches()) {
                    int numero = Integer.parseInt(matcher.group(1));
                    if (numero > maxNumero) {
                        maxNumero = numero;
                    }
                }
            }
        }

        return maxNumero + 1;
    }
}
