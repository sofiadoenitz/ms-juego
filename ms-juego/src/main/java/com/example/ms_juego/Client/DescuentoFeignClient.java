package com.example.ms_juego.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.ms_juego.DTO.DescuentoDTO;

@FeignClient(
    name = "ms-descuento",
    url = "http://localhost:8089"
)
public interface DescuentoFeignClient {
    @GetMapping("/api/v1/descuento/juego/{id}")
    DescuentoDTO obtenerDescuento(@PathVariable Long id);
}
