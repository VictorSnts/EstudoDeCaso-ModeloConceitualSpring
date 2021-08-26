package com.victor.spring.modeloconceitual.resource;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.victor.spring.modeloconceitual.domain.Cliente;
import com.victor.spring.modeloconceitual.dto.ClienteDTO;
import com.victor.spring.modeloconceitual.dto.NovoClienteDTO;
import com.victor.spring.modeloconceitual.services.ClienteService;

@RestController
@RequestMapping(value = "/cliente")
public class ClienteResource {

	@Autowired
	private ClienteService clienteService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> buscar(@PathVariable Integer id) {
		Cliente cliente = clienteService.buscar(id);
		return ResponseEntity.ok().body(cliente);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> listar() {
		List<Cliente> clientes = clienteService.listar();
		List<ClienteDTO> clienteDTO = clientes.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(clienteDTO);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> atualizar(@PathVariable Integer id, @Valid @RequestBody ClienteDTO clienteDTO) {
		Cliente cliente = clienteService.fromDTO(clienteDTO);
		cliente.setId(id);
		cliente = clienteService.atualizar(cliente);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deletar(@PathVariable Integer id) {
		clienteService.deletar(id);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ResponseEntity<?> listarPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		Page<Cliente> clientes = clienteService.listarPage(page, linesPerPage, orderBy, direction);
		Page<ClienteDTO> clienteDTO = clientes.map(obj -> new ClienteDTO(obj));
		return ResponseEntity.ok().body(clienteDTO);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> inserir(@Valid @RequestBody NovoClienteDTO novoClienteDTO) {
		Cliente cliente = clienteService.fromDTO(novoClienteDTO);
		cliente = clienteService.inserir(cliente);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest() // Pega a URI do ultimo recurso que foi inserido
				.path("/{id}").buildAndExpand(cliente.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

}
