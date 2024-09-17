package model;

public interface ModelUpdateListener {
    void onArrayUpdated(int[] updatedArray); // Notify when array is updated
    void onTextUpdated(String newText); // Notify when text is updated
}
