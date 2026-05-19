package com.example.ms_juego.DTO;

import lombok.Data;

@Data
public class CatalogoDTO {
    private Long id;
    private String categoria;
    private String plataforma;
    private boolean disponible;

}
