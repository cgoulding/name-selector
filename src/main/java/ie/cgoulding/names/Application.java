package ie.cgoulding.names;

public class Application {

    public static void main(String[] args) {
        NameService service = new NameService();
        service.loadNames("boy", new NameFilter(
            1,
            true,
            true
        )).forEach(name -> System.out.println(name));
    }
}
