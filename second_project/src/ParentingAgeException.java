// Definiujemy klasę wyjątku ParentingAgeException, która rozszerza klasę Exception
public class ParentingAgeException extends Exception{
    // Osoba, której dotyczy wyjątek
    public final Person person;

    // Metoda pomocnicza do formatowania danych osoby
    private static String personAndLifespan(Person person){
        // Zwraca sformatowany ciąg znaków z imieniem osoby i datami urodzenia i śmierci (jeśli istnieją)
        return String.format("%s (%s%s)", person.getName(), person.getBirthDate(), person.getDeathDate() == null ? "" : " - " + person.getDeathDate());
    }

    // Konstruktor wyjątku
    public ParentingAgeException(Person person, Person parent){
        // Wywołanie konstruktora klasy nadrzędnej z sformatowanym komunikatem o błędzie
        super(String.format("Osoba %s nie może być rodzicem %s.", personAndLifespan(parent), personAndLifespan(person)));
        // Przypisanie osoby do pola klasy
        this.person = person;
    }
}
