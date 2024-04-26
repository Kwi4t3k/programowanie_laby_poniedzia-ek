// Klasa SolidFillShapeDecorator dziedziczy po ShapeDecorator
public class SolidFillShapeDecorator extends ShapeDecorator{
    // Prywatne pole przechowujące kolor
    private String color;

    // Konstruktor klasy SolidFillShapeDecorator
    public SolidFillShapeDecorator(Shape decoratedShape, String color) {
        // Wywołanie konstruktora klasy nadrzędnej
        super(decoratedShape);
        // Przypisanie koloru do pola klasy
        this.color = color;
    }

    // Nadpisanie metody toSvg klasy nadrzędnej
    @Override
    public String toSvg(String string) {
        // Formatowanie ciągu znaków z użyciem koloru i ciągu znaków
        String f = String.format("fill=\"%s\" %s ", color, string);
        // Wywołanie metody toSvg na dekorowanym kształcie z nowym formatowaniem
        return decoratedShape.toSvg(f);
    }
}
