import java.util.Locale;

// Klasa Ellipse implementuje interfejs Shape
public class Ellipse implements Shape {
    // Prywatne zmienne przechowujące informacje o elipsie
    private Vec2 center;
    private double radiusX, radiusY;

    // Konstruktor klasy Ellipse
    public Ellipse(Vec2 center, double radiusX, double radiusY) {
        this.center = center;
        this.radiusX = radiusX;
        this.radiusY = radiusY;
    }

    // Implementacja metody toSvg z interfejsu Shape
    @Override
    public String toSvg(String param) {
        // Formatujemy stringa dodając do niego parametry elipsy
        return String.format(Locale.ENGLISH,"<ellipse rx=\"%f\" ry=\"%f\" cx=\"%f\" cy=\"%f\" %s/>",
                radiusX,radiusY,center.x,center.y,param);
    }
}
