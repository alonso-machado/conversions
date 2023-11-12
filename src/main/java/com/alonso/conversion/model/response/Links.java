package com.alonso.conversion.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class Links {
	private String self;
	private String first;
	private String prev;
	private String next;
	private String last;
}
