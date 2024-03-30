import java.util.Locale;

public class Ellipse extends Shape{
    private Point center;
    private double radiusX, radiusY;
    private Style style;

    public Ellipse(Style style, Point center, double radiusX, double radiusY) {
        super(style);
        this.center = center;
        this.radiusX = radiusX;
        this.radiusY = radiusY;
        this.style = style;
    }

    @Override
    public String toSvg() {
        String elipsa = String.format(Locale.ENGLISH, "<ellipse rx=\"%f\" ry=\"%f\" cx=\"%f\" cy=\"%f\"", radiusX, radiusY, center.x, center.y);
        if(style == null){
            elipsa += "style=\"fill:none;stroke:black;stroke-width:1\"";
        } else {
            elipsa += "";
            elipsa += style.toSvg();
        }
        elipsa += "/>";
        return  elipsa;
    }
}
