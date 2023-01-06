package com.borges.diario_eletronico.domain.model;

import java.time.LocalDate;
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
@Table(name = "tb_atividade")
public class Atividade {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "data_criacao" ,nullable = false)
	@DateTimeFormat(iso = ISO.DATE, style = "yyyy-MM-dd")
	private LocalDate dataCriacao;
	
	@Column(name = "data_entrega" ,nullable = false)
	@DateTimeFormat(iso = ISO.DATE, style = "yyyy-MM-dd")
	private LocalDate dataEntrega;
	
	@Column(name = "nota_maxima" , nullable = false)
	private Double notaMaxima;
	
	@Column(nullable = false)
	private String descricao;
	
	@Column(nullable = false)
	private String tipo;
	
	@JsonIgnore
	@OneToMany(mappedBy = "atividade")
	private List<AlunoAtividade> alunoAtividades;
	
}
