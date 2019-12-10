package com.ha.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@RevisionEntity
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "revision_info")
public class RevisionInfo implements Serializable {
	private static final long serialVersionUID = 3220125582020614708L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@RevisionNumber
	@EqualsAndHashCode.Include
	private long rev;
	
	@EqualsAndHashCode.Include
	@RevisionTimestamp
	private long timestamp;
}
