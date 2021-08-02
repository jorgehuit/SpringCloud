package com.formacion.item.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.formacion.item.clietes.ProductoClienteRest;
import com.formacion.item.models.Item;
import com.formacion.item.service.ItemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;


@Service("serviceFeing")
@Primary
public class ItemServiceFeignImpl  implements ItemService{

    @Autowired
    private ProductoClienteRest clienteFeing;

    @Override
    public List<Item> findAll() {
        return clienteFeing.listar().stream().map(p -> new Item(p, 1)).collect(Collectors.toList());
    }

    @Override
    public Item findById(Long id, Integer cantidad) {
        return new Item(clienteFeing.detalle(id), cantidad);
    }
    
}
