package ui;

import exception.PaceError;
import model.Player;
import model.Team;
import persistence.JsonReader;
import persistence.JsonWriter;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;


import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

// Reference : https://www.youtube.com/watch?v=Kmgo00avvEw&ab_channel=BroCode

public class SwingGui extends JFrame implements ActionListener {

    static final String JSON_STORE = "./data/SwingGui.json";
    JsonWriter j1;
    JsonReader j2;
    Player p1;
    Team t1;
    JTable jtable;
    DefaultTableModel defualtTable;
    InputStream sound;
    File file;
    ImageIcon icon;

//EFFECTS: Constructs a SwingGui constructor. This method constructs a team
// object and names the popup fantasy football app using which the application runs.
    // It also takes in the soccer image which displays when the application runs.

    public SwingGui() {
        super("Fantasy Football App");
        j1 = new JsonWriter(JSON_STORE);
        j2 = new JsonReader(JSON_STORE);
        t1 = new Team();
        addPlayer.addActionListener(this);
        numPlayers.addActionListener(this);
        save.addActionListener(this);
        load.addActionListener(this);
        try {
            file = new File("./data/soccer.jpg");
            icon = new ImageIcon(ImageIO.read(file));
            setContentPane(new JLabel(icon));

        } catch (IOException e) {
            e.printStackTrace();
        }
        pack();
        setSize(600, 750);
        setResizable(true);
        setLayout(new FlowLayout());
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initialization();
    }

    String[] top = new String[]{"Player Name", "Goals Scored", "Pace", "Dribble Rate", "Pass Accuracy",
            "Position", "Physique"};
    JTextField textName = new JTextField(15);
    JTextField textPace = new JTextField(5);
    JTextField textGoals = new JTextField(5);
    JTextField textDribbleRate = new JTextField(5);
    JTextField textPassAccuracy = new JTextField(5);
    JTextField textPosition = new JTextField(5);
    JTextField textPhysique = new JTextField(5);
    JButton addPlayer = new JButton("Add Player");
    JButton save = new JButton("Save");
    JButton load = new JButton("Load");
    JButton numPlayers = new JButton("Number of Players");
    JLabel playerName = new JLabel("Name of the Player");
    JLabel goals = new JLabel("Goals Scored:");
    JLabel pace = new JLabel("Pace:");
    JLabel dribble = new JLabel("Dribbling Rate:");
    JLabel passAccuracy = new JLabel("Pass Accuracy:");
    JLabel position = new JLabel("Position of the Player");
    JLabel physique = new JLabel("Physique");
    JLabel numberOfPlayers = new JLabel("Number of Players");


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addPlayer) {
            try {
                playerInitialization();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            sound("./data/playerAdded.wav");
        } else if (e.getSource() == load) {
            load();
            sound("./data/load.wav");

        } else if (e.getSource() == save) {
            save();
            sound("./data/saved.wav");
        } else if (e.getSource() == numPlayers) {
            numberOfPlayers();
            sound("./data/whistle.wav");
        }
    }

    //EFFECTS: Initializes the player object

    private void playerInitialization() {

        String name = textName.getText();
        int goals = Integer.parseInt(textGoals.getText());
        int pace = Integer.parseInt(textPace.getText());
        int dribble = Integer.parseInt(textDribbleRate.getText());
        int passAccuracy = Integer.parseInt(textPassAccuracy.getText());
        int position = Integer.parseInt(textPosition.getText());
        int physique = Integer.parseInt(textPhysique.getText());
        p1 = new Player();
        p1.setPlayerName(name);
        p1.setGoalsScored(goals);
        try {
            p1.setPace(pace);
        } catch (PaceError e) {
            e.printStackTrace();
        }
        p1.setDribbleRate(dribble);
        p1.setPassAccuracy(passAccuracy);
        p1.setPosition(position);
        p1.setPhysique(physique);
        t1.addPlayers(p1);
        defualtTable.setRowCount(0);
        defualtTable.addRow(top);
        showplayerProfile();


    }


    //EFFECTS: Displays the profile of the players of a particular team in the form of a table
    private void showplayerProfile() {
        for (int i = 0; i < t1.getPlayers().size(); i++) {
            Object[] object = {t1.getPlayers().get(i).getPlayerName(), t1.getPlayers().get(i).getGoalsScored(),
                    t1.getPlayers().get(i).getPace(), t1.getPlayers().get(i).getDribbleRate(),
                    t1.getPlayers().get(i).getPassAccuracy(), t1.getPlayers().get(i).getPosition(),
                    t1.getPlayers().get(i).getPhysique()};
            defualtTable.addRow(object);
        }
    }


    //EFFECTS: Initializes and add buttons of various actions to the frame
    private void initialization() {

        numberOfPlayers.setFont(new Font("Best player to be decided based on the number of goals"
                + " scored", Font.ITALIC, 20));
        numberOfPlayers.setForeground(Color.BLACK);
        add(playerName);
        add(textName);
        add(goals);
        add(textGoals);
        add(pace);
        add(textPace);
        add(dribble);
        add(textDribbleRate);
        add(passAccuracy);
        add(textPassAccuracy);
        add(position);
        add(textPosition);
        add(physique);
        add(textPhysique);
        add(addPlayer);
        add(numPlayers);
        add(save);
        add(load);
        add(numberOfPlayers);
        tableInitialization();


    }

    //EFFECTS: Initializes and constructs a new table object and then adds it.

    private void tableInitialization() {
        jtable = new JTable();
        defualtTable = new DefaultTableModel(top, 0);
        jtable.setModel(defualtTable);
        add(jtable);
    }

    //EFFECTS: Shows the number of player in the team as the output.

    private void numberOfPlayers() {

        add(numberOfPlayers);
        numberOfPlayers.setText("Number of Players in the team: " + t1.getPlayers().size());
        numberOfPlayers.setForeground(Color.RED);

    }

    //EFFECTS: loads the saved team from the file

    private void load() {
        try {
            t1 = j2.teamReader();
            showplayerProfile();
        } catch (IOException exception) {
            System.out.println("File not found");
        }
    }
// EFFECTS: Saves the player in a team

    private void save() {
        try {
            j1.open();
            j1.teamWriter(t1);
            j1.close();
        } catch (FileNotFoundException e) {
            System.out.println("Could not Save");
        }
    }

// EFFECTS: reads sound from the file

    private void sound(String soundFile) {
        try {
            sound = new FileInputStream(soundFile);
            AudioStream audio = new AudioStream(sound);
            AudioPlayer.player.start(audio);

        } catch (Exception e) {
            System.out.println("Sound not detected");
        }
    }


}
