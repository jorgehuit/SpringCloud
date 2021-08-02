package com.formacion.service;

import java.util.List;

import com.formacion.models.entity.ProductoEntity;

public interface ProductoService {
	
	public List<ProductoEntity> findAll();
	public ProductoEntity findById(Long id);
	
}
