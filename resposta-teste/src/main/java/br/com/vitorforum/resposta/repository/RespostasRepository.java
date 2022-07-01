package br.com.vitorforum.resposta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.vitorforum.resposta.modelo.Resposta;

@Repository
public interface RespostasRepository extends JpaRepository<Resposta, Long>{

}
