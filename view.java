public class view { // extend java swing

    private String[] body = new String[];

    private String[] clan = new String[];

    private String[] sensei = new String[];

    private String[] talent = new String[];

    private String[] mind = new String[];

    private String[] summon = new String[];

    private String[] chakra = new String[];

    private String[] aura = new String[];

    // this is to keep track of which images to keep on the game board after it has been chosen 
    private currentPicks // not too sure on this one yet


    public view() {
        //init java swing GUI
        // fill the arrays with the shinobis that will rotate through each catagory
        
    }

    // this method will flip through the arrays for each catagory and randomly pick a shinobi for the next found of choosing
    // called by controller
    // returns a list of the current options for the round for the controller to use when the player is picking an option
    public String[] randomize() {
        
    }

    // called once currentPicks is full, stops game and displays the score
    public displayScore() {
        getScore();
    }


    // retrieves the score from the model to display
    public getScore() {

    }

    // gets an updated array of currentPicks so the view knows what to display
    public updateCurrentPicks() {

    }






}