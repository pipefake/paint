package controladorInterfaz;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.MenuBar;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import modelo.Archivo;
import modelo.Punto2D;
import static org.jdom2.filter.Filters.document;

/**
 *
 * @author dacastro
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Canvas canvas;
    @FXML
    private MenuBar menubar;

    private GraphicsContext lienzo;

    private String figura;
    private ColorPicker colorpicker;

    HashMap<String, LinkedList<Punto2D>> mapFiguras;
    LinkedList<Punto2D> listapuntosFigura;
    Color c = null;

    @FXML
    void changecolor2(ActionEvent event) {
        c = colorpicker.getValue();

    }

    public void save() {
        WritableImage image = canvas.snapshot(new SnapshotParameters(), null);

        // TODO: probably use a file chooser here
        File file = new File("chart.png");

        try {
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
        } catch (IOException e) {
            // TODO: handle exception here
        }
    }

    @FXML
    private void crearCirculo(ActionEvent event) {
        lienzo.setLineWidth(3);
        lienzo.setStroke(Color.CHARTREUSE);
        lienzo.strokeOval(10.6, 15.3, 100, 100);
    }

    @FXML
    private void guardarArchivo(ActionEvent event) {

        boolean t = Archivo.guardarArchivoXML(mapFiguras);

        if (t) {
            JOptionPane.showMessageDialog(null, "Archivo Creado con exito");
        } else {
            JOptionPane.showMessageDialog(null, "Archivo No Creado");
        }

    }

    @FXML
    private void leerArchivo(ActionEvent event) {

        HashMap<String, LinkedList<Punto2D>> mapF = Archivo.leerArchivo();

        Iterator<Map.Entry<String, LinkedList<Punto2D>>> entries = mapF.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<String, LinkedList<Punto2D>> elemento = entries.next();
            for (int i = 0; i < elemento.getValue().size(); i++) {
                System.out.println(" Key " + elemento.getKey() + " Value "
                        + elemento.getValue().get(i).toString());

            }

        }
    }

    @FXML
    private void guardar(ActionEvent event) {
//        mapEjemplo.put(25, "Maria");
//        mapEjemplo.put(10, "David");
//        mapEjemplo.put(2, "Sandra");
//        mapEjemplo.put(150, "Rafael");
//      

        Iterator<Map.Entry<String, LinkedList<Punto2D>>> entries = mapFiguras.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<String, LinkedList<Punto2D>> elemento = entries.next();
            for (int i = 0; i < elemento.getValue().size(); i++) {
                System.out.println(" Key " + elemento.getKey() + " Value "
                        + elemento.getValue().get(i).toString());

            }

        }

    }

    @FXML
    private void crearHexagono(ActionEvent event) {
        figura = "hexagono";
    }

    private void crearHexagono(double x, double y) {
        figura = "hexagono";
        listapuntosFigura = new LinkedList<>();
        double[] coordenadasx = {x, x + 200, x + 250, x + 200, x, x - 50};
        double[] coordenadasy = {y, y, y + 100, y + 200, y + 200, y + 100};

        lienzo.setLineWidth(3);
        lienzo.setStroke(Color.DARKCYAN);
        lienzo.strokePolygon(coordenadasx, coordenadasy, 6);

        for (int i = 0; i < coordenadasy.length; i++) {
            listapuntosFigura.add(new Punto2D(coordenadasx[i], coordenadasy[i]));

        }
        mapFiguras.put("Hexagono", listapuntosFigura);
    }

    @FXML
    private void crearHeptagono(ActionEvent event) {
        figura = "Heptagono";
    }

    private void crearHeptagono(double x, double y) {
        listapuntosFigura = new LinkedList<>();
        double[] coordenadasx = {x, x + 50, x + 75, x + 50, x - 50, x - 75, x - 50};
        double[] coordenadasy = {y, y + 50, y + 100, y + 150, y + 150, y + 100, y + 50};

        lienzo.setLineWidth(3);
        lienzo.setStroke(Color.CORAL);
        lienzo.strokePolygon(coordenadasx, coordenadasy, 7);

        for (int i = 0; i < coordenadasy.length; i++) {
            listapuntosFigura.add(new Punto2D(coordenadasx[i], coordenadasy[i]));

        }
        mapFiguras.put("Heptagono", listapuntosFigura);
    }

    @FXML
    private void crearOctogono(ActionEvent event) {
        figura = "Octogono";
    }

    private void crearOctogono(double x, double y) {
        listapuntosFigura = new LinkedList<>();
        double[] coordenadasx = {x, x + 25, x + 25, x, x - 100, x - 125, x - 125, x - 100};
        double[] coordenadasy = {y, y + 25, y + 75, y + 100, y + 100, y + 75, y + 25, y};

        lienzo.setLineWidth(3);
        lienzo.setStroke(Color.SPRINGGREEN);
        lienzo.strokePolygon(coordenadasx, coordenadasy, 8);

        for (int i = 0; i < coordenadasy.length; i++) {
            listapuntosFigura.add(new Punto2D(coordenadasx[i], coordenadasy[i]));

        }
        mapFiguras.put("Octogono", listapuntosFigura);
    }

    @FXML
    private void crearRectangulo(ActionEvent event) {
        figura = "Rectangulo";
    }

    private void crearRectangulo(double x, double y) {
        listapuntosFigura = new LinkedList<>();
        double[] coordenadasx = {x, x, x + 150, x + 150};
        double[] coordenadasy = {y, y - 100, y - 100, y};

        lienzo.setLineWidth(3);
        lienzo.setStroke(c);
        lienzo.strokePolygon(coordenadasx, coordenadasy, 4);

        for (int i = 0; i < coordenadasy.length; i++) {
            listapuntosFigura.add(new Punto2D(coordenadasx[i], coordenadasy[i]));

        }
        mapFiguras.put("Rectangulo", listapuntosFigura);
    }

    @FXML
    private void crearPentagono(ActionEvent event) {
        figura = "Pentagono";
    }

    private void crearPentagono(double x, double y) {
        listapuntosFigura = new LinkedList<>();
        double[] coordenadasx = {x, x + 75, x + 50, x - 50, x - 75};
        double[] coordenadasy = {y, y + 50, y + 100, y + 100, y + 50};

        lienzo.setLineWidth(3);
        lienzo.setStroke(c);
        lienzo.strokePolygon(coordenadasx, coordenadasy, 5);

        for (int i = 0; i < coordenadasy.length; i++) {
            listapuntosFigura.add(new Punto2D(coordenadasx[i], coordenadasy[i]));

        }
        mapFiguras.put("Pentagono", listapuntosFigura);
    }

    @FXML
    private void crearCuadrado(ActionEvent event) {
        figura = "cuadrado";
    }

    @FXML
    private void crearcuadradito(MouseEvent event) {

    }

    private void crearCuadrada(double x, double y) {
        listapuntosFigura = new LinkedList<>();
        double[] coordenadasx = {x, x + 150, x, x - 150};
        double[] coordenadasy = {y, y + 100, y + 200, y + 100};

        lienzo.setLineWidth(3);
                lienzo.setStroke(c);
        lienzo.strokePolygon(coordenadasx, coordenadasy, 4);

        for (int i = 0; i < coordenadasy.length; i++) {
            listapuntosFigura.add(new Punto2D(coordenadasx[i], coordenadasy[i]));

        }
        mapFiguras.put("cuadrado", listapuntosFigura);
    }

    @FXML
    private void creaCirculo(MouseEvent event) {

        double x = event.getX();
        double y = event.getY();
        if (figura.equalsIgnoreCase("cuadrado")) {
            crearCuadrada(x, y);
        } else if (figura.equalsIgnoreCase("hexagono")) {
            crearHexagono(x, y);
        } else if (figura.equalsIgnoreCase("heptagono")) {
            crearHeptagono(x, y);
        } else if (figura.equalsIgnoreCase("Rectangulo")) {
            crearRectangulo(x, y);
        } else if (figura.equalsIgnoreCase("Pentagono")) {
            crearPentagono(x, y);
        } else if (figura.equalsIgnoreCase("Octogono")) {
            crearOctogono(x, y);
        }

    }

    @FXML
    private void eliminar(ActionEvent event) throws IOException {

        lienzo.clearRect(0, 0, 5000, 5000);
        double w = canvas.getWidth();
        double h = canvas.getHeight();

        lienzo.setLineWidth(3);
        lienzo.setStroke(Color.BLUEVIOLET);
        lienzo.strokeRect(0, 0, w, h);

    }

    @FXML
    private void crearCamino(MouseEvent event) {
        double x = event.getX();
        double y = event.getY();
        lienzo.setLineWidth(3);
                lienzo.setStroke(c);
        lienzo.strokeOval(x, y, 100, 100);

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lienzo = canvas.getGraphicsContext2D();

        double w = canvas.getWidth();
        double h = canvas.getHeight();

        lienzo.setLineWidth(3);
        lienzo.setStroke(Color.BLUEVIOLET);
        lienzo.strokeRect(0, 0, w, h);

        mapFiguras = new HashMap<>();
    }

}
