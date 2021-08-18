package com.formacion.item.clietes;

import java.util.List;

import springboot.servicio.commons.models.entity.ProductoEntity;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "servicio-productos")
public interface ProductoClienteRest {
    
    @GetMapping("/listar")
    public List<ProductoEntity> listar();
    
    @GetMapping("/ver/{id}")
    public ProductoEntity detalle(@PathVariable Long id);
    
}
