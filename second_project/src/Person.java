import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Person {
    private String name;  // Imię osoby
    private LocalDate birthDate, deathDate;  // Daty urodzenia i śmierci osoby
    private List<Person> parents = new ArrayList<>();  // Lista rodziców osoby

    public Person(String name, LocalDate birthDate, LocalDate deathDate){
        this.name = name;
        this.birthDate = birthDate;
        this.deathDate = deathDate;
    }

    public String getName() {
        return name;  // Zwraca imię osoby
    }

    public LocalDate getBirthDate() {
        return birthDate;  // Zwraca datę urodzenia osoby
    }

    public LocalDate getDeathDate() {
        return deathDate;  // Zwraca datę śmierci osoby
    }

    public static Person fromCsvLine(String line){
        String[] parts = line.split("," ,-1);   // Metoda split dzieli linię na części, używając przecinka jako separatora
        String name = parts[0].trim();  // Pobiera imię osoby

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");  // Tworzy obiekt DateTimeFormatter z określonym wzorcem daty

        String birthPart = parts[1];  // Pobiera datę urodzenia osoby
        String deathPart = parts[2];  // Pobiera datę śmierci osoby

        LocalDate death = null;
        LocalDate birth = null;

        if(!deathPart.isEmpty()){
            death = LocalDate.parse(deathPart, formatter);  // Przekształca datę śmierci na obiekt LocalDate
        }
        birth = LocalDate.parse(birthPart, formatter);  // Przekształca datę urodzenia na obiekt LocalDate

        return new Person(name, birth, death);    // Tworzy nowy obiekt Person i go zwraca
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", birthDate=" + birthDate +
                ", deathDate=" + deathDate +
                ", parents = " + parents +
                '}';  // Zwraca reprezentację tekstową obiektu Person
    }

    public static List<Person> fromCsv(String filePath) throws IOException, NegativeLifespanException, UndefinedParentException{
        BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath)); // Tworzy BufferedReader do czytania z pliku
        List<Person> people = new ArrayList<>();  // Tworzy listę osób
        List<PersonWithParentsNames> parentsNames = new ArrayList<>();  // Tworzy listę imion rodziców

        String line;

        bufferedReader.readLine(); // Pomija pierwszą linię pliku (nagłówek)

        // Czyta plik linia po linii
        while ((line = bufferedReader.readLine()) != null){
            // Tworzy obiekt PersonWithParentsNames z linii CSV
            PersonWithParentsNames personWithParentsNames = PersonWithParentsNames.fromCsvLine(line);
            // Sprawdza, czy osoba ma prawidłowy czas życia
            personWithParentsNames.person.validateLifespan();
            // Sprawdza, czy osoba jest prawidłowa
            personWithParentsNames.person.validatePerson(people);
            // Dodaje imiona rodziców do listy
            parentsNames.add(personWithParentsNames);
            // Dodaje osobę do listy osób
            people.add(personWithParentsNames.person);
        }
        // Łączy krewnych
        PersonWithParentsNames.linkRelatives(parentsNames);

        try {
            // Sprawdza, czy wiek rodzicielstwa jest prawidłowy dla każdej osoby
            for (Person person : people) {
                person.validateParentingAge();
            }
        } catch (ParentingAgeException e){
            // W przypadku wyjątku prosi o potwierdzenie od użytkownika
            Scanner scanner = new Scanner(System.in);
            System.out.println(e.getMessage());
            System.out.println("Proszę o potwierdzenie [t/n]: ");
            String response = scanner.nextLine();

            // Jeśli odpowiedź nie jest "t" ani "n", usuwa osobę z listy
            if(!response.equals("t") && !response.equals("n")){
                people.remove(e.person);
            }
        }

        bufferedReader.close();  // Zamyka BufferedReader
        return people;  // Zwraca listę osób
    }

    //do NegativeLifespanException

    private void validateLifespan() throws NegativeLifespanException{
        // Sprawdza, czy osoba nie żyje dłużej niż żyła
        if (deathDate != null && deathDate.isBefore(birthDate)){
            // Rzuca wyjątek, jeśli osoba zmarła przed urodzeniem
            throw new NegativeLifespanException(this);
        }
    }

    //do AmbiguousPersonException

    private void validatePerson(List<Person> people) throws AmbiguousPersonException {
        for (Person person : people){
            // Sprawdza, czy istnieje już osoba o takim samym imieniu
            if(person.name.equals(this.name)){
                // Rzuca wyjątek, jeśli istnieje już osoba o takim samym imieniu
                throw new AmbiguousPersonException(name);
            }
        }
    }

    public void addParent(Person person){
        // Dodaje rodzica do listy rodziców osoby
        parents.add(person);
    }

    private void validateParentingAge() throws ParentingAgeException {
        for(Person parent : parents){
            // Sprawdza, czy rodzic jest zbyt młody lub nie żyje w momencie narodzin osoby
            if(birthDate.isBefore(parent.birthDate.plusYears(15)) || (parent.deathDate != null && birthDate.isAfter(parent.deathDate))){
                // Rzuca wyjątek, jeśli rodzic jest zbyt młody lub nie żyje w momencie narodzin osoby
                throw new ParentingAgeException(this, parent);
            }
        }
    }

    //statyczne metody toBinaryFile i fromBinaryFile, które zapiszą i odczytają listę osób do i z pliku binarnego

    public static void toBinaryFile(List<Person> personList, String fileName) throws IOException {
        // Zapisuje listę osób do pliku binarnego
        try(
                // Tworzy strumień wyjściowy pliku
                FileOutputStream fos = new FileOutputStream(fileName);
                // Tworzy strumień wyjściowy obiektu
                ObjectOutputStream oos = new ObjectOutputStream(fos);
        ){
            // Zapisuje listę osób do pliku
            oos.writeObject(personList);
        }
    }

    public static List<Person> fromBinaryFile(String fileName) throws IOException, ClassNotFoundException {
        // Odczytuje listę osób z pliku binarnego
        try(
                // Tworzy strumień wejściowy pliku
                FileInputStream fis = new FileInputStream(fileName);
                // Tworzy strumień wejściowy obiektu
                ObjectInputStream ois = new ObjectInputStream(fis);
        ){
            // Odczytuje listę osób z pliku i ją zwraca
            return (List<Person>) ois.readObject();
        }
    }

}
