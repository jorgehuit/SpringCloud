package com.formacion.com;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

//@SpringBootTest
class SpringbootServicioProductosApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	public void duplicados() {
		List<Integer> elementosDuplicados = new ArrayList<>();
		elementosDuplicados.add(1);
		elementosDuplicados.add(2);
		elementosDuplicados.add(3);
		elementosDuplicados.add(1);
		Set<Integer> set = new LinkedHashSet<>(elementosDuplicados);
		elementosDuplicados.clear();
		elementosDuplicados.addAll(set);
		elementosDuplicados.forEach(f -> {
			System.out.println(f);
		});

	}
}