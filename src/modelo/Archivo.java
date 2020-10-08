/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

/**
 *
 * @author dacastro
 */
public class Archivo {

    public static boolean guardarArchivoXML(HashMap<String, LinkedList<Punto2D>> mpfg) {
        boolean t = false;
        try {
            Element primario = new Element("figurasgeometricas");
            Document doc = new Document(primario);
            Iterator<Map.Entry<String, LinkedList<Punto2D>>> entries = mpfg.entrySet().iterator();
            while (entries.hasNext()) {
                Map.Entry<String, LinkedList<Punto2D>> elemento = entries.next();
                Element figurageometrica = new Element("figurageometrica");
                figurageometrica.setAttribute(new Attribute("nombre", elemento.getKey()));
                for (int i = 0; i < elemento.getValue().size(); i++) {
                    double valuex = elemento.getValue().get(i).getX();
                    double valuey = elemento.getValue().get(i).getY();
                    figurageometrica.addContent(new Element("punto" + (i + 1)).
                            setAttribute(new Attribute("x", String.valueOf(valuex))).
                            setAttribute(new Attribute("y", String.valueOf(valuey))));
                }
                doc.getRootElement().addContent(figurageometrica);
            }

            XMLOutputter xmloutput = new XMLOutputter();
            xmloutput.setFormat(Format.getPrettyFormat());
            xmloutput.output(doc, new FileWriter("figurasgeometricas.xml"));
            t = true;
        } catch (IOException io) {
            t = false;
            System.out.println("Error " + io.toString());
        }

        return t;
    }

    public static HashMap<String, LinkedList<Punto2D>> leerArchivo() {
        HashMap<String, LinkedList<Punto2D>> map = new HashMap<>();
        LinkedList<Punto2D> listap;
        SAXBuilder builder = new SAXBuilder();

        try {
            File xmlfile = new File("figurasgeometricas.xml");
            Document documento = (Document) builder.build(xmlfile);
            Element root = documento.getRootElement();
            List list = root.getChildren("figurageometrica");

            for (int i = 0; i < list.size(); i++) {
                listap = new LinkedList<>();
                Element datos = (Element) list.get(i);
                String nombreFigura = datos.getAttributeValue("nombre");
                System.out.println("Tabla: " + nombreFigura);

                List listapuntos = datos.getChildren();
                for (int j = 0; j < listapuntos.size(); j++) {
                    Element campo = (Element) listapuntos.get(j);
                    double x = Double.parseDouble(campo.getAttributeValue("x"));
                    double y = Double.parseDouble(campo.getAttributeValue("y"));
                    Punto2D p = new Punto2D(x, y);
                    listap.add(p);
                }
                map.put(nombreFigura, listap);

            }

        } catch (IOException io) {
            System.out.println("error " + io.toString());
        } catch (JDOMException jde) {
            System.out.println("error " + jde.toString());
        }

        return map;
    }

}
