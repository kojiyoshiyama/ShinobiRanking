package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class ShinobiGame extends JFrame {
    private JLabel shinobiLabel;

    // these are all the buttons used 
    private JButton selectBody;
    private JButton selectClan;
    private JButton selectSensei;
    private JButton selectTalent;
    private JButton selectMind;
    private JButton selectSummon;
    private JButton selectChakra;
    private JButton selectAura;

    // the string[] that list all the shinobis that will be randomized in each catagory
    private String[] bodyChoices = {"Naruto", "Sasuke", "Kakashi"}; 
    private String[] ClanChoices = {"Naruto", "Sasuke", "Kakashi"}; 
    private String[] SenseiChoices = {"Naruto", "Sasuke", "Kakashi"}; 
    private String[] TalentChoices = {"Naruto", "Sasuke", "Kakashi"}; 
    private String[] MindChoices = {"Naruto", "Sasuke", "Kakashi"}; 
    private String[] SummonChoices = {"Naruto", "Sasuke", "Kakashi"}; 
    private String[] ChakraChoices = {"Naruto", "Sasuke", "Kakashi"}; 
    private String[] AuraChoices = {"Naruto", "Sasuke", "Kakashi"}; 

    private Timer timer;
    private int counter = 0;

    public ShinobiGame() {

        // window initialization 
        setTitle("Shinobi Ranking");
        setSize(800, 400);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout(FlowLayout.CENTER, 50, 50));

        // add the label for shinobi names
        shinobiLabel = new JLabel("Shinobi name appears here");
        add(shinobiLabel);

        addSelectButtons();
        setUpTimer();
    }

    // this method creates the buttons to be added into the game 
    private JButton createButton() {
    JButton button = new JButton("Select");
    button.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            button.setEnabled(false); // Disable after selection
            timer.start();
        }
    });
    add(button);
    return button;
}

    // this method creates all thr required buttons, references stored as fields
    private void addSelectButtons() {
        selectBody = createButton();
        selectClan = createButton();
        selectSensei = createButton();
        selectTalent = createButton();
        selectMind = createButton();
        selectSummon = createButton();
        selectChakra = createButton();
        selectAura = createButton();
    }

    // this method sets up the timer
    private void setUpTimer() {
        timer = new Timer(150, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // code that cycles through possible shinobis. Add on to this with multiple catagories etc...
                Random random = new Random();
                String randomShinobi = bodyChoices[random.nextInt(bodyChoices.length)];
                shinobiLabel.setText(randomShinobi);
                //call to stopRandomizing() method to check if its time to stop the randomiziation for this round
                stopRandomizing();
            }
        });
        timer.start();
    }

    // this method stores all image paths to use for randomization
    private String getImagePathForShinobi(String shinobiName) {
        // Replace with actual image paths
        switch (shinobiName) {
            case "Naruto":
                return "path/to/naruto/image.png";
            case "Sasuke":
                return "path/to/sasuke/image.png";
            case "Kakashi":
                return "path/to/kakashi/image.png";
            default:
                return "path/to/default/image.png";
        }
    }

    // check if its time to stop the randomiziation for this round
    private void stopRandomizing() {
        counter++;
        if (counter > 25) {
            timer.stop();
            resetButtons();
            counter = 0;
        }
    }

    // this method resets the buttons so they are all clickable again 
    private void resetButtons() {
        selectBody.setEnabled(true);
        selectClan.setEnabled(true);
        selectSensei.setEnabled(true);
        selectTalent.setEnabled(true);
        selectMind.setEnabled(true);
        selectSummon.setEnabled(true);
        selectChakra.setEnabled(true);
        selectAura.setEnabled(true);
    }
}