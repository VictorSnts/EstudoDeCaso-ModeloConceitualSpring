package com.victor.spring.modeloconceitual.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.victor.spring.modeloconceitual.domain.Cliente;

// indica ao JPA que essa interface vai ser capaz de realizar operacoes de acesso dados
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
	
	@Transactional(readOnly = true)
	Cliente findByEmail(String email); // O Spring Data autmaticamente cria o metodo quando usamos o findBy no inicio
										// do nome do metodo

}
