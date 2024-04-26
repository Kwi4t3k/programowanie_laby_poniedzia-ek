public class SolidFilledPolygon extends Polygon{
    // Prywatne pole przechowujące kolor wypełnienia wielokąta
    private String color;

    // Konstruktor klasy SolidFilledPolygon, który przyjmuje tablicę punktów, kolor i styl
    // Wywołuje konstruktor klasy nadrzędnej (Polygon) i ustawia kolor
    public SolidFilledPolygon(Vec2[] vec2s, String color, Style style) {
        super(vec2s, style);
        this.color = color;
    }

    // Nadpisana metoda toSvg, która generuje reprezentację SVG wielokąta
    // Formatuje ciąg znaków, dodając do niego punkty i kolor, a następnie wywołuje metodę toSvg klasy nadrzędnej
    @Override
    public String toSvg(String string) {
        String f = String.format("<polygon points=\"%s\" %s ", color, string);
        return super.toSvg(f);
    }
}
