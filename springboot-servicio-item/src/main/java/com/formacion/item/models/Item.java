package com.formacion.item.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import springboot.servicio.commons.models.entity.ProductoEntity;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {

	private ProductoEntity producto;
	private Integer cantidad;
	
	public Double getTotal() {
		return producto.getPrecio() * cantidad.doubleValue();
	}
}
