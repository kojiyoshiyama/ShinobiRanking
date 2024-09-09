import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class ShinobiGame extends JFrame {
    private JLabel shinobiLabel;
    private JButton selectButton;
    private String[] shinobis = {"Naruto", "Sasuke", "Kakashi"}; // Example names
    private Timer timer;
    private int counter = 0;

    public ShinobiGame() {

        // window initialization 
        setTitle("Shinobi Builder Game");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // add the label for shinobi names
        shinobiLabel = new JLabel("Shinobi name appears here");
        add(shinobiLabel);

        addSelectButtons();
        setUpTimer();
    }

    // this method should be called within the timer set up to display the pictures of each shinobi with 
    // a given string parameter to know which shinobi to display
    private void addPictures(String theRandomShinobi) {

    }

    private void addSelectButtons() {
        //add button to select and action listener to determine what happens when clicked
        selectButton = new JButton("Select Shinobi");
        selectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                selectButton.setEnabled(false); // Disable after selection
                timer.start();
            }
        });
        add(selectButton);
    }

    // this method sets up the timer. Timer 
    private void setUpTimer() {
        timer = new Timer(150, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // code that cycles through possible shinobis. Add on to this with multiple catagories etc...
                Random random = new Random();
                String randomShinobi = shinobis[random.nextInt(shinobis.length)];
                shinobiLabel.setText(randomShinobi);
                //call to stopRandomizing() method to check if its time to stop the randomiziation for this round
                stopRandomizing();
            }
        });
        timer.start();
    }

    // check if its time to stop the randomiziation for this round
    private void stopRandomizing() {
        counter++;
        if (counter > 25) {
            timer.stop();
            selectButton.setEnabled(true);
            counter = 0;
        }
    }
}