package com.muta1.italomutao.technicalOpinion.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.muta1.italomutao.process.entity.Process;
import com.muta1.italomutao.user.entity.User;

import lombok.Data;
import lombok.experimental.Accessors;

@Entity
@Table(name = "technical_opinion")
@Data
@Accessors(chain = true)
public class TechnicalOpinion {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "has_technical_opinion_pending")
	private Boolean hasTechnicalOpinionPending;
	@Column(name = "technical_opinion")
	private String technicalOpinion;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "process", referencedColumnName = "id", nullable = false)
	private Process process;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user", referencedColumnName = "id", nullable = false)
	private User user;
	
	
}
