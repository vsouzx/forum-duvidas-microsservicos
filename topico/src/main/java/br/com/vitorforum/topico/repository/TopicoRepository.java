package br.com.vitorforum.topico.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.vitorforum.topico.modelo.Status;
import br.com.vitorforum.topico.modelo.Topico;

@Repository
public interface TopicoRepository extends JpaRepository<Topico, Long> {

	@Transactional
    @Modifying(clearAutomatically = true)
    @Query("update Topico t set t.status = :status where p = :topico")
    void atualizaStatus(Status status, Topico topico);
}
