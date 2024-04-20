import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Person {
    private String name;
    private LocalDate birthDate, deathDate;
    private Person mother, father;

    public String getName() {
        return name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public LocalDate getDeathDate() {
        return deathDate;
    }

    public Person(String name, LocalDate birthDate, LocalDate deathDate, Person mother, Person father) {
        this.name = name;
        this.birthDate = birthDate;
        this.deathDate = deathDate;
        this.mother = mother;
        this.father = father;
    }
    public Person(String name, LocalDate birthDate, LocalDate deathDate) throws NegativeLifespanException {
        this.name = name;
        this.birthDate = birthDate;
        this.deathDate = deathDate;
        validateLifespan();
    }

    @Override
    public String toString() {
        return "Person{" + "name=" + name + "\"" + " birthDate=" + birthDate + ", deathDate=" + deathDate + ", parents: " + mother + ", " + father + "}";
    }

    public static Person fromCsvLine(String line) throws NegativeLifespanException {
        String[] parts = line.split(",");   // split dzieli sobie linię (z pliku w tym przypadku) i regex mówi metodzie co oddziela elementy tablicy (w tym przypadku ",")
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");  // Ta linia kodu tworzy obiekt DateTimeFormatter z określonym wzorcem daty, który jest używany do przekształcania tekstowych reprezentacji dat na obiekty LocalDate.
        LocalDate birthDate = LocalDate.parse(parts[1], formatter);   //Ta linia kodu przekształca drugą część (indeks 1) tablicy parts na obiekt LocalDate za pomocą wcześniej utworzonego formatera.
        LocalDate deathDate = null;   //Ta linia kodu inicjalizuje deathDate jako null. Jest to potrzebne, ponieważ nie każda osoba ma datę śmierci (jeśli jest żywa).
        if(!parts[2].isEmpty()){
            deathDate = LocalDate.parse(parts[2], formatter);   //  Ten warunek sprawdza, czy trzecia część (indeks 2) tablicy parts nie jest pusta. Jeśli nie jest pusta, oznacza to, że osoba nie żyje i data śmierci jest przekształcana na obiekt LocalDate.
        }
        return new Person(parts[0], birthDate, deathDate);    //  Na końcu metoda tworzy nowy obiekt Person z odczytanymi danymi i zwraca go.
    }

    public static List<Person> fromCsv(String filePath) throws IOException, NegativeLifespanException, AmbiguousPersonException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath)); // magiczne rzeczy do czytania z pliku
        List<Person> people = new ArrayList<>();

        String line;

        bufferedReader.readLine(); // odczytanie wcześniej pierwszej linii (imię i nazwisko,data urodzenia,data śmierci,rodzic,rodzic) żeby parsowanie dat działało | ogółem pomija pierwszą linię

        while ((line = bufferedReader.readLine()) != null){ // pętla idzie sobie przez cały plik jeśli wartości są równe "null" to wie że plik się skończył
            //people.add(fromCsvLine(line)); // Ta linia wywołuje metodę fromCsvLine(line), która przekształca odczytaną linię na obiekt Person i dodaje go do listy people

            // wersja po dodaniu AmbiguousPersonException

            Person person = fromCsvLine(line);
            person.validatePerson(people); // sprawdzenie czy osoba jest zdublowana (AmbiguousPersonException)
            people.add(person);

        }

        bufferedReader.close();  // zamykanie readera
        return people;
    }

    //do NegativeLifespanException (żeby działało trzeba dodać throws do: fromCsv, fromCsvLine, i konstruktora Person. Dodatkowo w konstruktorze musi się pojawić: "validateLifespan();")
    private void validateLifespan() throws NegativeLifespanException{  // throws NegativeLifespanException oznacza, że metoda może rzucić wyjątek typu NegativeLifespanException.
        if (deathDate != null && deathDate.isBefore(birthDate)){
            throw new NegativeLifespanException(this);   // Ta linia kodu rzuca nowy wyjątek NegativeLifespanException. Słowo kluczowe new jest używane do utworzenia nowego obiektu, a this jest przekazywane jako argument do konstruktora NegativeLifespanException, co oznacza, że obiekt Person, dla którego metoda validateLifespan() została wywołana, jest przekazywany do konstruktora wyjątku.
        }
    }

    //do AmbiguousPersonException (metoda fromCsv została zmodyfikowana i jest dodane: throws AmbiguousPersonException w nagłówku)
    private void validatePerson(List<Person> people) throws AmbiguousPersonException {
        for (Person person : people){
            if(person.getName().equals(getName())){
                throw new AmbiguousPersonException(person);
            }
        }
    }
}
