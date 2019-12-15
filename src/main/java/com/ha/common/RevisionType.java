package com.ha.common;

public enum RevisionType {
	
	NONE,
	ADD,
	MODIFIED,
	DELETE;
	
	public static RevisionType findRevisionType(org.hibernate.envers.RevisionType revType) {
		switch(revType) {
			case ADD: return ADD;
			case DEL: return DELETE;
			case MOD: return MODIFIED;
		}
		return NONE;
	}
}
