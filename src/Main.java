import model.repository.PostRepository;
import view.UI;

public class Main {
    public static void main(String[] args){
        new PostRepository()
                .findAll()
                .forEach(System.out::println);
    }
}
