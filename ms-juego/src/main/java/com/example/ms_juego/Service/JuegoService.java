package com.example.ms_juego.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ms_juego.Client.CatalogoFeignClient;
import com.example.ms_juego.Client.DescuentoFeignClient;
import com.example.ms_juego.Client.ModFeignClient;
import com.example.ms_juego.DTO.CatalogoDTO;
import com.example.ms_juego.DTO.DescuentoDTO;
import com.example.ms_juego.DTO.ModDTO;
import com.example.ms_juego.Model.Juego;
import com.example.ms_juego.Repository.JuegoRepository;

@Service
public class JuegoService {
    @Autowired
    private JuegoRepository repo;
    @Autowired
    private CatalogoFeignClient catalogoClient;
    @Autowired
    private DescuentoFeignClient descuentoClient;
    @Autowired
    private ModFeignClient modClient;

    //Listar
    public List<Juego> listarJuego(){
        return repo.findAll();
    }

    //Guardar
    public Juego guardarJuego(Juego jue){
        return repo.save(jue);
    }

    //Actualizar
    public Juego actualizarJuego(Juego jue){
        return repo.save(jue);
    }

    //Eliminar
    public void eliminar(Long id){
        repo.deleteById(id);
    }

    //Disponibles
    public List<Juego> juegosDisponible(){
        return repo.findByDisponible(true);
    }

    //Conexiones
    public Map<String, Object> obtenerJuegoCompleto(Long id){
        Juego juego = repo.findById(id).orElse(null);
        Map<String, Object> respuesta = new HashMap<>();
        
        if (juego != null){
            CatalogoDTO catalogo =
                    catalogoClient.obtenerCatalogo(id);

            DescuentoDTO descuento =
                    descuentoClient.obtenerDescuento(id);

            ModDTO mod =
                    modClient.obtenerMod(id);

            respuesta.put("juego", juego);
            respuesta.put("catalogo", catalogo);
            respuesta.put("descuento", descuento);
            respuesta.put("mod", mod);
        }
        return respuesta;
    }
}
