package com.ha.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.envers.Audited;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Audited
@Getter
@Setter
@EqualsAndHashCode
@Entity
public class Memo implements Serializable {
	private static final long serialVersionUID = -2071781267268010096L;

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long id;
	
	private String title;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "userId", referencedColumnName = "id", nullable = false)
	private User registerUser;
}
