package com.formacion.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.formacion.models.entity.ProductoEntity;

public interface ProductoRepository extends JpaRepository<ProductoEntity, Long> {

}
