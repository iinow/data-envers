package com.ha.commonp.domain;

public interface DomainMapper {
	public <D,E> D convertToDomain(E source,Class<? extends D> classLiteral);
}

