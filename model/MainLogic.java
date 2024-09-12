package model;

public MainLogic {

    private int score;

    // this array of strings represents the different catagories, as each shinobi is chosen, add an element to the correct index. 
    // This array is traversed to determine the score the array is updated via an external array being sent in from the controller
    private String[] MyChosenShinobis= new String[8];


    // this hashmap contains all the different shinobis that will be used in this game. this map is used to 
    // look up the specific shinobis to them access their point values
    private HashMap<String, Shinobi> shinobiGuide = new HashMap<>();


    public MainLogic() {
        // initialize all shinobis here and add to an arraylist
    }

    public int calculateCurrentScore() {
        // traverse the array and calculate the current score here 
    }

    public String[] recieveUpdatedArray(String[] theUpdatedArray) {
        // this method is called by the controller and sends in the updated array from the view after each selection
        MyChosenShinobis = theUpdatedArray;
    }


}