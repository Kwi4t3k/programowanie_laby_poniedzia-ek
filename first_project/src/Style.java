import java.util.Locale;

// Klasa Style reprezentuje styl kształtu w SVG
public class Style {
    // Kolor wypełnienia kształtu
    public String fillColor;
    // Kolor linii kształtu
    public String strokeColor;
    // Szerokość linii kształtu
    public double strokeWidth;

    // Konstruktor klasy Style
    public Style(String fillColor, String strokeColor, double strokeWidth) {
        this.fillColor = fillColor;
        this.strokeColor = strokeColor;
        this.strokeWidth = strokeWidth;
    }

    // Konstruktor kopiujący klasy Style
    public Style(Style style) {
        this.fillColor = fillColor;
        this.strokeColor = strokeColor;
        this.strokeWidth = strokeWidth;
    }

    // Metoda konwertująca styl do formatu SVG
    public String toSvg(){
        // Formatowanie atrybutów stylu SVG
        String styleFor = String.format(Locale.ENGLISH, "style=\"stroke:%s;fill:%s;stroke-width:%f\"", strokeColor, fillColor, strokeWidth);
        return styleFor;
    }
}
