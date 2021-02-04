package ui;

import exception.PaceError;
import model.Player;
import model.Team;
import model.Tournament;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import java.util.List;
import java.util.Scanner;


public class FantasyFootballApp {
    // This FantasyFootball class here is the heart of the application and will help us to run our
    // desktop application through the main class which is contained in th UI package. Based on the teller app.
    //
    public static Scanner scan = new Scanner(System.in);
    private Team team;
    private Player player;
    private Tournament tournament;
    private static final String JSON_STORE = "./data/workroom.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    //EFFECTS-  The method below constructs a fantasy football app that creates a new tournament object which
// starts a tournament and runs the application and the methods inside of it.
    // Reference: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    public FantasyFootballApp() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        tournament = new Tournament();
        System.out.println("Start or End?");
        String ans = scan.nextLine().trim();
        if (ans.trim().equalsIgnoreCase("Start")) {
            while (!ans.trim().equalsIgnoreCase("End")) {
                userResponse();
                System.out.println("Start again or End?");
                ans = scan.nextLine().trim();
            }
        } else {
            System.out.println("The end!");
        }

    }

    // EFFECTS- Takes into account the user response and runs different methods accordingly
    private void userResponse() {
        System.out.println("Select number between 1 to 4 to perform the particular action!");
        displayBoard();
        int input = Integer.parseInt(scan.nextLine().trim());
        if (input == 1) {
            addTeam();
        } else if (input == 2) {
            addPlayersToYourChosenTeam();
        } else if (input == 3) {
            try {
                updateStatsOfaChosenPlayer();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (input == 4) {
            seeAllPlayersInaTeam(selectATeam());
        } else if (input == 5) {
            saveTournament();
        } else if (input == 6) {
            loadTournament();
        } else {
            System.out.println("Input not found.");
        }


    }
// EFFECT: Displays four user stories and what the user wishes to do

    private static void displayBoard() {
        System.out.println("Press '1' to add team!");
        System.out.println("Press '2' to select team and add players!");
        System.out.println("Press '3' to update the stats of a particular player of a team. ");
        System.out.println("Press '4' to see all players in a team");
        System.out.println("\t5 -> save work room from file");
        System.out.println("\t6 -> load work room to file");

    }
// EFFECT- Adds a team to the tournament by asking user the name of the team and adds the team to the list.

    private void addTeam() {
        team = new Team();
        System.out.print("What would you like to name your team?");
        String teamName = scan.nextLine().trim();
        team.setTeamName(teamName);
        tournament.addTeam(team);


    }
    //EFFECTS- Selects a team based on the users input and names the player and adds it to the same team.

    private void addPlayersToYourChosenTeam() {
        team = selectATeam();
        System.out.print("Name your Player and add it");
        String playerName = scan.nextLine().trim();
        player = new Player();
        player.setPlayerName(playerName);
        team.addPlayers(player);


    }
    //EFFECTS: Selects a team based on the user's input

    private Team selectATeam() {
        System.out.println("What team do you want to select?");
        String input = scan.nextLine().trim();
        for (Team team : tournament.getTeams()) {
            if (team.getTeamName().equalsIgnoreCase(input)) {
                return team;
            }
        }

        return team;
    }

    //EFFECTS: Selects a player based on the user input and updates the statistics of the player

    private void updateStatsOfaChosenPlayer()  {
        player = selectParticularPlayer();
        System.out.println("Number of goals Scored:");
        int x1 = Integer.parseInt(scan.nextLine().trim());
        player.setGoalsScored(x1);
        System.out.println("Pace of the player:");
        int x2 = Integer.parseInt(scan.nextLine().trim());
        try {
            player.setPace(x2);
        }  catch (PaceError e) {
            e.printStackTrace();
        }
        System.out.println("Dribbling rate of the player:");
        int x3 = Integer.parseInt(scan.nextLine().trim());
        player.setDribbleRate(x3);
        System.out.println("Pass Accuracy of the player");
        int x4 = Integer.parseInt(scan.nextLine().trim());
        player.setPassAccuracy(x4);
        System.out.println("Position of the player");
        int x5 = Integer.parseInt(scan.nextLine().trim());
        player.setPassAccuracy(x5);
        System.out.println("Physique of the player:");
        int x6 = Integer.parseInt(scan.nextLine().trim());
        player.setPhysique(x6);

    }
//EFFECTS: Selects a a particular player based on the user's input and returns it

    private Player selectParticularPlayer() {
        System.out.println("What player do you want?");
        String input = scan.nextLine().trim();
        for (Player player : team.getPlayers()) {
            if (player.getPlayerName().equalsIgnoreCase(input)) {
                return player;
            }
        }
        return player;

    }

    //EFFECTS: Displays all the players in a team chosen by the user
    private void seeAllPlayersInaTeam(Team team) {
        List<Player> playerList = team.getPlayers();
        for (Player player : playerList) {
            System.out.println(player.getPlayerName());
        }
    }
    // EFFECTS: saves the workroom to file

    private void saveTournament() {
        try {
            jsonWriter.open();
            jsonWriter.write(tournament);
            jsonWriter.close();
            System.out.println("Saved " + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    private void loadTournament() {
        try {
            tournament = jsonReader.read();
            System.out.println("Loaded " + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}
