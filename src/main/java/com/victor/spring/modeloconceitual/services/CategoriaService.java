package com.victor.spring.modeloconceitual.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.victor.spring.modeloconceitual.domain.Categoria;
import com.victor.spring.modeloconceitual.repositories.CategoriaRepository;
import com.victor.spring.modeloconceitual.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	// indica que essa dependencia vai ser instanciada automaticamente pelo spring
	@Autowired
	private CategoriaRepository categoriaRepository;

	public Categoria buscar(Integer id) {
		Optional<Categoria> categoria = categoriaRepository.findById(id);
		return categoria.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}
	
	public List<Categoria> listar() {
		List<Categoria> categorias = categoriaRepository.findAll();
		if(categorias.isEmpty())
			throw new ObjectNotFoundException("Nenhum objeto encontrado!");
		return categorias;
	}
	
	public Categoria inserir(Categoria categoria) {
		categoria.setId(null); // Garantindo que o objeto seja uma nova categoria
		return categoriaRepository.save(categoria);
	}
	
	public Categoria atualizar(Categoria categoria) {
		this.buscar(categoria.getId());
		return categoriaRepository.save(categoria);
	}
	
}
