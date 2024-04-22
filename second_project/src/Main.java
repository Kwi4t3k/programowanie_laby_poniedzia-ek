import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Inicjalizacja obiektu Person
        Person p = null;

        try {
            // Tworzenie obiektu Person z linii CSV
            p = Person.fromCsvLine("Anna Dąbrowska,07.02.1930,22.12.1991,Ewa Kowalska,Marek Kowalski");
            // Wyświetlanie obiektu Person
            System.out.println(p.toString());
            // Tworzenie listy obiektów Person z pliku CSV
            List<Person> personList = Person.fromCsv("second_project\\family.csv");

            // Przechodzenie przez listę i wyświetlanie każdego obiektu Person
            for (Person person : personList) {
                System.out.println(person);
            }
        } catch (Exception e){
            // Wyświetlanie komunikatu o błędzie, jeśli wystąpi wyjątek
            System.err.println(e.getMessage());
        }
    }
}
