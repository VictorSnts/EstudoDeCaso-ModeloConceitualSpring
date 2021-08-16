package com.victor.spring.modeloconceitual;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.victor.spring.modeloconceitual.domain.Categoria;
import com.victor.spring.modeloconceitual.domain.Cidade;
import com.victor.spring.modeloconceitual.domain.Cliente;
import com.victor.spring.modeloconceitual.domain.Endereco;
import com.victor.spring.modeloconceitual.domain.Estado;
import com.victor.spring.modeloconceitual.domain.Produto;
import com.victor.spring.modeloconceitual.domain.enums.TipoCliente;
import com.victor.spring.modeloconceitual.repositories.CategoriaRepository;
import com.victor.spring.modeloconceitual.repositories.CidadeRepository;
import com.victor.spring.modeloconceitual.repositories.ClienteRepository;
import com.victor.spring.modeloconceitual.repositories.EnderecoRepository;
import com.victor.spring.modeloconceitual.repositories.EstadoRepository;
import com.victor.spring.modeloconceitual.repositories.ProdutoRepository;

@SpringBootApplication
public class ModeloconceitualApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;

	public static void main(String[] args) {
		SpringApplication.run(ModeloconceitualApplication.class, args);
	}

	// Instanciacao dos objetos
	@Override
	public void run(String... args) throws Exception {

		Categoria cat1 = new Categoria(null, "Informatica");
		Categoria cat2 = new Categoria(null, "Escritorio");

		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);

		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");

		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);

		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "36378912477", TipoCliente.PESSOAFISICA);
		
		Endereco e1 = new Endereco(null, "Rua Flores", 300, "Apto 203", "Jardim", "38220834", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Matos", 105, "Sala 800", "Centro", "38777012", cli1, c2);


		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));

		cli1.getTelefones().addAll(Arrays.asList("27363323", "93838393"));
		
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		

		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));

	}

}
