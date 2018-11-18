import controller.Controller;
import service.ServiceImp;
import view.Window;

/**
 * Main class
 */
public class Main {
    public static void main(String[] args) {
        new Window(new Controller(new ServiceImp()));
    }
}
