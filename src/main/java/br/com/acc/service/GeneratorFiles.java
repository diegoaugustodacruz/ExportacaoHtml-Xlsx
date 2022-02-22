package br.com.acc.service;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;

public interface GeneratorFiles {

	String generateHTML(String content);
	
	void saveFile(String fileName, byte[] data) throws IOException;
	
	void generatorXlsx (String table) throws Exception;
	
	void generatorOds (String table) throws Exception;
	
	void generatorPdf (String table) throws Exception;
	
	void removeEmptyRows(XSSFSheet sheet);
}
