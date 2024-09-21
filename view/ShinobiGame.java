package view;

import controller.Controls;
import java.net.URL;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class ShinobiGame extends JFrame {

    // this is the label that will display the final ranking at the end of the game
    private JLabel ranking;

    // these are the panels used to group together the randomized pictures and the select buttons
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JPanel panel4;
    private JPanel panel5;
    private JPanel panel6;
    private JPanel panel7;
    private JPanel panel8;

    // these are the JLabels used to display all the images
    private JLabel bodyPics = new JLabel();
    private JLabel clanPics = new JLabel();
    private JLabel senseiPics = new JLabel();
    private JLabel talentPics = new JLabel();
    private JLabel mindPics = new JLabel();
    private JLabel summonPics = new JLabel();
    private JLabel chakraPics = new JLabel();
    private JLabel auraPics = new JLabel();

    // these are the JLabels used to say which catagory each panel is for
    private JLabel bodyLabel = new JLabel("Body");
    private JLabel clanLabel = new JLabel("Clan");
    private JLabel senseiLabel = new JLabel("Sensei");
    private JLabel talentLabel = new JLabel("Talent");
    private JLabel mindLabel = new JLabel("Mind");
    private JLabel summonLabel = new JLabel("Summon");
    private JLabel chakraLabel = new JLabel("Chakra");
    private JLabel auraLabel = new JLabel("Aura");

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
    private String[] clanChoices = {"naruto", "sasuke", "neji", "madara", "hashirama", "itachi", "shino", "temari", "gaara", "kakashi", "kiba"};
    private String[] senseiChoices = {"kakashi", "jiraiya", "hiruzen", "tsunade", "orochimaru", "minato", "mightGuy", "hashirama", "sasuke"};
    private String[] summonChoices = {"naruto", "jiraiya", "orochimaru", "tsunade", "pain", "sasuke", "kakashi", "minato", "madara", "kiba", "zetsu"};

    private String[] bodyChoices = {"killerBee", "mightGuy", "rockLee", "naruto", "sasuke", "tsunade", "sakura", "neji", "kisame", "madara", "hashirama", "itachi", "kakashi", "jiraiya", "hiruzen", "minato", "gaara", "zetsu", "pain", "orochimaru", "kiba", "shikamaru", "shino", "temari", "tenTen", "tobirama", "zetsu", "kaguya"};
    private String[] talentChoices = {"naruto", "sasuke", "minato", "itachi", "neji", "hashirama", "jiraiya", "orochimaru", "madara", "pain", "tenTen", "killerBee", "mightGuy", "rockLee", "tsunade", "sakura", "kakashi", "hiruzen", "gaara", "zetsu", "kisame", "kiba", "shikamaru", "shino", "temari", "tobirama", "kaguya"};
    private String[] mindChoices = {"itachi", "shikamaru", "kakashi", "sasuke", "madara", "orochimaru", "minato", "hiruzen", "pain", "jiraiya", "sakura", "naruto", "neji", "hashirama", "killerBee", "mightGuy", "rockLee", "tsunade", "gaara", "zetsu", "kisame", "kiba", "shino", "temari", "tenTen", "tobirama", "kaguya"};
    private String[] chakraChoices = {"naruto", "killerBee", "kisame", "madara", "sasuke", "hashirama", "pain", "kaguya", "kakashi", "minato", "gaara", "itachi", "jiraiya", "hiruzen", "orochimaru", "neji", "tsunade", "sakura", "mightGuy", "rockLee", "shikamaru", "shino", "temari", "tenTen", "tobirama", "zetsu", "kiba"};
    private String[] auraChoices = {"madara", "kaguya", "hashirama", "naruto", "sasuke", "pain", "itachi", "minato", "jiraiya", "hiruzen", "rockLee", "temari", "killerBee", "mightGuy", "tsunade", "sakura", "kakashi", "gaara", "orochimaru", "kisame", "neji", "shikamaru", "shino", "kiba", "zetsu", "tobirama", "tenTen"};

    //the timer used when going through rounds 
    private Timer timer;

    // this counter is to figure out when to stop randomizing
    private int counter = 0;

    // reference to the controller
    private Controls controller;

    // after randomization, this string[] keeps track of the current round's random picks
    private String[] randomSet;

    // this int[] is used to check which catagories to start randomzing
    // keeps track of shinobis that have already bee picked
    private int[] lockedIn = new int[]{0, 0, 0, 0, 0, 0, 0, 0};

    public ShinobiGame(Controls theController) {
        controller = theController;
        // window initialization 
        setTitle("Shinobi Ranking");
        setSize(1000, 700);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout(FlowLayout.CENTER, 50, 50));

        // this method adds in all the select buttons 
        addSelectButtons();

        // this method groups together buttons with images
        addShinobiComponents();
        setUpTimer();
    }

    private JLabel customizeLabel(JLabel label) {
        Font labelFont = new Font("Arial", Font.BOLD, 20);
        label.setFont(labelFont);
        label.setHorizontalAlignment(SwingConstants.CENTER);        
        return label;
    }

    // piece together all the panels 
    private void addShinobiComponents() {
        panel1 = new JPanel(new BorderLayout());
        panel1.add(customizeLabel(bodyLabel), BorderLayout.NORTH);
        panel1.add(bodyPics, BorderLayout.CENTER);
        panel1.add(selectBody, BorderLayout.SOUTH);  
        add(panel1);      

        panel2 = new JPanel(new BorderLayout());
        panel2.add(customizeLabel(clanLabel), BorderLayout.NORTH);
        panel2.add(clanPics, BorderLayout.CENTER);
        panel2.add(selectClan, BorderLayout.SOUTH);  
        add(panel2);

        panel3 = new JPanel(new BorderLayout());
        panel3.add(customizeLabel(senseiLabel), BorderLayout.NORTH);
        panel3.add(senseiPics, BorderLayout.CENTER);
        panel3.add(selectSensei, BorderLayout.SOUTH);  
        add(panel3);

        panel4 = new JPanel(new BorderLayout());
        panel4.add(customizeLabel(talentLabel), BorderLayout.NORTH);
        panel4.add(talentPics, BorderLayout.CENTER);
        panel4.add(selectTalent, BorderLayout.SOUTH);  
        add(panel4);

        panel5 = new JPanel(new BorderLayout());
        panel5.add(customizeLabel(mindLabel), BorderLayout.NORTH);
        panel5.add(mindPics, BorderLayout.CENTER);
        panel5.add(selectMind, BorderLayout.SOUTH);  
        add(panel5);

        panel6 = new JPanel(new BorderLayout());
        panel6.add(customizeLabel(summonLabel), BorderLayout.NORTH);
        panel6.add(summonPics, BorderLayout.CENTER);
        panel6.add(selectSummon, BorderLayout.SOUTH);  
        add(panel6);

        panel7 = new JPanel(new BorderLayout());
        panel7.add(customizeLabel(chakraLabel), BorderLayout.NORTH);
        panel7.add(chakraPics, BorderLayout.CENTER);
        panel7.add(selectChakra, BorderLayout.SOUTH);  
        add(panel7);

        panel8 = new JPanel(new BorderLayout());
        panel8.add(customizeLabel(auraLabel), BorderLayout.NORTH);
        panel8.add(auraPics, BorderLayout.CENTER);
        panel8.add(selectAura, BorderLayout.SOUTH);  
        add(panel8);

        // Create a centered JLabel with a large, bold font and no initial text
        ranking = new JLabel("", SwingConstants.CENTER); // Center the text horizontally
        ranking.setFont(new Font("Arial", Font.BOLD, 30)); // Set font to Arial, bold, size 24

        // Create a panel to hold the JLabel and center it
        JPanel panel9 = new JPanel(new BorderLayout());
        panel9.add(ranking, BorderLayout.CENTER); // Add the label to the center of the panel

        // Add the centerPanel to the main container
        add(panel9);
    }

    // this method creates the buttons to be added into the game 
    private JButton createButton() {
    JButton button = new JButton("Select");
    button.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton clickedButton = (JButton) e.getSource();
            controller.buttonClicked(clickedButton.getActionCommand(), randomSet);
            timer.start();
        }
    });
    add(button);
    return button;
}

    // this method creates all the required buttons, references stored as fields
    private void addSelectButtons() {
        selectBody = createButton();
        selectBody.setActionCommand("body");
        selectClan = createButton();
        selectClan.setActionCommand("clan");
        selectSensei = createButton();
        selectSensei.setActionCommand("sensei");
        selectTalent = createButton();
        selectTalent.setActionCommand("talent");
        selectMind = createButton();
        selectMind.setActionCommand("mind");
        selectSummon = createButton();
        selectSummon.setActionCommand("summon");
        selectChakra = createButton();
        selectChakra.setActionCommand("chakra");
        selectAura = createButton();
        selectAura.setActionCommand("aura");
    }

    // method called by controller that will update the lockedIn array
    public void updateLockedIn(int[] newArray) {
        lockedIn = newArray;
    }

    public void updateRanking(String theRanking) {
        ranking.setText(theRanking);
    }

    // this method sets up the timer
    private void setUpTimer() {
        timer = new Timer(100, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            selectBody.setEnabled(false);
            selectClan.setEnabled(false);
            selectSensei.setEnabled(false);
            selectTalent.setEnabled(false);
            selectMind.setEnabled(false);
            selectSummon.setEnabled(false);
            selectChakra.setEnabled(false);
            selectAura.setEnabled(false);
            Random random = new Random();

            String randomBody = ""; 
            String randomClan = ""; 
            String randomSensei = ""; 
            String randomTalent = ""; 
            String randomMind = ""; 
            String randomSummon = ""; 
            String randomChakra = ""; 
            String randomAura = "";
            if (lockedIn[0] == 0) { 
                // Randomize for bodyPics 
                randomBody = bodyChoices[random.nextInt(bodyChoices.length)];
                String bodyImagePath = getImagePathForShinobi(randomBody);
                updateShinobiImage(bodyPics, bodyImagePath);

            }
            if (lockedIn[1] == 0) {  
                // Randomize for clanPics
                randomClan = clanChoices[random.nextInt(clanChoices.length)];
                String clanImagePath = getImagePathForShinobi(randomClan);
                updateShinobiImage(clanPics, clanImagePath);

            }
            if (lockedIn[2] == 0) {  
                randomSensei = senseiChoices[random.nextInt(senseiChoices.length)];
                String senseiImagePath = getImagePathForShinobi(randomSensei);
                updateShinobiImage(senseiPics, senseiImagePath);

            }
            // Randomize for senseiPics
            if (lockedIn[3] == 0) {  
                // Randomize for talentPics
                randomTalent = talentChoices[random.nextInt(talentChoices.length)];
                String talentImagePath = getImagePathForShinobi(randomTalent);
                updateShinobiImage(talentPics, talentImagePath);

            }
            if (lockedIn[4] == 0) {  
                // Randomize for mindPics
                randomMind = mindChoices[random.nextInt(mindChoices.length)];
                String mindImagePath = getImagePathForShinobi(randomMind);
                updateShinobiImage(mindPics, mindImagePath);

            }
            if (lockedIn[5] == 0) { 
                // Randomize for summonPics
                randomSummon = summonChoices[random.nextInt(summonChoices.length)];
                String summonImagePath = getImagePathForShinobi(randomSummon);
                updateShinobiImage(summonPics, summonImagePath); 
            }
            if (lockedIn[6] == 0) { 
                // Randomize for chakraPics
                randomChakra = chakraChoices[random.nextInt(chakraChoices.length)];
                String chakraImagePath = getImagePathForShinobi(randomChakra);
                updateShinobiImage(chakraPics, chakraImagePath); 
            }
            if (lockedIn[7] == 0) { 
                // Randomize for auraPics
                randomAura = auraChoices[random.nextInt(auraChoices.length)];
                String auraImagePath = getImagePathForShinobi(randomAura);
                updateShinobiImage(auraPics, auraImagePath); 
            }
            
            randomSet = new String[]{randomBody, 
                                    randomClan, 
                                    randomSensei, 
                                    randomTalent, 
                                    randomMind, 
                                    randomSummon, 
                                    randomChakra, 
                                    randomAura};

            // Call to stopRandomizing() method to check if it's time to stop the randomization for this round
            stopRandomizing();
        }
    });
    timer.start();
}

    // This method updates the specified JLabel with the desired image
    private void updateShinobiImage(JLabel label, String imagePath) {
        URL imageUrl = getClass().getResource(imagePath);
        if (imageUrl != null) {
            ImageIcon icon = new ImageIcon(imageUrl);
            label.setIcon(icon);
            label.setText(""); // Optionally clear any text if needed
        } else {
            System.out.println("Image not found at: " + imagePath);
            label.setText("Image not found");
        }
    }

    // this method creates all image paths to use for randomization within timer setup
    private String getImagePathForShinobi(String shinobiName) {
        return "/images/" + shinobiName + ".png";
    }

    // check if its time to stop the randomiziation for this round
    private void stopRandomizing() {
        counter++;
        if (counter > 10) {
            timer.stop();
            resetButtons();
            counter = 0;
        }
    }

    // this method resets the buttons so they are all clickable again 
    private void resetButtons() {
        if (lockedIn[0] == 0) {
            selectBody.setEnabled(true);
        }
        if (lockedIn[1] == 0) {
            selectClan.setEnabled(true);
        }
        if (lockedIn[2] == 0) {
            selectSensei.setEnabled(true);
        }
        if (lockedIn[3] == 0) { 
            selectTalent.setEnabled(true);
        }
        if (lockedIn[4] == 0) { 
            selectMind.setEnabled(true);
        }
        if (lockedIn[5] == 0) {
            selectSummon.setEnabled(true);
        } 
        if (lockedIn[6] == 0) { 
            selectChakra.setEnabled(true);
        }
        if (lockedIn[7] == 0) { 
            selectAura.setEnabled(true);
        }
    }
}