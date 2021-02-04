package persistence;

import exception.PaceError;
import model.Player;
import model.Team;
import model.Tournament;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;


// Reference: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class JsonReader {
    private String source;

    //EFFECTS: Constructs a reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Tournament read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseTournament(jsonObject);
    }

    public Team teamReader() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseTeam(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    //EFFECTS: parses Tournament from JSON object and returns it

    private Tournament parseTournament(JSONObject jsonObject) {
        Tournament t = new Tournament();
        addTeams(t, jsonObject);
        return t;
    }


    private Team parseTeam(JSONObject jsonObject) {
        Team t = new Team();
        addPlayers(t, jsonObject);
        return t;
    }
    // Modifies: t;
    //Effects: parses Team t from JSON object and adds it to the league

    private void addTeams(Tournament t, JSONObject jsonObject) {
        JSONArray arr = jsonObject.getJSONArray("teams");
        for (Object json : arr) {
            JSONObject nextTeam = (JSONObject) json;
            addTeam(t, nextTeam);
        }


    }
    // Modifies: t;
    //Effects: parses Team t from JSON object and adds it to the league

    private void addTeam(Tournament t, JSONObject jsonObj) {
        String name = jsonObj.getString("Team");
        Team team = new Team();
        team.setTeamName(name);
        t.addTeam(team);
        addPlayers(team, jsonObj);


    }
//Modifies: t
    //Effects: parses Players from JSON and adds it to the team which then adds to the the tournament

    private void addPlayers(Team t, JSONObject jsonObject) {
        JSONArray arr = jsonObject.getJSONArray("players");
        for (Object json : arr) {
            JSONObject nextTeam = (JSONObject) json;
            addPlayer(t, nextTeam);
        }

    }
    //Modifies: t
    // Effects: parses Player from JSON and adds it to the team which then adds to the the tournament

    private void addPlayer(Team t, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int goals = jsonObject.getInt("Goals");
        int pace = jsonObject.getInt("pace");
        int dribble = jsonObject.getInt("Dribbling Rate");
        int pass = jsonObject.getInt("Pass Accuracy");
        int position = jsonObject.getInt("Position");
        int physique = jsonObject.getInt("Physique");
        Player p = new Player();
        p.setPlayerName(name);
        p.setGoalsScored(goals);
        try {
            p.setPace(pace);
        } catch (PaceError e) {
            System.out.println("Cannot use negative pace");
        }
        p.setDribbleRate(dribble);
        p.setPassAccuracy(pass);
        p.setPosition(position);
        p.setPhysique(physique);
        t.addPlayers(p);


    }


}
