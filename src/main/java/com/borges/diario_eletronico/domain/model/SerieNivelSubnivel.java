package com.borges.diario_eletronico.domain.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "tb_serie_nivel_subnivel")
public class SerieNivelSubnivel {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private Integer nivel;
	
	@Column(nullable = false)
	private Integer subnivel;
	
	@Column(nullable = false)
	private String descricao;
	
	@JsonIgnore
	@OneToMany(mappedBy = "serieNivelSubnivel")
	private List<Disciplina> disciplinas = new ArrayList<>();
    
}
