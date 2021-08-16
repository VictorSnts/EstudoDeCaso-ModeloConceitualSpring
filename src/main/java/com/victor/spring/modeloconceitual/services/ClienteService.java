package com.victor.spring.modeloconceitual.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.victor.spring.modeloconceitual.domain.Cliente;
import com.victor.spring.modeloconceitual.repositories.ClienteRepository;
import com.victor.spring.modeloconceitual.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	// indica que essa dependencia vai ser instanciada automaticamente pelo spring
	@Autowired
	private ClienteRepository clienteRepository;

	public Cliente buscar(Integer id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		return cliente.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}
}
