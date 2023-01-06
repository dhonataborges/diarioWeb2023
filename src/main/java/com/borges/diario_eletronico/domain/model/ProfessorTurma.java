package com.borges.diario_eletronico.domain.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "tb_professor_turma")
public class ProfessorTurma {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "turma_id")
	private Turma turma;
	
	@ManyToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "professor_id")
	private Professor professor;
	
	@DateTimeFormat(iso = ISO.DATE, style = "yyyy-MM-dd")
	@Column(nullable = false)
	private LocalDate dataAtribuicao;
	
	@Column(nullable = false)
	private Integer status;
	
	@JsonIgnore
	@OneToMany(mappedBy = "professorTurma")
	private List<ProfessorTurmaDisciplina> professorTurmaDisciplinas = new ArrayList<>();
	
}
