package it.mrstark.garage.entities;

import it.mrstark.garage.utilities.ValoriContabili;

public class Ricambio extends ElementoMagazzino {

	public static float RINCARO_RICAMBI = 0.33f;
	public static char TIPO = 'R';
	
	@Override
	public char getTipo() {
		return TIPO;
	}

	@Override
	public float getPrezzo() {
		return this.getCosto() * ValoriContabili.IVA * RINCARO_RICAMBI;
	}
	
}
