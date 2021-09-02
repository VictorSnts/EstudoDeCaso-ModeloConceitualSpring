package com.victor.spring.modeloconceitual.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.victor.spring.modeloconceitual.domain.Categoria;
import com.victor.spring.modeloconceitual.domain.Produto;
import com.victor.spring.modeloconceitual.repositories.CategoriaRepository;
import com.victor.spring.modeloconceitual.repositories.ProdutoRepository;
import com.victor.spring.modeloconceitual.services.exceptions.ObjectNotFoundException;

@Service
public class ProdutoService {

	// indica que essa dependencia vai ser instanciada automaticamente pelo spring
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private CategoriaRepository categoriaRepository;

	public Produto buscar(Integer id) {
		Optional<Produto> produto = produtoRepository.findById(id);
		return produto.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Produto.class.getName()));
	}

	public List<Produto> listar() {
		List<Produto> produtos = produtoRepository.findAll();
		if (produtos.isEmpty())
			throw new ObjectNotFoundException("Nenhum objeto encontrado!");
		return produtos;
	}

	public Page<Produto> search(String nome, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy,
			String direction) {
		PageRequest pr = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		List<Categoria> categorias = categoriaRepository.findAllById(ids);
		return produtoRepository.search(nome, categorias, pr);

	}
}
