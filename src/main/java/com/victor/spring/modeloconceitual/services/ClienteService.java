package com.victor.spring.modeloconceitual.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.victor.spring.modeloconceitual.domain.Cliente;
import com.victor.spring.modeloconceitual.dto.ClienteDTO;
import com.victor.spring.modeloconceitual.repositories.ClienteRepository;
import com.victor.spring.modeloconceitual.services.exceptions.DataIntegrityException;
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

	public List<Cliente> listar() {
		List<Cliente> clientes = clienteRepository.findAll();
		if (clientes.isEmpty())
			throw new ObjectNotFoundException("Nenhum objeto encontrado!");
		return clientes;
	}
	
	public Cliente inserir(Cliente cliente) {
		cliente.setId(null); // Garantindo que o objeto seja uma nova cliente
		return clienteRepository.save(cliente);
	}
	
	public Cliente atualizar(Cliente cliente) {
		Cliente novoCliente = buscar(cliente.getId());
		atualizaDados(cliente, novoCliente);
		return clienteRepository.save(novoCliente);
	}

	public void deletar(Integer id) {
		this.buscar(id);
		try {
			clienteRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException(
					"Não é possivel excluir uma cliente que tenha entidades relacionadas");
		}
	}

	public Page<Cliente> listarPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pr = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return clienteRepository.findAll(pr);
	}

	public Cliente fromDTO(ClienteDTO clienteDTO) {
		return new Cliente(clienteDTO.getId(), clienteDTO.getNome(), clienteDTO.getEmail(), null, null);
	}
	
	private void atualizaDados (Cliente cliente, Cliente novoCliente) {
		novoCliente.setNome(cliente.getNome());
		novoCliente.setEmail(cliente.getEmail());
	}
}
