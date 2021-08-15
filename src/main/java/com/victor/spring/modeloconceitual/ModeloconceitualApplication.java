package com.victor.spring.modeloconceitual;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.victor.spring.modeloconceitual.domain.Categoria;
import com.victor.spring.modeloconceitual.repositories.CategoriaRepository;

@SpringBootApplication
public class ModeloconceitualApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;

	public static void main(String[] args) {
		SpringApplication.run(ModeloconceitualApplication.class, args);
	}

	// Instanciacao dos objetos
	@Override
	public void run(String... args) throws Exception {

		Categoria cat1 = new Categoria(1, "Informatica");
		Categoria cat2 = new Categoria(2, "Escritorio");
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		

	}

}
