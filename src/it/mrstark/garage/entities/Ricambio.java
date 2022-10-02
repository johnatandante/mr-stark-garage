package it.mrstark.garage.entities;

import java.time.LocalDate;

import it.mrstark.garage.utilities.ValoriContabili;

public class Ricambio extends ElementoMagazzino {

	public static float RINCARO_RICAMBI = 0.33f;
	public static char TIPO = 'R';
	
	public Ricambio( String descrizione,
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

	@Override
	public float getPrezzo() {
		return this.getCosto() * ValoriContabili.IVA * RINCARO_RICAMBI;
	}
	
}
