package it.mrstark.garage.moduli;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.Writer;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import it.mrstark.garage.entities.ElementoMagazzino;
import it.mrstark.garage.entities.Moto;
import it.mrstark.garage.entities.Ricambio;
import it.mrstark.garage.entities.StatoRecord;
import it.mrstark.garage.utilities.DatabaseConfiguration;

public class Database {
	
	public static Database INSTANCE = new Database();
	
	public File open() throws FileNotFoundException {
		return new File(DatabaseConfiguration.fileName);
	}
	
	public String[] readAll(File file) throws FileNotFoundException {
		
		List<String> allLines = new ArrayList<String>();
		Scanner reader = new Scanner(file);
		while(reader.hasNextLine()) {
			allLines.add(reader.nextLine());
		}
		reader.close();
		
		return (String[])allLines.toArray();
	}
	
	public Moto[] readAllMoto(File file)  throws FileNotFoundException, InvalidObjectException {
		List<Moto> allLines = new ArrayList<Moto>();
		Scanner reader = new Scanner(file);
		while(reader.hasNextLine()) {
			String line = reader.nextLine();
			if(line.length() > 0 && line.charAt(0) == Moto.TIPO) {
				allLines.add(getNewMoto(line));	
			}
		}
		reader.close();
		
		return (Moto[])allLines.toArray();
	}
	
	public Ricambio[] readAllRicambi(File file)  throws FileNotFoundException, InvalidObjectException {
		List<Ricambio> allLines = new ArrayList<Ricambio>();
		Scanner reader = new Scanner(file);
		while(reader.hasNextLine()) {
			String line = reader.nextLine();
			if(line.length() > 0 && line.charAt(0) == Ricambio.TIPO) {
				allLines.add(getNewRicambio(line));	
			}
		}
		reader.close();
		
		return (Ricambio[])allLines.toArray();
	}

	private Moto getNewMoto(String line) throws InvalidObjectException {
		String tipo;
		String descrizione = null;
		String codice = null;
		LocalDate dataAcquisto = null;
		LocalDate dataVendita = null;
		float costo = 0.0f;
		float prezzo = 0.0f;
		StatoRecord stato = StatoRecord.NESSUNO;
        int index = 0;
		String[] splitted = line.split(DatabaseConfiguration.DELIMITER);
		
		tipo = splitted[index++];
		if(!tipo.equals(Moto.TIPO + "") ) {
			throw new InvalidObjectException("Il tipo di dati non è relativo ad una moto");
		}
		
		if(splitted.length > index)
			descrizione = splitted[index++];
		
		if(splitted.length > index)
			codice= splitted[index++];
		
		if(splitted.length > index)
			dataAcquisto= LocalDate.parse(splitted[index++]);
		
		if(splitted.length > index)
			dataVendita= LocalDate.parse(splitted[index++]);
		
		if(splitted.length > index)
			costo = Float.parseFloat( splitted[index++]);
		
		if(splitted.length > index)
			prezzo = Float.parseFloat( splitted[index++]);
		
		if(splitted.length > index)
			stato = StatoRecord.valueOf(splitted[index++]);
		
		Moto moto = new Moto(descrizione, codice, dataAcquisto, dataVendita, costo, prezzo, stato);
		return moto;
	}

	private Ricambio getNewRicambio(String line) throws InvalidObjectException {
		String tipo;
		String descrizione = null;
		String codice = null;
		LocalDate dataAcquisto = null;
		LocalDate dataVendita = null;
		float costo = 0.0f;
		float prezzo = 0.0f;
		StatoRecord stato = StatoRecord.NESSUNO;
        int index = 0;
		String[] splitted = line.split(DatabaseConfiguration.DELIMITER);
		
		tipo = splitted[index++];
		if(!tipo.equals(Ricambio.TIPO + "") ) {
			throw new InvalidObjectException("Il tipo di dati non è relativo ad un ricambio");
		}
		
		if(splitted.length > index)
			descrizione = splitted[index++];
		
		if(splitted.length > index)
			codice= splitted[index++];
		
		if(splitted.length > index)
			dataAcquisto= LocalDate.parse(splitted[index++]);
		
		if(splitted.length > index)
			dataVendita= LocalDate.parse(splitted[index++]);
		
		if(splitted.length > index)
			costo = Float.parseFloat( splitted[index++]);
		
		if(splitted.length > index)
			prezzo = Float.parseFloat( splitted[index++]);
		
		if(splitted.length > index)
			stato = StatoRecord.valueOf(splitted[index++]);
		
		Ricambio moto = new Ricambio(descrizione, codice, dataAcquisto, dataVendita, costo, prezzo, stato);
		return moto;
	}
	
	public void aggiungi(ElementoMagazzino motoORicambio) throws FileNotFoundException, Exception {
		
		File file = open();
		
		if(file.exists()) 
			file.createNewFile();
		
		if(!file.canWrite()) {
			throw new Exception("Il database non supporta la scrittura");
		}
		
		append(file, motoORicambio.getCsvRecord());
		
	}
	
	private void append(File file, String linea) throws IOException {
		FileWriter writer = new FileWriter(file, true);
		Writer output = new BufferedWriter(writer);
		output.write(linea);
		output.flush();
		output.close();
		
	}
	
	public void modifica(ElementoMagazzino motoORicambio)throws FileNotFoundException, Exception {
		
		File database = open();
		String[] lines = readAll(database);
		List<String> nuoveLinee = new ArrayList<String>();
		for(int i = 0; i<lines.length; i++) {
				
			String codice = getCodice(lines[i]);
			if(codice.equals(motoORicambio.getCodice())) {
				nuoveLinee.add(motoORicambio.getCsvRecord());
				
			} else {
				nuoveLinee.add(lines[i]);
			}
			
		}
		
	}
	
	private String getCodice(String linea) throws InvalidObjectException {
		ElementoMagazzino elemento;
		if(linea.charAt(0) == Moto.TIPO) {
			elemento = getNewMoto(linea);
		} else {
			elemento = getNewRicambio(linea);
		}
			
		return elemento.getCodice();
		
	}
	
}
