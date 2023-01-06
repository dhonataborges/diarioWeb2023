package com.borges.diario_eletronico.domain.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.borges.diario_eletronico.domain.model.Professor;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {
	@Query("SELECT obj FROM Professor obj WHERE obj.login =:login")
	Professor findByLogin(@Param("login") String login);
}
