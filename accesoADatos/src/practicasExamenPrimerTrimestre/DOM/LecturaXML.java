package practicasExamenPrimerTrimestre.DOM;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;
import java.io.File;

public class LecturaXML {
	
	
	public static void main(String[] args) {
		
		
		try {
			// Cargar el archivo XML
			File archivoXML = new File("C:\\Users\\Usuario\\Desktop\\Orduna20242025\\accesoADatos\\src\\practicasExamenPrimerTrimestre\\libros.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(archivoXML);

			// Normalizar el documento XML
			doc.getDocumentElement().normalize();

			// Obtener todos los elementos <libro>
			NodeList listaNodos = doc.getElementsByTagName("libro");

			System.out.println("- Títulos de los libros:");
			// Recorrer la lista de <libro> y extraer los títulos
			for (int i = 0; i < listaNodos.getLength(); i++) {
				Element libro = (Element) listaNodos.item(i);
				String titulo = libro.getElementsByTagName("titulo").item(0).getTextContent();
				System.out.println("Título: " + titulo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
