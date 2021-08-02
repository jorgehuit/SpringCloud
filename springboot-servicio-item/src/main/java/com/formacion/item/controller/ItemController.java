package com.formacion.item.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.formacion.item.models.Item;
import com.formacion.item.models.Producto;
import com.formacion.item.service.ItemService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RefreshScope
public class ItemController {

    private static final String ORDER_SERVICE_BCK = "orderServiceOffline";
	
    @Autowired
    private Environment env;
    
    @Autowired
    @Qualifier("serviceFeing")
    private ItemService itemService;
    
    @Value("${configuracion.texto}")
    private String texto;    

    @GetMapping("/listar")
    public List<Item> listar() {
        return itemService.findAll();
    }

    @CircuitBreaker(name = ORDER_SERVICE_BCK, fallbackMethod = "itemFailed")
    @GetMapping("/ver/{id}/cantidad/{cantidad}")
    public Item detalle(@PathVariable Long id, @PathVariable Integer cantidad) {

        return itemService.findById(id, cantidad);
    }
    
    @GetMapping("/obtener-config")
    public ResponseEntity<?> obtenerConfig(@Value("${server.port}") String port){
    	log.info(texto);
    	Map<String, String> json = new HashMap<>();
    	json.put("texto", texto);
    	json.put("port", port);
    	if(env.getActiveProfiles().length > 0 && env.getActiveProfiles()[0].equals("dev")) {
    		json.put("autor.nombre", env.getProperty("configuracion.autor.nombre"));
    		json.put("autor.email", env.getProperty("configuracion.autor.email"));
    	}
    	
    	return new ResponseEntity<Map<String, String>>(json, HttpStatus.OK);
    }
    
    public Item itemFailed(Exception e) {
    	Producto producto = new Producto();
    	producto.setId(1L);
    	producto.setNombre("NombreTest");
    	producto.setPrecio(23.22);
		return new Item(producto , 10);
    }
}
