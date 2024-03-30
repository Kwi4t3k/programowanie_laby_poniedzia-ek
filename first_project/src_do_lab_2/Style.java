import java.util.Locale;

public class Style {
    public final String fillColor, strokeColor;
    public final double strokeWidth;
    public Style(String fillColor, String strokeColor, double strokeWidth) {
        this.fillColor = fillColor;
        this.strokeColor = strokeColor;
        this.strokeWidth = strokeWidth;
    }
    public String toSvg(){
        String styleFor = String.format(Locale.ENGLISH, "style=\"stroke:%s;fill:%s;stroke-width:%f\"", strokeColor, fillColor, strokeWidth);
        return styleFor;
    }
}
