package br.com.vitorforum.resposta.service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.vitorforum.resposta.dto.RespostaDto;
import br.com.vitorforum.resposta.http.TopicoClient;
import br.com.vitorforum.resposta.modelo.Resposta;
import br.com.vitorforum.resposta.repository.RespostasRepository;

@Service
public class RespostaService {
	
	@Autowired
    private RespostasRepository repository;

	@Autowired
    private TopicoClient topico;

    @Autowired
    private ModelMapper modelMapper;
    
    public List<RespostaDto> obterTodos(){
        return repository.findAll()
        		.stream()
                .map(t -> modelMapper.map(t, RespostaDto.class))
                .collect(Collectors.toList());
    }
    
    public RespostaDto obterPorId(Long id) {
        Resposta resposta = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException());

        return modelMapper.map(resposta, RespostaDto.class);
    }
    
    public RespostaDto criarResposta(RespostaDto dto) {
        Resposta resposta = modelMapper.map(dto, Resposta.class);
        resposta.setDataHora(LocalDateTime.now());
        repository.save(resposta);
        
        topico.atualizaStatus(resposta.getTopicoId());
        
        return modelMapper.map(resposta, RespostaDto.class);
    }
    
    public RespostaDto atualizarResposta(Long id, RespostaDto dto) {
    	Resposta resposta = modelMapper.map(dto, Resposta.class);
        resposta.setId(id);
        resposta = repository.save(resposta);
        return modelMapper.map(resposta, RespostaDto .class);
    }
    
    public void excluirResposta(Long id) {
        repository.deleteById(id);
    }
}
