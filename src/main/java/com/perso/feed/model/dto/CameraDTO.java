package com.perso.feed.model.dto;

import com.perso.feed.model.CameraStateEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CameraDTO {

	private CameraStateEnum state;
	
}
