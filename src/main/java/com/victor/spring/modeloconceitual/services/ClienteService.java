package com.victor.spring.modeloconceitual.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.victor.spring.modeloconceitual.domain.Cidade;
import com.victor.spring.modeloconceitual.domain.Cliente;
import com.victor.spring.modeloconceitual.domain.Endereco;
import com.victor.spring.modeloconceitual.domain.enums.TipoCliente;
import com.victor.spring.modeloconceitual.dto.ClienteDTO;
import com.victor.spring.modeloconceitual.dto.NovoClienteDTO;
import com.victor.spring.modeloconceitual.repositories.ClienteRepository;
import com.victor.spring.modeloconceitual.repositories.EnderecoRepository;
import com.victor.spring.modeloconceitual.services.exceptions.DataIntegrityException;
import com.victor.spring.modeloconceitual.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	// indica que essa dependencia vai ser instanciada automaticamente pelo spring
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;

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
			throw new DataIntegrityException("Não é possivel excluir uma cliente que tenha entidades relacionadas");
		}
	}

	public Page<Cliente> listarPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pr = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return clienteRepository.findAll(pr);
	}

	public Cliente fromDTO(ClienteDTO clienteDTO) {
		return new Cliente(clienteDTO.getId(), clienteDTO.getNome(), clienteDTO.getEmail(), null, null);
	}

	public Cliente fromDTO(NovoClienteDTO novoClienteDTO) {
		Cliente cliente = new Cliente(null, novoClienteDTO.getNome(), novoClienteDTO.getEmail(),
				novoClienteDTO.getCpfOuCnpj(), TipoCliente.toEnum(novoClienteDTO.getTipoCliente()));
		Cidade cidade = new Cidade(novoClienteDTO.getCidadeId(), null, null);
		Endereco endereco = new Endereco(null, novoClienteDTO.getLogradouro(), novoClienteDTO.getNumero(),
				novoClienteDTO.getComplemento(), novoClienteDTO.getBairro(), novoClienteDTO.getCep(), cliente, cidade);

		cliente.getEnderecos().add(endereco);
		cliente.getTelefones().add(novoClienteDTO.getTel1());

		if (novoClienteDTO.getTel2() != null) {
			cliente.getTelefones().add(novoClienteDTO.getTel2());
		}
		if (novoClienteDTO.getTel3() != null) {
			cliente.getTelefones().add(novoClienteDTO.getTel3());
		}
		return cliente;
	}

	private void atualizaDados(Cliente cliente, Cliente novoCliente) {
		novoCliente.setNome(cliente.getNome());
		novoCliente.setEmail(cliente.getEmail());
	}

	@Transactional // indica que a inclusao dos dados tem que ocorrer de forma transacional
	public Cliente inserir(Cliente cliente) {
		cliente.setId(null);
		cliente = clienteRepository.save(cliente);
		enderecoRepository.saveAll(cliente.getEnderecos());
		return cliente;
	}
}
