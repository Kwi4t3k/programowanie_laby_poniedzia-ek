import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        //drukowanie listy osób z pliku family.csv

        try {  // Ten blok kodu jest używany do obsługi wyjątków, które mogą wystąpić podczas wykonania kodu wewnątrz bloku try. Jeśli wystąpi wyjątek, wykonanie przechodzi do odpowiedniego bloku catch.
            List<Person> people = Person.fromCsv("second_project\\family.csv");  // Ta linia kodu wywołuje statyczną metodę fromCsv() klasy Person, przekazując ścieżkę do pliku CSV jako argument. Metoda fromCsv() czyta plik CSV i tworzy listę obiektów Person na podstawie danych w pliku. Wynik jest przypisywany do zmiennej people.
            for(Person person : people){   // Ta pętla for-each przechodzi przez każdy obiekt Person w liście people. Dla każdej osoby, wykonuje kod wewnątrz pętli.
                System.out.println(person);    //Ta linia kodu drukuje reprezentację tekstową obiektu Person na konsoli. Reprezentacja ta jest generowana przez metodę toString() klasy Person.
            }
        } catch (IOException e) {   // Ten blok kodu jest wykonywany, gdy wystąpi wyjątek IOException podczas wykonania kodu w bloku try. IOException to wyjątek, który może wystąpić podczas operacji wejścia/wyjścia, takich jak odczyt pliku.
            throw new RuntimeException(e);  //Ta linia kodu tworzy nowy wyjątek RuntimeException i rzuca go. Wyjątek RuntimeException jest niesprawdzonym wyjątkiem, co oznacza, że kompilator nie wymaga, aby był obsługiwany lub zgłaszany. W tym przypadku, oryginalny wyjątek IOException jest przekazywany jako argument do konstruktora RuntimeException, co pozwala na zachowanie informacji o pierwotnym wyjątku.
        } catch (NegativeLifespanException | AmbiguousPersonException e){  // gdy data śmierci jest wcześniejsza niż data urodzenia jest wypisywany błąd | w późniejszym etapie trzeba było dodać wyjątek do: AmbiguousPersonException
            System.out.println(e.getMessage());
        }
    }
}