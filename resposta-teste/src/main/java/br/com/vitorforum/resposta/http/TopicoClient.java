package br.com.vitorforum.resposta.http;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("topico-ms")
public interface TopicoClient {

	 @RequestMapping(method = RequestMethod.PUT, value = "/topicos/{id}/naoresolvido")
	 void atualizaStatus(@PathVariable Long id);
}
