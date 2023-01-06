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
@Table(name = "tb_professor_turma_disciplina")
public class ProfessorTurmaDisciplina {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "professor_turma_id")
	private ProfessorTurma professorTurma;
	
	@ManyToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "disciplina_id")
	private Disciplina disciplina;
	
	@Column(nullable = false)
	private Integer bimestre;
	
	@Column(name = "ano_letivo", nullable = false)
	private Integer anoLetivo;
	
}
