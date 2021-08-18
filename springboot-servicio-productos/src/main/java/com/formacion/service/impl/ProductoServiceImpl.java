package com.formacion.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import springboot.servicio.commons.models.entity.ProductoEntity;
import com.formacion.models.repository.ProductoRepository;
import com.formacion.service.ProductoService;

@Service
public class ProductoServiceImpl implements ProductoService {

	@Autowired
	private ProductoRepository productoRepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<ProductoEntity> findAll() {
		return productoRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public ProductoEntity findById(Long id) {
		return productoRepository.findById(id).orElse(null);
	}

}
