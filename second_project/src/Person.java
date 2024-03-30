import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Person {
    private String name;
    private LocalDate dateOfBirth, dateOfDeath;
    private Person mother, father;

    public Person(String name, LocalDate dateOfBirth, LocalDate dateOfDeath, Person mother, Person father) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.dateOfDeath = dateOfDeath;
        this.mother = mother;
        this.father = father;
    }

    public static List<Person> fromCsv(String path){
        List<Person> people = new ArrayList<>();
        // List<> - nie wiem jaka listę będę mieć na mysli(kolejka)
        // ArrayList<> - wiem jaka będzie lista potrzebna (lista tablicowa)
        //stos - elementy które zostały włozone do kolekcji jako ostatnie, opuszczą stos jako pierwsze
        // (wieża hanoi) Żeby się dostać do drugiego elementu od góry, trzeba usunąć ten z góry

        try {
            //BufferedReader - dekorator
            BufferedReader br = new BufferedReader(new FileReader(path));
            br.readLine(); // pomijanie pierwszej linijki
            String line;
            while ((line = br.readLine()) != null){
                fromCsvLine(line, people);
            }
            return people;

        } catch (Exception e) {
            System.err.println("Nie mogę odczytać pliku");
            return null;
        }
    }

    public static void fromCsvLine(String line, List<Person> people) {
        //statyczna metoda nie musi miec utworzonego obiektu w tym przypadku Person
        try {
            String[] parts = line.split(","); //oddzielenie kolumn w pliku przecinkiem
            String name = null;
            LocalDate dateOfBirth = null;
            LocalDate dateOfDeath = null;
            Person mother = null;
            Person father = null;

            int numOfCols = parts.length; //zmienna do sprawdzenia ile jest kolumn
            if (numOfCols > 0) {
                name = parts[0].trim(); // trim docina do ostatniej literki nazwisko
            }
            if (numOfCols > 1) {
                String dob = parts[1].trim(); //dob - dateOfBirth
                if (!dob.isEmpty()) { //jeżeli data urodzenia nie jest pusta
                    dateOfBirth = LocalDate.parse(dob, DateTimeFormatter.ofPattern("dd.MM.yyyy")); //parsowanie = ujednolicenie formatów
                }
            }
            if (numOfCols > 2) {
                String dod = parts[2].trim(); //dod - dateOfDeath
                if (!dod.isEmpty()) {
                    dateOfDeath = LocalDate.parse(dod, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
                }
            }
            if (dateOfBirth != null && dateOfDeath != null && dateOfDeath.isBefore(dateOfBirth))
                throw new NegativeLifespanException(name);
            people.add(new Person(name, dateOfBirth, dateOfDeath, mother, father));
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Person{" );
        sb.append("name='" + name + '\'' );
        if(dateOfBirth != null)
            sb.append(", date of birth=" + dateOfBirth);
        if(dateOfDeath != null)
            sb.append(", date of death=" + dateOfDeath);
        if(mother != null)
            sb.append(", mother=" + mother.name);
        if(father != null)
            sb.append(", father=" + father.name);
        sb.append('}');

//        return "Person{" +
//                "name='" + name + '\'' +
//                ", date of birth=" + dateOfBirth +
//                ", date of death=" + dateOfDeath +
//                ", mother=" + mother.name + //mother.name żeby się nie odwołowywało do persona w nieskończonosć
//                ", father=" + father.name +
//                '}';
        return sb.toString();
    }
}
