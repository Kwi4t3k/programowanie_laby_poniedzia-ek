import java.util.Locale;

public class Ellipse implements Shape {
    private Vec2 center;
    private double radiusX, radiusY;

    public Ellipse(Vec2 center, double radiusX, double radiusY) {
        this.center = center;
        this.radiusX = radiusX;
        this.radiusY = radiusY;
    }

    @Override
    public String toSvg(String param) {
        return String.format(Locale.ENGLISH,"<ellipse rx=\"%f\" ry=\"%f\" cx=\"%f\" cy=\"%f\" %s/>",
                radiusX,radiusY,center.x,center.y,param);
    }
}
