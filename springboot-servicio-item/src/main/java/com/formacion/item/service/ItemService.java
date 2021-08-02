package com.formacion.item.service;

import java.util.List;

import com.formacion.item.models.Item;

public interface ItemService {
    public List<Item> findAll();
    public Item findById(Long id, Integer cantidad);
}
