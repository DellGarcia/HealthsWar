package br.com.healthswar.comunication;

import java.io.Serializable;

public enum Response implements Serializable {
	MATCH_FOUND,
	MATCH_NOT_FOUND,
	MATCH_READY,
	SUCCESSFULLY_REGISTERED,
	SUCCESSFULLY_UPDATED,
	SUCCESSFULLY_DELETED
}
