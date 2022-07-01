package br.com.vitorforum.resposta.controller;

import java.awt.print.Pageable;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.vitorforum.resposta.dto.RespostaDto;
import br.com.vitorforum.resposta.service.RespostaService;

@RestController
@RequestMapping(value = "/respostas")
public class RespostaController {

	@Autowired
	private RespostaService service;

	@RequestMapping(method = RequestMethod.GET)
	public List<RespostaDto> listar() {
		return service.obterTodos();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<RespostaDto> detalhar(@PathVariable @NotNull Long id) {
		RespostaDto dto = service.obterPorId(id);

		return ResponseEntity.ok(dto);
	}
	
	@RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<RespostaDto> cadastrar(@RequestBody @Valid RespostaDto dto, UriComponentsBuilder uriBuilder){
		RespostaDto resposta = service.criarResposta(dto);
		
        URI endereco = uriBuilder.path("/respostas/{id}").buildAndExpand(resposta.getId()).toUri();

        return ResponseEntity.created(endereco).body(resposta);
    }
	
	@RequestMapping(value = "/atualizar/{id}", method = RequestMethod.PUT)
    public ResponseEntity<RespostaDto> atualizar(@PathVariable @NotNull Long id, @RequestBody @Valid RespostaDto dto) {
        RespostaDto atualizado = service.atualizarResposta(id, dto);
        return ResponseEntity.ok(atualizado);
    }
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<RespostaDto> remover(@PathVariable @NotNull Long id) {
        service.excluirResposta(id);
        return ResponseEntity.noContent().build();
    }
}
