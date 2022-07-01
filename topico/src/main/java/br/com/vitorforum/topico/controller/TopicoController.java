package br.com.vitorforum.topico.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.vitorforum.topico.dto.StatusDto;
import br.com.vitorforum.topico.dto.TopicoDto;
import br.com.vitorforum.topico.service.TopicoService;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

	@Autowired
	private TopicoService service;

	@GetMapping()
	public List<TopicoDto> listarTodos() {
		return service.obterTodos();
	}

	@GetMapping("/{id}")
	public ResponseEntity<TopicoDto> listarPorId(@PathVariable @NotNull Long id) {
		TopicoDto dto = service.obterPorId(id);

		return ResponseEntity.ok(dto);
	}

	@PostMapping()
	public ResponseEntity<TopicoDto> realizaPedido(@RequestBody @Valid TopicoDto dto, UriComponentsBuilder uriBuilder) {
		TopicoDto topicoRealizado = service.criarTopico(dto);

		URI endereco = uriBuilder.path("/topicos/{id}").buildAndExpand(topicoRealizado.getId()).toUri();

		return ResponseEntity.created(endereco).body(topicoRealizado);
	}

	@PutMapping("/{id}/status")
     public ResponseEntity<TopicoDto> atualizaStatus(@PathVariable Long id, @RequestBody StatusDto status){
        TopicoDto dto = service.atualizaStatusTopico(id, status);

         return ResponseEntity.ok(dto);
	}
	
	@PutMapping("/{id}/resolvido")
    public ResponseEntity<Void> resolveTopico(@PathVariable @NotNull Long id) {
        service.resolveTopico(id);

        return ResponseEntity.ok().build();
    }
	
	@PutMapping("/{id}/naoresolvido")
    public ResponseEntity<Void>  naoResolveTopico(@PathVariable @NotNull Long id) {
        service.naoResolveTopico(id);

        return ResponseEntity.ok().build();
    }
}
