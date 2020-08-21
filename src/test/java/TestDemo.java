import java.io.IOException;
import java.util.ArrayList;

public class TestDemo {
    public static void main(String[] args) throws IOException {

        DataDriver dataDriver = new DataDriver();
        ArrayList<String> data = dataDriver.getData("Password");
        data.forEach(System.out::println);
    }
}
