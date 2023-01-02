package com.borges.diario_eletronico.domain.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "tb_aluno_aula")
public class AlunoAula {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private Boolean frequencia;
	
	
	@ManyToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "aula_id")
	private Aula aula;
	
	@ManyToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "aluno_id")
	private Aluno aluno;
	
}
