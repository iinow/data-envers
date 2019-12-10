package com.ha.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.envers.Audited;

import lombok.Data;

@Audited
@Data
@Entity
public class Memo {

	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private Long id;
	
	private String title;
}
