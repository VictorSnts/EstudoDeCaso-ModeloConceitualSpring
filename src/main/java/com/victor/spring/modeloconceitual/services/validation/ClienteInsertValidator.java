package com.victor.spring.modeloconceitual.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.victor.spring.modeloconceitual.domain.Cliente;
import com.victor.spring.modeloconceitual.domain.enums.TipoCliente;
import com.victor.spring.modeloconceitual.dto.NovoClienteDTO;
import com.victor.spring.modeloconceitual.repositories.ClienteRepository;
import com.victor.spring.modeloconceitual.resource.exception.FieldMessage;
import com.victor.spring.modeloconceitual.services.validation.utils.br;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, NovoClienteDTO> {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Override
	public void initialize(ClienteInsert ann) {
	}

	@Override
	public boolean isValid(NovoClienteDTO novoClienteDTO, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();

		if (novoClienteDTO.getTipoCliente().equals(TipoCliente.PESSOAFISICA.getId()) && !br.isValidCPF(novoClienteDTO.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "O numero de cpf nao é valido"));
		}
		if (novoClienteDTO.getTipoCliente().equals(TipoCliente.PESSOAJURIDICA.getId()) && !br.isValidCNPJ(novoClienteDTO.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "O numero de CNPJ nao é valido"));
		}
		
		Cliente aux = clienteRepository.findByEmail(novoClienteDTO.getEmail());
		if(aux != null) {
			list.add(new FieldMessage("email", "O email informado ja esta cadastrado"));
		}

		// Transforma os erros personalizados(FieldMessage) para os erros do framework
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMsg()).addPropertyNode(e.getFiledName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}