package com.gestionComprasVentas.dao;

import java.io.FileWriter;
import java.io.IOException;
import java.text.Format;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.Document;

import org.w3c.dom.Element;

import com.gestionComprasVentas.modelo.Compra;
import com.gestionComprasVentas.modelo.Producto;


public class CompraDAO {
    private List<Compra> compras;

    public CompraDAO() {
        compras = new ArrayList<>();
    }

    public void agregarCompra(Compra compra) {
        compras.add(compra);
    }

    public void eliminarCompra(int id) {
        compras.removeIf(c -> c.getId() == id);
    }

    public List<Compra> getCompras() {
        return compras;
    }

    public void guardarCompras() {
        try {
            Element root = new Element("compras");
            Document doc = new Document(root);

            for (Compra compra : compras) {
                Element compraElement = new Element("compra");

                compraElement.addContent(new Element("id").setText(String.valueOf(compra.getId())));
                compraElement.addContent(new Element("productoId").setText(String.valueOf(compra.getProducto().getId())));
                compraElement.addContent(new Element("cantidad").setText(String.valueOf(compra.getCantidad())));
                compraElement.addContent(new Element("fecha").setText(String.valueOf(compra.getFecha().getTime())));

                root.addContent(compraElement);
            }

            XMLOutputter xmlOutput = new XMLOutputter();
            xmlOutput.setFormat(Format.getPrettyFormat());
            xmlOutput.output(doc, new FileWriter("compras.xml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void cargarCompras(List<Producto> productos) {
        // Implementación para cargar compras desde compras.xml
        // Se debe relacionar el producto por su id
    }
}
