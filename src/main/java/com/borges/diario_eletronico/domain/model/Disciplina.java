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
@Table(name = "tb_disciplina")
public class Disciplina {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String nome;
	
	@Column(nullable = false, length = 1000)
	private String ementa;
	
    @ManyToOne()
	@JoinColumn(name = "serieNivelSubnivel_id", nullable = false)
	private SerieNivelSubnivel serieNivelSubnivel;
    
    @JsonIgnore
	@OneToMany(mappedBy = "disciplina")
	private List<ProfessorTurmaDisciplina> professorTurmaDisciplinas = new ArrayList<>();

}
