package it.mrstark.garage.entities;

import java.time.LocalDate;

public interface Record {

	char getTipo();
	String getDescrizione();
	String getCodice();
	LocalDate getDataAcquisto();
	LocalDate getDataVendita();
	float getCosto();
	float getPrezzo();
	char getStato();

	String getCsvRecord();
	
}
