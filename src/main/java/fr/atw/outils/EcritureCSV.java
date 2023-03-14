package fr.atw.outils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.opencsv.CSVWriter;

public class EcritureCSV {
	private CSVWriter ecritureCsv;
	
	private File file;
	private FileWriter fileWriter;
	
	public EcritureCSV(String path) throws FileNotFoundException {
	
		this.file = new File(path);
		try {
			this.fileWriter = new FileWriter(this.file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.ecritureCsv = new CSVWriter(this.fileWriter,
					                    ';',
					                    CSVWriter.NO_QUOTE_CHARACTER,
					                    CSVWriter.DEFAULT_ESCAPE_CHARACTER,
					                    CSVWriter.DEFAULT_LINE_END);
	}
	
	public void ecrireCsv(List<List<String>> equipesCsv) {
		try {
			String[] header = { "Nom equipe", "Nom", "Prenom", "Genre", "Formation précédente", "Site précédent" };
	        ecritureCsv.writeNext(header);
			
	        for(List<String> ligne : equipesCsv) {
	        	String[] donnees = {ligne.get(0), ligne.get(1), ligne.get(2), ligne.get(3), ligne.get(4), ligne.get(5)};
	        	ecritureCsv.writeNext(donnees);
	        }
	    
			ecritureCsv.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public File getFile() {
		return file;
	}
	
	
}
