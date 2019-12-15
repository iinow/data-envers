package com.ha.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;

import org.hibernate.envers.Audited;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Audited
@EqualsAndHashCode
@Getter
@Setter
@Entity
public class User implements Serializable {
	private static final long serialVersionUID = -3212962025983515244L;

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long id;
	
	@Email
	private String email;
	
	private String username;
	
//	@JsonIgnore
//	@OneToMany(mappedBy = "registerUser", fetch = FetchType.LAZY)
//	private List<Memo> memos = new ArrayList<>();
}
