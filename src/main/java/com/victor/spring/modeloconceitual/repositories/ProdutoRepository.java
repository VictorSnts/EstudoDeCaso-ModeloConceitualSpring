package com.victor.spring.modeloconceitual.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.victor.spring.modeloconceitual.domain.Categoria;
import com.victor.spring.modeloconceitual.domain.Produto;

// indica ao JPA que essa interface vai ser capaz de realizar operacoes de acesso dados
@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

	@Transactional(readOnly = true)
	@Query("SELECT DISTINCT obj FROM Produto obj INNER JOIN obj.categorias cat WHERE obj.nome LIKE %:nome% AND cat IN :categorias")
	public Page<Produto> search(@Param("nome") String nome, @Param("categorias") List<Categoria> categorias,
			Pageable pageRequest);

}
