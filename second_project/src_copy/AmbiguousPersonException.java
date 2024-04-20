public class AmbiguousPersonException extends Exception{
    public AmbiguousPersonException(Person person){
        super(String.format("Osoba: %s pojawiła się kolejny raz w pliku.", person.getName()));
    }
}
