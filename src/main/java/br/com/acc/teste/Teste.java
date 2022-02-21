package br.com.acc.teste;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Properties;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.io.IOUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.xml.sax.SAXException;

import uk.co.certait.htmlexporter.writer.excel.ExcelExporter;
import uk.co.certait.htmlexporter.writer.ods.OdsExporter;



public class Teste {

	public static void main(String[] args) throws SAXException,
    IOException, ParserConfigurationException  {

//		File xmlFile = new File("src/main/resources/league.xml");
//		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//        DocumentBuilder dBuilder = factory.newDocumentBuilder();
//        Document doc = dBuilder.parse(xmlFile);
//        
//        doc.getDocumentElement().normalize();
//
//        System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
//        
//        
//        NodeList nList = doc.getElementsByTagName("table");
//        
//        System.out.println(nList);

//        for (int i = 0; i < nList.getLength(); i++) {
//
//            Node nNode = nList.item(i);
//
//            System.out.println("\nCurrent Element: " + nNode.getNodeName());
//
//            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
//
//                Element elem = (Element) nNode;
//
//                String uid = elem.getAttribute("id");
//
//                Node node1 = elem.getElementsByTagName("firstname").item(0);
//                String fname = node1.getTextContent();
//
//                Node node2 = elem.getElementsByTagName("lastname").item(0);
//                String lname = node2.getTextContent();
//
//                Node node3 = elem.getElementsByTagName("occupation").item(0);
//                String occup = node3.getTextContent();
//
//                System.out.printf("User id: %s%n", uid);
//                System.out.printf("First name: %s%n", fname);
//                System.out.printf("Last name: %s%n", lname);
//                System.out.printf("Occupation: %s%n", occup);
//                
//            }
//        }       
        
               

		
		String base = "<table> <thead> <tr> <th>ECGE</th> <th>Janeiro</th> <th>Fevereiro</th> <th>Marco</th> </tr> </thead> <tbody> <tr> <td>Salario a Pagar</td> <td>2000</td> "
				+ "<td>3000</td> <td>4000</td> </tr> <tr> <td>INSS</td> <td>50</td> <td>60</td> <td>70</td> </tr> </tbody> </table>";
		String html = generateHTML(base);
		saveFile("league.html", html.getBytes());

		new ExcelExporter().exportHtml(html, new File("./results/league.xlsx"));
		new OdsExporter().exportHtml(html, new File("./results/league.ods"));
	}


	private static String generateHTML(String content) {
		Properties props = new Properties();
		props.put("resource.loader", "class");
		props.put("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");

		Velocity.init(props);
		Template template = Velocity.getTemplate("league.vm");

		VelocityContext context = new VelocityContext();
		context.put("content", content);
		Writer writer = new StringWriter();
		template.merge(context, writer);

		return writer.toString();
		
	}

	public static void saveFile(String fileName, byte[] data) throws IOException {
		File file = new File(fileName);
		FileOutputStream out = new FileOutputStream(file);
		IOUtils.write(data, out);
	}
}
