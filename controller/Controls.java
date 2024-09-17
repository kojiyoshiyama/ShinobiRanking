package controller;

import view.ShinobiGame;
import model.ModelUpdateListener;
import model.MainLogic;
import javax.swing.*;


public class Controls extends JFrame implements ModelUpdateListener {

    // references to the model and view placed as fields
    private ShinobiGame view;
    private MainLogic model;

    // initializing the class, brings in refrences to the view and model
    public Controls(MainLogic theModel) {
        model = theModel;
        model.addModelUpdateListener(this);
        SwingUtilities.invokeLater(() -> {
            view = new ShinobiGame(this);
            view.setVisible(true);
        });
    }

    public void buttonClicked(String buttonName, String[] theSet) {
        model.recieveRoundInfo(buttonName, theSet);
    }

    @Override
    public void onArrayUpdated(int[] updatedArray) {
        view.updateLockedIn(updatedArray); // Notify view with updated array
    }

    @Override
    public void onTextUpdated(String newText) {
        view.updateRanking(newText); // Notify view with new text
    }
}