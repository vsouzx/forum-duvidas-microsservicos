package br.com.vitorforum.resposta.modelo;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "respostas")
public class Resposta {

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private LocalDateTime dataHora = LocalDateTime.now();

    @NotNull
    private String mensagem;
    
    private Long topicoId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDataHora() {
		return dataHora;
	}

	public void setDataHora(LocalDateTime dataHora) {
		this.dataHora = dataHora;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}


	public Resposta(Long id, @NotNull LocalDateTime dataHora, @NotNull String mensagem, Long topicoId) {
		super();
		this.id = id;
		this.dataHora = dataHora;
		this.mensagem = mensagem;
		this.topicoId = topicoId;
	}

	public Resposta() {
		super();
	}

	public Long getTopicoId() {
		return topicoId;
	}

	public void setTopicoId(Long topicoId) {
		this.topicoId = topicoId;
	}
    
    
}

