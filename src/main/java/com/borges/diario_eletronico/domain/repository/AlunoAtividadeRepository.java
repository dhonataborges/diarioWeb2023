package com.borges.diario_eletronico.domain.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.borges.diario_eletronico.domain.model.AlunoAtividade;
import com.borges.diario_eletronico.domain.model.Atividade;

@Repository
public interface AlunoAtividadeRepository extends JpaRepository<AlunoAtividade, Long> {
	
}
