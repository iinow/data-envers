package com.ha.response;

import org.hibernate.envers.RevisionType;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class Memo {
	
	private String title;
	
	private User registerUser;
}
