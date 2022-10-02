package it.mrstark.garage.entities;

import java.time.LocalDate;
import java.util.List;

import it.mrstark.garage.utilities.DatabaseConfiguration;

public abstract class ElementoMagazzino implements Record {

	private String descrizione;
	private String codice;
	private LocalDate dataAcquisto;
	private LocalDate dataVendita;
	private float costo = 0.0f;
	private float prezzo = 0.0f;
	private StatoRecord stato = StatoRecord.ACQUISTATO;
	
	public ElementoMagazzino(
		 String descrizione,
			String codice,
			LocalDate dataAcquisto,
			LocalDate dataVendita,
			float costo,
			float prezzo,
			StatoRecord stato) {
		
		this.descrizione = descrizione;
		this.codice = codice;
		this.dataAcquisto = dataAcquisto;
		this.dataVendita = dataVendita;
		this.costo = costo;
		this.prezzo = prezzo;
		this.stato = stato;
		
	}
	
	@Override
	public String getDescrizione() {
		return descrizione;
	}

	@Override
	public String getCodice() {
		return codice;
	}

	@Override
	public LocalDate getDataAcquisto() {
		return dataAcquisto;
	}

	@Override
	public LocalDate getDataVendita() {
		return dataVendita;
	}

	@Override
	public float getCosto() {
		return costo;
	}

	@Override
	public float getPrezzo() {
		return prezzo;
	}

	@Override
	public char getStato() {
		return stato.name().charAt(0);
	}
	
	@Override
	public String getCsvRecord() {
		return this.toString();
	}
	
	@Override
	public String toString() {
		String tipo = this.getTipo() + "";
		String dataVenditaString = "";
		if(dataVendita != null)
			dataVenditaString = this.dataVendita.toString();
		
		String prezzoVenditaString = "";
		if(this.getPrezzo() != 0.0)
			prezzoVenditaString = Float.toString(prezzo);
		
		List<String> toConcatenate = List.of( tipo, 
				 this.getDescrizione()
			,this.getCodice() 
			, this.dataAcquisto.toString()
			, dataVenditaString
			, Float.toString(costo)
			, prezzoVenditaString
			, this.getStato() + "");
		
		return String.join( DatabaseConfiguration.DELIMITER
				, toConcatenate);
	}

}
