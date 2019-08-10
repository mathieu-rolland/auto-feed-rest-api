package com.perso.feed.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoxResponseDTO {

	private BoxStateDTO boxState;
	private ErrorDescriptionDTO errorDescription;
	
}
