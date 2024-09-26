package ficheros;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class ManejoFicherosBloque3V2 {

	public static void main(String[] args) {

		try {
			String ruta = "libros.xml"; // Ruta del archivo XML

			// Cargar el documento XML
			File inputFile = new File(ruta);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile); // Carga y analiza el archivo XML
			doc.getDocumentElement().normalize(); // Normaliza el formato del documento

			// Llamada a los métodos para realizar diferentes operaciones sobre el XML
			mostrarIDs(doc);
			mostrarAutoresYTitulos(doc);
			// mostrarTitulosYPrecios(doc);
			// mostrarLibrosPorGenero(doc);
			// traducirYGuardarXML(doc, "libros_traducidos.xml");

		} catch (Exception e) {
			e.printStackTrace(); // Captura y muestra cualquier excepción que ocurra
		}

	}

//--------------------------------------------------------------------------------------------------
	// metodo para mostrar IDs de
	private static void mostrarIDs(Document doc) {

		System.out.println("=== IDs de los libros ===");
		NodeList bookList = doc.getElementsByTagName("Book");
		for (int i = 0; i < bookList.getLength(); i++) {
			Element book = (Element) bookList.item(i);
			String id = book.getAttribute("id");
			System.out.println("ID del libro: " + id);
		}
	}

	// metodo mostrar Autores y Titulo de cada libro
	private static void mostrarAutoresYTitulos(Document doc) {
	    System.out.println("\n=== Autores y títulos de los libros ===");
	    NodeList bookList = doc.getElementsByTagName("Book");
	    for (int i = 0; i < bookList.getLength(); i++) {
	        Element book = (Element) bookList.item(i);
	        String author = book.getElementsByTagName("Author").item(0).getTextContent(); 
	        String title = book.getElementsByTagName("Title").item(0).getTextContent(); 
	        System.out.println("Autor: " + author + " | Título: " + title);
	    }
	}
	
	//mostrar Titulos y Precios
	private static void mostrarTitulosYPrecios(Document doc) {

		System.out.println("\n=== Títulos y precios ordenados ===");
		NodeList bookList = doc.getElementsByTagName("Book");
		List<Element> books = new ArrayList<Element>();

		for (int i = 0; i < bookList.getLength(); i++) {
			books.add((Element) bookList.item(i));

			// ordenar la lista de libros por precio
			books.sort((b1, b2) -> {
				double price1 = Double.parseDouble(b1.getElementsByTagName("Price").item(0).getTextContent());
				double price2 = Double.parseDouble(b2.getElementsByTagName("Price").item(0).getTextContent());
				return Double.compare(price1, price2);
			});

		}
	}
	
	private static void traducirYGuardarXML(Document doc, String fileName) {
	    try {
	        NodeList catalogList = doc.getElementsByTagName("Catalog");
	        if (catalogList.getLength() > 0) {
	            Element catalog = (Element) catalogList.item(0);
	            doc.renameNode(catalog, null, "Catalogo");

	            NodeList books = catalog.getElementsByTagName("Book");
	            for (int i = 0; i < books.getLength(); i++) {
	                Element book = (Element) books.item(i);
	                doc.renameNode(book, null, "Libro");
	                renombrarElemento(doc, book, "Title", "Titulo");
	                renombrarElemento(doc, book, "Genre", "Genero");
	                renombrarElemento(doc, book, "Price", "Precio");
	                renombrarElemento(doc, book, "Publish_date", "FechaPublicacion");
	                renombrarElemento(doc, book, "Description", "Descripción");
	                renombrarElemento(doc, book, "Author", "Autor");
	            }

	            // Guardar el archivo XML traducido
	            TransformerFactory transformerFactory = TransformerFactory.newInstance();
	            Transformer transformer = transformerFactory.newTransformer();
	            DOMSource source = new DOMSource(doc);
	            StreamResult result = new StreamResult(new File(fileName));
	            transformer.transform(source, result);

	            System.out.println("\nXML traducido y guardado como " + fileName);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	private static void renombrarElemento(Document doc, Element parent, String oldTag, String newTag) {
	    NodeList elements = parent.getElementsByTagName(oldTag);
	    if (elements.getLength() > 0) {
	        Element oldElement = (Element) elements.item(0);
	        Element newElement = doc.createElement(newTag);
	        newElement.setTextContent(oldElement.getTextContent());
	        parent.replaceChild(newElement, oldElement);
	    }
	}

	
	
	
	
	
	
	
	
}