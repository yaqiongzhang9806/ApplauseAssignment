package com.example.applause.dataprovider;

import java.io.FileReader;
import java.util.List;

import org.springframework.stereotype.Component;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
@Component
public class OpenCSVReader {	
	private static final String CSV_FILE_PATH = System.getProperty("user.dir");
	public List<String[]> readAllDataAtOnce(String file) 
	{	
	    try { 
	    	String csvfile = CSV_FILE_PATH.concat("\\"+file);
	        // Create an object of file reader 
	        // class with CSV file as a parameter. 
	        FileReader filereader = new FileReader(csvfile); 
	  
	        // create csvReader object and skip first Line 
	        CSVReader csvReader = new CSVReaderBuilder(filereader) 
	                                  .withSkipLines(1) 
	                                  .build(); 
	        List<String[]> allData = csvReader.readAll();   
	        
	        return allData;
	    } 
	    catch (Exception e) { 
	        e.printStackTrace(); 
	    } 
	    return null;
	} 
}
