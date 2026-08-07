package com.edu.cibertec.proyecto_desarrolloweb.service;

import com.edu.cibertec.proyecto_desarrolloweb.dto.JuegosDto;
import com.edu.cibertec.proyecto_desarrolloweb.model.Categorias;
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
        return juegosRepository.findByActivoTrue();
    }

    public JuegosDto buscarPorId(Integer id) {
        Juegos juego = juegosRepository.findById(id).orElse(null);
        if (juego == null) {
            return null;
        } else {
            JuegosDto dto = new JuegosDto();
            dto.setIdjuegos(juego.getIdjuegos());
            dto.setDescripcion(juego.getDescripcion());
            dto.setPrecio(juego.getPrecio());
            dto.setImagen(juego.getImagen());
            dto.setIdcategoria(juego.getCategoria().getIdcategoria());
            dto.setActivo(juego.getActivo());
            return dto;
        }
    }
    public void actualizarJuego(JuegosDto dto){
        juegosRepository.updateJuego(dto.getDescripcion(), dto.getPrecio(),
                dto.getImagen(), dto.getIdcategoria(), dto.getActivo() ,dto.getIdjuegos());
    }
    public void eliminarJuego(Integer id){
        juegosRepository.deleteJuego(false, id);
    }
    public void guardarJuego(JuegosDto dto) {
        Categorias categoria = new Categorias();
        categoria.setIdcategoria(dto.getIdcategoria());

        Juegos juego = new Juegos();
        juego.setDescripcion(dto.getDescripcion());
        juego.setPrecio(dto.getPrecio());
        juego.setImagen(dto.getImagen());
        juego.setCategoria(categoria);
        juego.setActivo(dto.getActivo());
        juegosRepository.save(juego);
    }
}