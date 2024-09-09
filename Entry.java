import javax.swing.SwingUtilities;

public class Entry {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ShinobiGame game = new ShinobiGame();
            game.setVisible(true);
        });
    }
}