package fr.atw.outils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;

import jakarta.servlet.http.Part;

public class LecteurCSV {
	private CSVReader lecteurCsv;
	
	private FileReader file;
	
	private List<List<String>> output;
	
	public LecteurCSV(String path) throws FileNotFoundException {
		this.file = new FileReader(path);
		
		CSVParser parser = new CSVParserBuilder().withSeparator(';').build();
		this.lecteurCsv = new CSVReaderBuilder(this.file).withCSVParser(parser).build();
		
		this.output = new ArrayList<List<String>>();
		
		this.lireCSV();
	}
	
	public void lireCSV() {
		String[] nextline;
		try {
			while((nextline = this.lecteurCsv.readNext()) != null) {
				output.add(Arrays.asList(nextline));
			}
		} catch (CsvValidationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<List<String>> getOutput() {
		return output;
	}
	
	
}
