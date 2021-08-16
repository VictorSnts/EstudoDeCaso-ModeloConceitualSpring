package com.victor.spring.modeloconceitual.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.victor.spring.modeloconceitual.domain.Cidade;

// indica ao JPA que essa interface vai ser capaz de realizar operacoes de acesso dados
@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Integer> {

}
