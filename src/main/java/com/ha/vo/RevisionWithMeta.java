package com.ha.vo;

import com.ha.common.RevisionType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class RevisionWithMeta<T> {

	private T value;
	
	private RevisionType revType;
}
