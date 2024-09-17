import javax.swing.SwingUtilities;
import controller.Controls;
import model.MainLogic;

public class Entry {
    public static void main(String[] args) {
        MainLogic model = new MainLogic();
        Controls controller = new Controls(model);
    }
}