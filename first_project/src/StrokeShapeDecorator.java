import java.util.Locale;

// Klasa StrokeShapeDecorator dziedziczy po klasie ShapeDecorator
public class StrokeShapeDecorator extends ShapeDecorator{
    // Kolor kształtu
    private String color;
    // Szerokość linii kształtu
    private double width;

    // Konstruktor klasy StrokeShapeDecorator
    public StrokeShapeDecorator(Shape decoratedShape, String color, double width) {
        // Wywołanie konstruktora klasy nadrzędnej
        super(decoratedShape);
        // Przypisanie koloru
        this.color = color;
        // Przypisanie szerokości
        this.width = width;
    }
    // Metoda konwertująca kształt do formatu SVG
    public String toSvg(String string) {
        // Formatowanie atrybutów SVG
        String f = String.format(Locale.ENGLISH, "stroke=\"%s\" stroke-width=\"%f\" ", color, width);
        // Zwraca kształt w formacie SVG z dodanymi atrybutami
        return decoratedShape.toSvg(f);
    }
}
