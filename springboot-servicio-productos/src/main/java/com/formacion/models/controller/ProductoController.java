package com.formacion.models.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.formacion.models.entity.ProductoEntity;
import com.formacion.service.ProductoService;

@RestController
public class ProductoController {

	@Autowired
	private ProductoService productoService;
	
	@Autowired
	private Environment env;
	
	@Value("${server.port}")
	private Integer serverPort;
	
	@GetMapping("/listar")
	public List<ProductoEntity> listar(){
		return productoService.findAll().stream().map(p ->{
//			p.setPort(serverPort);
			p.setPort(Integer.parseInt(env.getProperty("local.server.port")));
			return p;
		}).collect(Collectors.toList());
	}

	@GetMapping("/ver/{id}")
	public ProductoEntity detalle(@PathVariable Long id){
		ProductoEntity producto = productoService.findById(id);
//		producto.setPort(serverPort);
		producto.setPort(Integer.parseInt(env.getProperty("local.server.port")));
		
		//Simular timeout
		try {
			Thread.sleep(3000L);
		} catch (InterruptedException e) {
		}
	
		return producto;
	}
}
