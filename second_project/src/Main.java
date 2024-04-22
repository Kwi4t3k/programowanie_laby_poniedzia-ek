import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        PlantUMLRunner.setJarPath("second_project/tmp/download/plantuml-1.2024.3.jar");
//        PlantUMLRunner.generate(data, "uml", "test");

        try {
            // Tworzenie listy obiektów Person z pliku CSV
            List<Person> personList = Person.fromCsv("second_project/family.csv");

            // Przechodzenie przez listę i wyświetlanie każdego obiektu Person
            for (Person person : personList) {
                System.out.println(person);
            }



            // Tworzymy obiekt Optional<String> o nazwie changedPeople
            Optional<String> changedPeople = personList
                    .stream() // Tworzymy strumień z listy osób
                    .sorted((person1, person2) -> person1.getName().compareTo(person2.getName())) // Sortujemy osoby alfabetycznie
                    .map(person -> person.getName()) // Mapujemy strumień na imiona osób
                    .filter(name -> !name.equals("Anna Dąbrowska")) // Filtrujemy strumień, aby usunąć "Anna Dąbrowska"
                    .max((name1, name2) -> name1.compareTo(name2)); // Znajdujemy największe imię alfabetycznie

            // Jeśli obiekt Optional nie jest pusty, wyświetlamy jego wartość
            if(!changedPeople.isEmpty()) {
                System.out.println(changedPeople.get());
            }

            // Tworzymy diagram UML dla listy osób
            String uml = Person.toUML(personList);

            // Generujemy diagram UML
            PlantUMLRunner.generate(uml, "second_project/uml", "family");

            // Pobieramy trzecią osobę z listy
            Person person = personList.get(2);

            // Tworzymy diagram UML dla tej osoby
            uml = person.toUML();

            // Generujemy diagram UML dla tej osoby
            PlantUMLRunner.generate(uml, "second_project/uml", person.getName());

        } catch (Exception e){
            // Wyświetlanie komunikatu o błędzie, jeśli wystąpi wyjątek
//            System.err.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}