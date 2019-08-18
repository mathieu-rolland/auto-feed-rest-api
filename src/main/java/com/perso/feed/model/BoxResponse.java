package com.perso.feed.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoxResponse {

	private BoxState boxState;
	private ReturnCodeEnum error;
	
}
