package com.perso.feed.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ReturnCodeEnum {

	NO_ERROR(0 , null),
	DRAWER_OPENNING( 0 , "Tiroir en cours d'ouverture"),
	DRAWER_CLOSING( 0 , "Tiroir en cours de fermerture"),
	ALREADY_OPEN( 1 , "Le tiroir est déjà ouvert."),
	ALREADY_CLOSED( 2 , "Le tiroir est déjà fermé."),
	FAILED_TO_OPEN( 3 , "Erreur lors de l'ouverture du tiroir."),
	FAILED_TO_CLOSE( 4 , "Erreur lors de la fermeture du tiroir."),
	CAMERA_STARTED( 0 , "Camera démarrée avec succès" ),
	CAMERA_STOPPED( 0 , "Camera arrêtée avec succès"),
	CAMERA_ALREADY_CLOSED( 7 , "La camera n'est pas démarrée." ),
	CAMERA_ALREADY_STARTED( 8 , "La camera est déjà démarrée." ),
	UNKNOWN_DRAWER( 9 , "Numero de tiroir incorrecte." );
	
	private int errorCode;
	private String message;
	
}
