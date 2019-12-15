package com.ha.response;

import lombok.Setter;

import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;

@AllArgsConstructor
@EqualsAndHashCode
@Setter
@Getter
@Builder
public class DefaultResponse {

	private boolean success = true;
	
	private Object data;
}
