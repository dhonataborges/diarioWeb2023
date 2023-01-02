package com.borges.diario_eletronico.domain.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "tb_aula")
public class Aula {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@DateTimeFormat(iso = ISO.DATE, style = "yyyy-MM-dd")
	@Column(nullable = false)
	private LocalDate data;
	
	@DateTimeFormat(iso = ISO.TIME, style = "HH:mm")
	@Column(name = "hora_inicio", nullable = false)
	private LocalTime horaInicio;
	
	@DateTimeFormat(iso = ISO.TIME, style = "HH:mm")
	@Column(name = "hora_fim", nullable = false)
	private LocalTime horaFim;
	
	private String conteudo;
	
	@JsonIgnore
	@OneToMany(mappedBy = "aula")
	private List<AlunoAula> alunoAula;
}
