import java.awt.*;

public class Ellipse implements Shape{
    private Point middle;
    private double radius1, radius2;
    private Style style;

    public Ellipse(Point middle, double radius1, double radius2, Style style) {
        this.middle = middle;
        this.radius1 = radius1;
        this.radius2 = radius2;
        this.style = style;
    }
}
