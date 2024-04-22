// Definiujemy klasę wyjątku AmbiguousPersonException, która rozszerza klasę RuntimeException
public class AmbiguousPersonException extends RuntimeException{
    // Konstruktor wyjątku
    public AmbiguousPersonException(String name){
        // Wywołanie konstruktora klasy nadrzędnej z sformatowanym komunikatem o błędzie
        super(String.format("Osoba: %s pojawiła się kolejny raz w pliku.", name));
    }
}
