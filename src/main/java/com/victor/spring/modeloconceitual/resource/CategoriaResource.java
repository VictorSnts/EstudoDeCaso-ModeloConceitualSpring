package com.victor.spring.modeloconceitual.resource;

import java.net.URI;
import java.util.List;

import javax.servlet.Servlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.victor.spring.modeloconceitual.domain.Categoria;
import com.victor.spring.modeloconceitual.services.CategoriaService;

@RestController
@RequestMapping(value = "/categoria")
public class CategoriaResource {

	@Autowired
	private CategoriaService categoriaService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> listar(@PathVariable Integer id) {

		Categoria categoria = categoriaService.buscar(id);
		return ResponseEntity.ok().body(categoria);
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<?> listar() {
		List<Categoria> categoria = categoriaService.listar();
		return ResponseEntity.ok().body(categoria);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> inserir(@RequestBody Categoria categoria) {
		categoria = categoriaService.inserir(categoria);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()			// Pega a URI do ultimo recurso que foi inserido
				.path("/{id}").buildAndExpand(categoria.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
}
