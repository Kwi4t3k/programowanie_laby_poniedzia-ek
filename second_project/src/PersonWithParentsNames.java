import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Klasa reprezentująca osobę wraz z imionami jej rodziców
public class PersonWithParentsNames {
    // Osoba
    public final Person person;
    // Imiona rodziców
    public final String [] parentNames;

    // Konstruktor klasy
    public PersonWithParentsNames(Person person, String[] parents) {
        this.person = person;
        this.parentNames = parents;
    }

    // Metoda tworząca obiekt klasy na podstawie linii CSV
    public static PersonWithParentsNames fromCsvLine(String line){
        // Tworzenie osoby na podstawie linii CSV
        Person person = Person.fromCsvLine(line);
        // Podział linii na części
        String[] parts = line.split(",", -1); //-1 powoduje, ze puste pola na końcu linii nie będą ignorowane, będzie utworzona pusta składowa tablicy
        // Tablica z imionami rodziców
        String[] parents = new String[2];
        // Wypełnianie tablicy imionami rodziców
        for (int i = 0; i < 2; ++i) {
            if(!parts[i + 3].isEmpty()){
                parents[i] = parts[i+3];
            }
        }
        // Zwracanie nowego obiektu klasy
        return new PersonWithParentsNames(person, parents);
    }

    // Metoda łącząca osoby z ich rodzicami
    public static void linkRelatives(List<PersonWithParentsNames> people) throws UndefinedParentException{
        // Mapa osób
        Map<String, PersonWithParentsNames> peopleMap = new HashMap<>();

        /*Mapa to struktura danych, która przechowuje pary klucz-wartość. W tym przypadku kluczem jest String, a wartością jest obiekt klasy PersonWithParentsNames.

        Każdy klucz w mapie jest unikalny, a do każdego klucza przypisana jest dokładnie jedna wartość. Możemy dodać parę klucz-wartość do mapy, możemy usunąć parę na podstawie klucza,
        możemy sprawdzić czy dany klucz istnieje w mapie, a także możemy pobrać wartość przypisaną do danego klucza.

        HashMap to konkretna implementacja interfejsu Map w Javie. Wykorzystuje ona tablicę do przechowywania danych i funkcję hashującą do przypisywania kluczy do indeksów w tej tablicy.
        Dzięki temu, operacje takie jak dodawanie pary klucz-wartość, usuwanie pary klucz-wartość, czy pobieranie wartości na podstawie klucza są bardzo szybkie.*/



        // Wypełnianie mapy osobami
        for (PersonWithParentsNames personWithParentsNames : people){
            peopleMap.put(personWithParentsNames.person.getName(), personWithParentsNames);
        }

        // Przypisywanie rodziców do osób
        for (PersonWithParentsNames personWithParentsNames : people){
            Person person = personWithParentsNames.person;

            // Przypisywanie rodziców do osoby
            for (int i = 0; i < 2; i++) {
                String parentName = personWithParentsNames.parentNames[i];
                if(parentName != null){
                    // Jeżeli rodzic jest w mapie, dodajemy go do osoby
                    if(peopleMap.containsKey(parentName)){
                        person.addParent(peopleMap.get(parentName).person);
                    } else {
                        // Jeżeli rodzica nie ma w mapie, rzucamy wyjątek
                        throw new UndefinedParentException(person, parentName);
                    }
                }
            }
        }
    }
}
