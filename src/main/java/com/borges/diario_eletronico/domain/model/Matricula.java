package com.borges.diario_eletronico.domain.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "tb_matricula" )
public class Matricula {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String codMat;
	
	@MapsId
    @OneToOne
    @JoinColumn(name = "aluno_id")
	private Aluno aluno;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "turma_id", nullable = false)
	private Turma turma;
	
}
