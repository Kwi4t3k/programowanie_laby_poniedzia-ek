// Klasa ShapeDecorator implementuje interfejs Shape
public class ShapeDecorator implements Shape{
    // Zmienna chroniona typu Shape, która przechowuje dekorowany kształt
    protected Shape decoratedShape;

    // Konstruktor klasy ShapeDecorator przyjmujący jako argument obiekt typu Shape
    public ShapeDecorator(Shape decoratedShape) {
        // Przypisanie argumentu do zmiennej chronionej
        this.decoratedShape = decoratedShape;
    }

    // Metoda toSvg z interfejsu Shape, która jest nadpisywana w tej klasie
    @Override
    public String toSvg(String string) {
        // Wywołanie metody toSvg na dekorowanym kształcie
        return decoratedShape.toSvg(string);
    }
}
