package com.formacion.item.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.formacion.item.models.Item;
import springboot.servicio.commons.models.entity.ProductoEntity;
import com.formacion.item.service.ItemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private RestTemplate clienteRest;

    @Override
    public List<Item> findAll() {
        List<ProductoEntity> productos = 
            Arrays.asList(clienteRest.getForObject("http://localhost:8001/listar", ProductoEntity[].class));

        return productos.stream().map(p -> new Item(p, 1)).collect(Collectors.toList());
    }

    @Override
    public Item findById(Long id, Integer cantidad) {
        Map<String, Long> pathVariables = new HashMap<>();
        pathVariables.put("id", id);
        ProductoEntity producto = clienteRest.getForObject("http://localhost:8001/ver/{id}", ProductoEntity.class, pathVariables);
        return new Item(producto, cantidad);
    }
    
}
