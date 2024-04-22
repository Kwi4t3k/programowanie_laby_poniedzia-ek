// Definiujemy klasę wyjątku NegativeLifespanException, która rozszerza klasę Exception
public class NegativeLifespanException extends Exception{
    // Konstruktor wyjątku
    public NegativeLifespanException(Person person){
        // Wywołanie konstruktora klasy nadrzędnej z sformatowanym komunikatem o błędzie
        super(String.format("%s, urodził(a) się %s, później niż umarła %s", person.getName(), person.getBirthDate(), person.getDeathDate()));
    }
}
