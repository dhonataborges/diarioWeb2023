package com.borges.diario_eletronico.domain.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.borges.diario_eletronico.domain.model.SerieNivelSubnivel;

@Repository
public interface SerieNivelSubnivelRepository extends JpaRepository<SerieNivelSubnivel, Long> {

}
