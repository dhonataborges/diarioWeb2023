package com.borges.diario_eletronico.domain.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.borges.diario_eletronico.domain.model.ProfessorTurmaDisciplina;

@Repository
public interface ProfessorTurmaDisciplinaRepository extends JpaRepository<ProfessorTurmaDisciplina, Long> {

}
