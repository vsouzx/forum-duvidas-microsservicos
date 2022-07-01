package br.com.vitorforum.topico.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.vitorforum.topico.dto.StatusDto;
import br.com.vitorforum.topico.dto.TopicoDto;
import br.com.vitorforum.topico.modelo.Status;
import br.com.vitorforum.topico.modelo.Topico;
import br.com.vitorforum.topico.repository.TopicoRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TopicoService {
	
	@Autowired
    private TopicoRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    
    public List<TopicoDto> obterTodos() {
        return repository.findAll().stream()
                .map(t -> modelMapper.map(t, TopicoDto.class))
                .collect(Collectors.toList());
    }
    
    public TopicoDto obterPorId(Long id) {
        Topico topico = repository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        return modelMapper.map(topico, TopicoDto.class);
    }
    
    public TopicoDto criarTopico(TopicoDto dto) {
        Topico topico = modelMapper.map(dto, Topico.class);

        topico.setDataHora(LocalDateTime.now());
        topico.setStatus(Status.ABERTO_SEM_RESPOSTA);
        Topico salvo = repository.save(topico);

        return modelMapper.map(topico, TopicoDto.class);
    }
    
    
    public TopicoDto atualizaStatusTopico(Long id, StatusDto dto) {

        Topico topico = repository.getById(id);

        if (topico == null) {
            throw new EntityNotFoundException();
        }

        topico.setStatus(dto.getStatus());
        repository.atualizaStatus(Status.NAO_RESOLVIDO, topico);
        return modelMapper.map(topico, TopicoDto.class);
    }
    
    public void resolveTopico(Long id) {

        Topico topico = repository.getById(id);

        if (topico == null) {
            throw new EntityNotFoundException();
        }

        topico.setStatus(Status.RESOLVIDO);
        repository.save(topico);
    }
    
    public void naoResolveTopico(Long id) {

        Topico topico = repository.getById(id);

        if (topico == null) {
            throw new EntityNotFoundException();
        }

        topico.setStatus(Status.NAO_RESOLVIDO);
        repository.save(topico);
    }
}
