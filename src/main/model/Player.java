package model;

import exception.PaceError;
import org.json.JSONObject;
import persistence.Writeable;

public class Player implements Writeable  {
    private String playerName;
    private int goalsScored;
    private int pace;
    private int dribbleRate;
    private int passAccuracy;
    private int position;
    private int physique;

    // EFFECTS: Empty constructor constructs an empty player
    public Player() {


    }
//MODIFIES: This
    //EFFECTS: Sets the player's name

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    //MODIFIES: This
    //EFFECTS: Sets the player's position
    public void setPosition(int position) {
        this.position = position;
    }
    //MODIFIES: This
    //EFFECTS: Sets the player's pace

    public void setPace(int pace) throws PaceError {
        if (pace < 0) {
            throw new PaceError("Cannot use pace less than 0");
        } else {
            this.pace = pace;
        }
    }

    //MODIFIES: This
    //EFFECTS: Sets the player's dribbling rate
    public void setDribbleRate(int dribbleRate) {
        this.dribbleRate = dribbleRate;
    }

    //MODIFIES: This
    //EFFECTS: Sets the player's passing accuracy
    public void setPassAccuracy(int passAccuracy) {
        this.passAccuracy = passAccuracy;
    }

    //MODIFIES: This
    //EFFECTS: Sets the player's physique
    public void setPhysique(int physique) {
        this.physique = physique;
    }

    //EFFECTS: Gets players name

    public String getPlayerName() {
        return playerName;
    }
//EFFECTS: Gets goals Scored by the player

    public int getGoalsScored() {
        return goalsScored;
    }
    //EFFECTS: Gets players' pace

    public int getPace() {
        return pace;
    }
    //EFFECTS: Gets the dribbling rate of the player

    public int getDribbleRate() {
        return dribbleRate;
    }
    //EFFECTS: Gets the pass accuracy of the player

    public int getPassAccuracy() {
        return passAccuracy;
    }
    //EFFECTS: Gets the position of the player

    public int getPosition() {
        return position;
    }
//EFFECTS: Gets the physique of the player

    public int getPhysique() {
        return physique;
    }
    // MODIFIES: This
    //Effects: Sets the goals Scored

    public void setGoalsScored(int goalsScored) {
        this.goalsScored = goalsScored;

    }


    @Override
    public String toString() {
        return "Player{" + "playerName='" + getPlayerName() + '\'' + ", goalsScored=" + getGoalsScored()
                + ", pace=" + getPace() + ", dribbleRate=" + getDribbleRate() + ", passAccuracy=" + getPassAccuracy()
                + ", position=" + getPosition() + ", physique=" + getPhysique() + '}';
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", playerName);
        json.put("pace", pace);
        json.put("Dribbling Rate", dribbleRate);
        json.put("Pass Accuracy", passAccuracy);
        json.put("Goals", goalsScored);
        json.put("Position", position);
        json.put("Physique", physique);
        return json;
    }


}
