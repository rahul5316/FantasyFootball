package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writeable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Team implements Writeable {
    // EFFECTS: Defines the name of the team and the list of players in the team class
    private String teamName;
    ArrayList<Player> players;
    int count;

    //EFFECTS: A constructor with no arguments constructs an arraylist players.
    public Team() {
        players = new ArrayList<>();
    }
//MODIFIES: This
    //EFFECTS: Sets the name of the team

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
//MODIFIES: gets the name of the team

    public String getTeamName() {
        return this.teamName;
    }
//EFFECTS: Adds players to the list of players

    public void addPlayers(Player player) {
        players.add(player);
        count++;

    }

    public int getCount() {
        return count;
    }
//EFFECTS: Returns the list of players

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public List<Player> getCollectionOfPlayers() {
        return players;
    }

    // EFFECTS: returns things in this workroom as a JSON array
    private JSONArray playersToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Player player : players) {
            jsonArray.put(player.toJson());
        }

        return jsonArray;
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("players", playersToJson());
        json.put("Team", teamName);
        return json;
    }

}
