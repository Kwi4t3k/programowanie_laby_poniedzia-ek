// Definiujemy klasę wyjątku UndefinedParentException, która rozszerza klasę Exception
public class UndefinedParentException extends Exception{
    // Konstruktor wyjątku
    public UndefinedParentException(Person person, String parentName){
        // Wywołanie konstruktora klasy nadrzędnej z sformatowanym komunikatem o błędzie
        super(String.format("Dla osoby: %s, nie znaleziono rodzica: %s", person.getName(), parentName));
    }
}
