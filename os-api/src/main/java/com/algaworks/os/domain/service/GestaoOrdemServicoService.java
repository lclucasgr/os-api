package com.algaworks.os.domain.service;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.os.api.model.Comentario;
import com.algaworks.os.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.os.domain.exception.NegocioException;
import com.algaworks.os.domain.model.Cliente;
import com.algaworks.os.domain.model.OrdemServico;
import com.algaworks.os.domain.model.StatusOrdemServico;
import com.algaworks.os.domain.repository.ClienteRepository;
import com.algaworks.os.domain.repository.ComentarioRepository;
import com.algaworks.os.domain.repository.OrdemServicoRepository;

@Service
public class GestaoOrdemServicoService {
	
	@Autowired
	private OrdemServicoRepository ordemServicoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ComentarioRepository comentarioRepository;
	
	public OrdemServico criar(OrdemServico ordemServico)
	{
		
		Cliente cliente = clienteRepository.findById(ordemServico.getCliente().getId())
				. orElseThrow(() -> new NegocioException("Cliente nao encontrado"));
		
		ordemServico.setCliente(cliente);
		ordemServico.setStatus(StatusOrdemServico.ABERTA);
		ordemServico.setDataAbertura(OffsetDateTime.now());
		
		return ordemServicoRepository.save(ordemServico);
	}
	
	public void finalizar(Long ordemServicoId)
	{
		OrdemServico ordemServico = buscar(ordemServicoId);
		
		ordemServico.finalizar();
		
		ordemServicoRepository.save(ordemServico);
	}
	
	public Comentario adicionarComentario(Long ordemServicoId, String descricao)
	{
		OrdemServico ordemServico = buscar(ordemServicoId);
		
		Comentario comentario = new Comentario();
		comentario.setDataEnvio(OffsetDateTime.now());
		comentario.setDescricao(descricao);
		comentario.setOrdemServico(ordemServico);
		
		return comentarioRepository.save(comentario);
	}
	
	private OrdemServico buscar(Long ordemServicoId) {
		return ordemServicoRepository.findById(ordemServicoId)
				.orElseThrow(() -> new EntidadeNaoEncontradaException("Ordem de servico nao encontrada"));
	}
	

}
