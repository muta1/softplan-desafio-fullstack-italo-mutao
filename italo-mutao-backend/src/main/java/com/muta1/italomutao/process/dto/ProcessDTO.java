package com.muta1.italomutao.process.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "process")
@Data
public class ProcessDTO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "name")
	private String name;
	@Column(name = "technical_opinion")
	private String technicalOpinion;
	@Column(name = "has_technical_opinion_pending")
	private Boolean hasTechnicalOpinionPending;
}
