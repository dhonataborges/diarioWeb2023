package com.borges.diario_eletronico.domain.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "tb_turma")
public class Turma {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String descricao;
	
	@Column(nullable = false)
	private Integer sala;	
	
	@Column(nullable = false)
	private Integer anoLetivo;
	
	@JsonIgnore
	@OneToMany(mappedBy = "turma")
	private List<Matricula> matriculas = new ArrayList<>();
	
	@ManyToOne
	@JoinColumn(name = "serie_nivel_subnivel_id", nullable = false)
	private SerieNivelSubnivel serieNivelSubnivel;
}
