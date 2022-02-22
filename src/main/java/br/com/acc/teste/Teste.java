package br.com.acc.teste;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.util.SystemOutLogger;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import br.com.acc.domain.ConteudoHtml;
import br.com.acc.service.GeneratorFiles;
import br.com.acc.service.GeneratorService;

public class Teste {

	public static void main(String[] args) throws Exception {

		ConteudoHtml html = new ConteudoHtml(
				"<!DOCTYPE html> <html> <head> #include(\"./report.css\") </head> <body> <table style=\"width: 100%; page-break-before: avoid\" data-new-sheet=\"true\"> <thead> <tr><td></td><td></td><td></td><td></td></tr><tr><td></td><td></td><td></td><td></td></tr> <tr> <th>ECGE</th> <th>Janeiro</th> <th>Fevereiro</th> <th>Marco</th> </tr> </thead> <tbody> <tr> <td>Salario a Pagar</td> <td>2000</td> <td>3000</td> <td>4000</td> </tr> <tr> <td>INSS</td> <td>50</td> <td>60</td> <td>70</td> </tr> </tbody> </table> <br/> <table style=\"width: 100%; page-break-before: always\" data-new-sheet=\"true\"> <thead> <tr> <th>ECGE</th> <th>Janeiro</th> <th>Fevereiro</th> <th>Marco</th> </tr> </thead> <tbody> <tr> <td>Salario a Pagar</td> <td>2000</td> <td>3000</td> <td>4000</td> </tr> <tr> <td>INSS</td> <td>50</td> <td>60</td> <td>70</td> </tr> </tbody> </table> </body> </html>");
		GeneratorFiles gn = new GeneratorService();

		gn.generateHTML(html.getHtml());

		gn.saveFile("./results/ex.html", html.getHtml().getBytes());

		gn.generatorXlsx(html.getHtml());

//		XSSFSheet

		File file = new File("./results/ex.xlsx"); // creating a new file instance
		FileInputStream fis = new FileInputStream(file); // obtaining bytes from the file

		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheetAt(0);
		System.out.println("numeros de linhas da planilha: " + sheet.getPhysicalNumberOfRows());

		for (int i = 0; i <= sheet.getPhysicalNumberOfRows(); i++) {
			Row row = sheet.getRow(i);

			System.out.println("numeros de colunas da linha " + i + " " + row.getPhysicalNumberOfCells());
			boolean ver = false;
			for (int j = 0; j < row.getPhysicalNumberOfCells(); j++) {
				Cell cell = row.getCell(j);
//				if(cell.getCellType() == cell.CELL_TYPE_STRING) {
//					System.out.println(cell.getStringCellValue());
//					
//				}
//				if(cell.getCellType() == cell.CELL_TYPE_NUMERIC) {
//					System.out.println(cell.getNumericCellValue());
//					
//				}
//				if (cell.getStringCellValue().equals("")) {
//					ver = true;
//				} else {
//					ver = false;
//					break;
//				}
//
			}

//			if (ver == true) {
//				sheet.removeRow(row);
//			}
//			System.out.println(ver);
		}

		FileOutputStream outputStream = new FileOutputStream("./results/ex22.xlsx");
		wb.write(outputStream);

	}

}
