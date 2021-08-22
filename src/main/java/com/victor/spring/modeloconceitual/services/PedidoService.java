package com.victor.spring.modeloconceitual.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.victor.spring.modeloconceitual.domain.Pedido;
import com.victor.spring.modeloconceitual.repositories.PedidoRepository;
import com.victor.spring.modeloconceitual.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	// indica que essa dependencia vai ser instanciada automaticamente pelo spring
	@Autowired
	private PedidoRepository pedidoRepository;

	public Pedido buscar(Integer id) {
		Optional<Pedido> pedido = pedidoRepository.findById(id);
		return pedido.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
	}
	
	public List<Pedido> listar() {
		List<Pedido> pedidos = pedidoRepository.findAll();
		if(pedidos.isEmpty())
			throw new ObjectNotFoundException("Nenhum objeto encontrado!");
		return pedidos;
	}
}
