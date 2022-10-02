package it.mrstark.garage.entities;

import java.time.LocalDate;

public class Moto extends ElementoMagazzino {

	public static char TIPO = 'M';
	
	public Moto( String descrizione,
		String codice,
		LocalDate dataAcquisto,
		LocalDate dataVendita,
		float costo,
		float prezzo,
		StatoRecord stato) {
		super(descrizione, codice, dataAcquisto, dataVendita, costo, prezzo, stato);
	}
	
	@Override
	public char getTipo() {
		return TIPO;
	}
	

}
