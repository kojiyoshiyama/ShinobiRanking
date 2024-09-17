package model;

import java.util.*;

public class MainLogic {

    private int score;

    // this array of strings represents the different catagories, as each shinobi is chosen, add an element to the correct index. 
    // This array is traversed to determine the score the array is updated via an external array being sent in from the controller
    private ArrayList<String> MyChosenShinobis = new ArrayList<>();

    private String[] currentRandomSet;

    // this array keeps track of the catagories that have already been locked in 
    private int[] lockedIn = new int[]{0, 0, 0, 0, 0, 0, 0, 0};

    // this hashmap contains all the different shinobis that will be used in this game. this map is used to 
    // look up the specific shinobis to them access their point values
    private HashMap<String, Integer> shinobiGuide = new HashMap<>();

    private String finalRank = "";

    // this is the list of listeners
    private List<ModelUpdateListener> listeners = new ArrayList<>();

    private String[] rankNames = new String[]{"Shinobi God", 
                                              "Legendary Shinobi", 
                                              "Kage-Killer Shinobi", 
                                              "Kage-Level Shinobi", 
                                              "Master Ninja", 
                                              "Elite Jonin", 
                                              "Jonin", 
                                              "Chunin", 
                                              "Genin", 
                                              "Novice Shinobi", 
                                              "Villager"};

    public MainLogic() {
        // initialize all shinobis here and add to shinobiGuide
    }

    public void recieveRoundInfo(String buttonClicked, String[] theSet) {
        currentRandomSet = theSet;
        if (buttonClicked.equals("body")) {
            MyChosenShinobis.add(currentRandomSet[0]);
            lockedIn[0] = 1;
        } else if (buttonClicked.equals("clan"))  {
            MyChosenShinobis.add(currentRandomSet[1]);
            lockedIn[1] = 1;
        } else if (buttonClicked.equals("sensei"))  {
            MyChosenShinobis.add(currentRandomSet[2]);
            lockedIn[2] = 1;
        } else if (buttonClicked.equals("talent"))  {
            MyChosenShinobis.add(currentRandomSet[3]);
            lockedIn[3] = 1;
        } else if (buttonClicked.equals("mind"))  {
            MyChosenShinobis.add(currentRandomSet[4]);
            lockedIn[4] = 1;
        } else if (buttonClicked.equals("summon"))  {
            MyChosenShinobis.add(currentRandomSet[5]);
            lockedIn[5] = 1;
        } else if (buttonClicked.equals("chakra"))  {
            MyChosenShinobis.add(currentRandomSet[6]);
            lockedIn[6] = 1;
        } else if (buttonClicked.equals("aura"))  {
            MyChosenShinobis.add(currentRandomSet[7]);
            lockedIn[7] = 1;
        }
        notifyArrayUpdated();
        if (MyChosenShinobis.size() == 8) {
            // calculateScore();
        }

    }

    // Register a listener
    public void addModelUpdateListener(ModelUpdateListener listener) {
        listeners.add(listener);
    }

    // Notify listeners when the array is updated
    private void notifyArrayUpdated() {
        for (ModelUpdateListener listener : listeners) {
            listener.onArrayUpdated(lockedIn);
        }
    }

    // Notify listeners when the text is updated
    private void notifyTextUpdated() {
        for (ModelUpdateListener listener : listeners) {
            listener.onTextUpdated(finalRank);
        }
    }

    private void calculateScore() {
        int score = 0;
        //traverse through the chosen shinobis to calculate the rank 
        // rank of 0 is the best
        for (int i = 0; i < MyChosenShinobis.size(); i++) {
            score += shinobiGuide.get(MyChosenShinobis.get(0));
        }

        if (score == 0) {
            finalRank = "Rank: 0 Shinobi God";
        } else if (score == 1) {
            finalRank = "Rank: 1 Legendary Shinobi";
        } else if (score > 1 && score <= 4) {
            finalRank = "Rank: " + score + " Kage-Killer Shinobi";
        } else if (score > 4 && score <= 7) {
            finalRank = "Rank: " + score + " Kage-Level Shinobi";
        } else if (score > 7 && score <= 10) {
            finalRank = "Rank: " + score + " Master Ninja";
        } else if (score > 10 && score <= 14) {
            finalRank = "Rank: " + score + " Elite Jonin";
        } else if (score > 14 && score <= 19) {
            finalRank = "Rank: " + score + " Jonin";
        } else if (score > 19 && score <= 23) {
            finalRank = "Rank: " + score + " Chunin";
        } else if (score > 23 && score <= 27) {
            finalRank = "Rank: " + score + " Genin";
        } else if (score > 27 && score <= 31) {
            finalRank = "Rank: " + score + " Novice Shinobi";
        } else if (score > 31) {
            finalRank = "Rank: " + score + " Villager";
        }
    }
}