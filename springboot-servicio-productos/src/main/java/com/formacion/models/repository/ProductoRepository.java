package com.formacion.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import springboot.servicio.commons.models.entity.ProductoEntity;

public interface ProductoRepository extends JpaRepository<ProductoEntity, Long> {

}
