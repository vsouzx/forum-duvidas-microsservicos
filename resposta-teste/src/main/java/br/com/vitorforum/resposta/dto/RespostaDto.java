package br.com.vitorforum.resposta.dto;

import java.time.LocalDateTime;

public class RespostaDto {

    private Long id;
    private LocalDateTime dataHora;
    private String mensagem;
    private Long topicoId;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	public LocalDateTime getDataHora() {
		return dataHora;
	}
	public void setDataHora(LocalDateTime dataHora) {
		this.dataHora = dataHora;
	}
	public Long getTopicoId() {
		return topicoId;
	}
	public void setTopicoId(Long topicoId) {
		this.topicoId = topicoId;
	}


}
