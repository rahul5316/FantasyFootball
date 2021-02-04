package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Tournament {
    //EFFECTS: Creates an arraylist referencing to the variable team
    ArrayList<Team> teams;

    //EFFECTS: constructor constructs an arraylist team
    public Tournament() {
        teams = new ArrayList<>();
    }

    //Modifies: This
    //Effects: Adds team to the arraylist constructed above
    public void addTeam(Team team) {
        teams.add(team);
    }

    //EFFECTS: returns a list of teams in the fixture
    public ArrayList<Team> getTeams() {
        return teams;
    }

    public int numTeams() {
        return teams.size();
    }


    // Creates a json object and stores all the teams
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("teams", teamsToJson());
        return json;
    }

    private JSONArray teamsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Team team : teams) {
            jsonArray.put(team.toJson());
        }

        return jsonArray;
    }


}
