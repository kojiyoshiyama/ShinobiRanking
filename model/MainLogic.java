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
    private HashMap<String, Shinobi> shinobiMap = new HashMap<>();

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
        //Combination 1: Naruto (Talent), Might Guy (Body), Hashirama (Clan), Itachi (Mind), Killer Bee (Chakra), Madara (Aura), Kaguya (Summon), Minato (Sensei).
        // Combination 2: Madara (Clan), Might Guy (Body), Hashirama (Aura), Minato (Sensei), Itachi (Mind), Killer Bee (Chakra), Kaguya (Summon), Naruto (Talent).

        Shinobi naruto = new Shinobi("naruto", 0, 1, 1, 0, 1, 0, 0, 1);  // Best in talent, strong elsewhere
        Shinobi mightGuy = new Shinobi("mightGuy", 0, 3, 1, 2, 2, 4, 5, 2); // Best in body
        Shinobi hashirama = new Shinobi("hashirama", 1, 0, 0, 1, 1, 1, 1, 0); // Near-perfect, slight tweaks
        Shinobi itachi = new Shinobi("itachi", 1, 0, 1, 0, 0, 1, 2, 1);  // Best in mind
        Shinobi killerBee = new Shinobi("killerBee", 1, 0, 1, 2, 1, 0, 0, 1); // Perfect chakra
        Shinobi madara = new Shinobi("madara", 1, 0, 0, 0, 0, 1, 0, 0);  // Best in clan, aura
        Shinobi kaguya = new Shinobi("kaguya", 0, 0, 0, 0, 0, 0, 0, 0);  // Shinobi god in everything
        Shinobi minato = new Shinobi("minato", 0, 1, 0, 0, 1, 2, 1, 0);  // Best in aura and sensei
        Shinobi gaara = new Shinobi("gaara", 4, 2, 3, 3, 5, 3, 2, 2);  // Strong but not god-level
        Shinobi hiruzen = new Shinobi("hiruzen", 3, 3, 0, 2, 2, 3, 3, 3);  // Great, but aging
        Shinobi jiraya = new Shinobi("jiraiya", 2, 2, 2, 1, 1, 1, 3, 3);  // Very strong, not perfect
        Shinobi kakashi = new Shinobi("kakashi", 2, 1, 0, 1, 0, 2, 2, 2);  // Strong tactician, not top-tier in all
        Shinobi kakuzu = new Shinobi("kakuzu", 2, 2, 3, 3, 3, 2, 2, 3);  // Great strength but not god-level
        Shinobi kiba = new Shinobi("kiba", 6, 3, 4, 5, 4, 6, 4, 5);  // Strong, but lower-tier
        Shinobi kisame = new Shinobi("kisame", 1, 2, 3, 3, 3, 0, 1, 2);  // Strong in chakra, lesser in others
        Shinobi neji = new Shinobi("neji", 3, 0, 3, 2, 2, 2, 2, 3);  // Strong talent, but not god-like
        Shinobi orochimaru = new Shinobi("orochimaru", 1, 1, 0, 0, 0, 1, 1, 1);  // Highly talented but evil
        Shinobi pain = new Shinobi("pain", 1, 1, 2, 1, 1, 0, 1, 1);  // Near-perfect, but slight tweaks
        Shinobi rockLee = new Shinobi("rockLee", 0, 3, 4, 4, 3, 6, 5, 4);  // Best in body, weaker elsewhere
        Shinobi sakura = new Shinobi("sakura", 2, 3, 2, 3, 2, 3, 2, 2);  // Strong, but not top-level
        Shinobi sasuke = new Shinobi("sasuke", 0, 0, 0, 0, 0, 1, 0, 0);
        Shinobi shikamaru = new Shinobi("shikamaru", 4, 3, 0, 2, 0, 5, 4, 3);  // Highly intelligent, weaker in body
        Shinobi shino = new Shinobi("shino", 3, 3, 3, 3, 4, 2, 4, 3);  // Balanced, no standout category
        Shinobi temari = new Shinobi("temari", 3, 3, 3, 4, 4, 3, 4, 4);  // Strong, but not top-level
        Shinobi tenTen = new Shinobi("tenTen", 6, 4, 5, 5, 5, 5, 5, 5);  // Skilled, but not extraordinary
        Shinobi tobirama = new Shinobi("tobirama", 0, 0, 0, 0, 1, 0, 0, 0);  // Near-perfect, highly powerful in all aspects
        Shinobi tsunade = new Shinobi("tsunade", 1, 1, 2, 1, 1, 2, 0, 0);  // Very powerful in medical ninjutsu
        Shinobi zetsu = new Shinobi("zetsu", 5, 4, 5, 6, 5, 4, 6, 5);  // Mediocre, only a few notable strengths


        shinobiMap.put("naruto", naruto);
        shinobiMap.put("might Guy", mightGuy);
        shinobiMap.put("hashirama", hashirama);
        shinobiMap.put("itachi", itachi);
        shinobiMap.put("killer Bee", killerBee);
        shinobiMap.put("madara", madara);
        shinobiMap.put("kaguya", kaguya);
        shinobiMap.put("minato", minato);
        shinobiMap.put("gaara", gaara);
        shinobiMap.put("hiruzen", hiruzen);
        shinobiMap.put("jiraiya", jiraya);
        shinobiMap.put("kakashi", kakashi);
        shinobiMap.put("kakuzu", kakuzu);
        shinobiMap.put("kiba", kiba);
        shinobiMap.put("kisame", kisame);
        shinobiMap.put("neji", neji);
        shinobiMap.put("orochimaru", orochimaru);
        shinobiMap.put("pain", pain);
        shinobiMap.put("rock Lee", rockLee);
        shinobiMap.put("sakura", sakura);
        shinobiMap.put("sasuke", sasuke);
        shinobiMap.put("shikamaru", shikamaru);
        shinobiMap.put("shino", shino);
        shinobiMap.put("temari", temari);
        shinobiMap.put("tenTen", tenTen);
        shinobiMap.put("tobirama", tobirama);
        shinobiMap.put("tsunade", tsunade);
        shinobiMap.put("zetsu", zetsu);
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
            calculateRank();
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

    public int calculateTotalScore() {
        int totalScore = 0;

        // Iterate through each shinobi in the chosen list
        for (int i = 0; i < MyChosenShinobis.size(); i++) {
            String shinobiName = MyChosenShinobis.get(i);
            Shinobi chosenShinobi = shinobiMap.get(shinobiName); // Look up the shinobi by name

            if (chosenShinobi != null) {
                // Use switch to handle different categories based on index
                switch (i) {
                    case 0: // Body
                        totalScore += chosenShinobi.getBody();
                        break;
                    case 1: // Clan
                        totalScore += chosenShinobi.getClan();
                        break;
                    case 2: // Sensei
                        totalScore += chosenShinobi.getSensei();
                        break;
                    case 3: // Talent
                        totalScore += chosenShinobi.getTalent();
                        break;
                    case 4: // Mind
                        totalScore += chosenShinobi.getMind();
                        break;
                    case 5: // Summon
                        totalScore += chosenShinobi.getSummon();
                        break;
                    case 6: // Chakra
                        totalScore += chosenShinobi.getChakra();
                        break;
                    case 7: // Aura
                        totalScore += chosenShinobi.getAura();
                        break;
                    default:
                        System.out.println("Invalid index: " + i);
                        break;
                }
            } else {
                System.out.println("Shinobi not found: " + shinobiName);
            }
        }
        return totalScore;
    }


    private void calculateRank() {
        int score = calculateTotalScore();
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
        notifyTextUpdated();
    }
}