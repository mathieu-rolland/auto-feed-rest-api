package com.perso.feed.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCodeEnum {

	NO_ERROR(0 , null),
	ALREADY_OPEN( 1 , "Le tiroir est déjà ouvert."),
	ALREADY_CLOSED( 2 , "Le tiroir est déjà fermé."),
	FAILED_TO_OPEN( 3 , "Erreur lors de l'ouverture du tiroir."),
	FAILED_TO_CLOSE( 4 , "Erreur lors de la fermeture du tiroir."),
	CAMERA_ALREADY_CLOSED( 5 , "La camera n'est pas démarrée." ),
	CAMERA_ALREADY_STARTED( 6 , "La camera est déjà démarrée." );
	
	private int errorCode;
	private String message;
	
}
