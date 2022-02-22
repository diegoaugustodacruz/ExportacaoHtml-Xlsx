package br.com.acc.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import uk.co.certait.htmlexporter.pdf.PdfExporter;
import uk.co.certait.htmlexporter.writer.excel.ExcelExporter;
import uk.co.certait.htmlexporter.writer.ods.OdsExporter;

public class GeneratorService implements GeneratorFiles{

	@Override
	public String generateHTML(String content) {
		Properties props = new Properties();
		props.put("resource.loader", "class");
		props.put("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");

		Velocity.init(props);
		Template template = Velocity.getTemplate("ex.vm");

		VelocityContext context = new VelocityContext();
		context.put("content", content);
		Writer writer = new StringWriter();
		template.merge(context, writer);

		return writer.toString();		
	}

	@Override
	public void saveFile(String fileName, byte[] data) throws IOException {
		File file = new File(fileName);
		FileOutputStream out = new FileOutputStream(file);
		IOUtils.write(data, out);		
	}

	@Override
	public void generatorXlsx(String table) {
		String html = generateHTML(table);
		try {
			saveFile("./results/ex.html", html.getBytes());
			new ExcelExporter().exportHtml(html, new File("./results/ex.xlsx"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
	}

	@Override
	public void generatorOds(String table){
		String html = generateHTML(table);
		try {
			saveFile("./results/ex.html", html.getBytes());
			new OdsExporter().exportHtml(html, new File("./results/ex.ods"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void generatorPdf(String table){
		String html = generateHTML(table);
		try {
			saveFile("./results/ex.html", html.getBytes());
			new PdfExporter().exportHtml(html, new File("./results/ex.pdf"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void removeEmptyRows(XSSFSheet sheet) {
	    Boolean isRowEmpty = Boolean.FALSE;
	    for(int i = 0; i <= sheet.getLastRowNum(); i++){
	      if(sheet.getRow(i)==null){
	        isRowEmpty=true;
	        sheet.shiftRows(i + 1, sheet.getLastRowNum()+1, -1);
	        i--;
	        continue;
	      }
	      for(int j =0; j<sheet.getRow(i).getLastCellNum();j++){
	        if(sheet.getRow(i).getCell(j) == null || 
	        sheet.getRow(i).getCell(j).toString().trim().equals("")){
	          isRowEmpty=true;
	        }else {
	          isRowEmpty=false;
	          break;
	        }
	      }
	      if(isRowEmpty==true){
	        sheet.shiftRows(i + 1, sheet.getLastRowNum()+1, -1);
	        i--;
	      }
	    }
	}

}
