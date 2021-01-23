package com.algaworks.os.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.os.domain.exception.NegocioException;
import com.algaworks.os.domain.model.Cliente;
import com.algaworks.os.domain.repository.ClienteRepository;

@Service
public class CadastroClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
		
	public Cliente salvar(Cliente cliente)
	{
		Cliente clienteExistente = clienteRepository.findByEmail(cliente.getEmail());
		
		if(clienteExistente != null && !clienteExistente.equals(cliente))
		{
			throw new NegocioException("Ja existe um cliente cadastro com esse email ");
		}
		
		return clienteRepository.save(cliente);
	}
	
	public void excluir(Long clienteId)
	{
		clienteRepository.deleteById(clienteId);
	}
	
}
