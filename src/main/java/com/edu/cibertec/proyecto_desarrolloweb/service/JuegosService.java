package com.edu.cibertec.proyecto_desarrolloweb.service;

import org.springframework.stereotype.Service;
import com.edu.cibertec.proyecto_desarrolloweb.model.Juegos;
import com.edu.cibertec.proyecto_desarrolloweb.repository.JuegosRepository;
import java.util.List;

@Service
public class JuegosService {

    private final JuegosRepository juegosRepository;

    public JuegosService(JuegosRepository juegosRepository) {
        this.juegosRepository = juegosRepository;
    }

    public List<Juegos> listarJuegos() {
        return juegosRepository.findAll();
    }

    public Juegos buscarPorId(Integer id) {
        return juegosRepository.findById(id).orElse(null);
    }

    public void guardarJuego(Juegos juego) {
        juegosRepository.save(juego);
    }
}