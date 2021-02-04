package persistence;

import exception.PaceError;
import model.Player;
import model.Team;
import model.Tournament;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class JsonWriterTest extends JsonTest {
    //NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter is to
    //write data to a file and then use the reader to read it back in and check that we
    //read in a copy of what was written out.

    // Reference: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

    @Test
    void testWriterInvalidFile() {
        try {
           Tournament t = new Tournament();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyTournament() {
        try {
            Tournament t = new Tournament();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyWorkroom.json");
            writer.open();
            writer.write(t);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyWorkroom.json");
            t = reader.read();
            assertEquals(0, t.numTeams());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralTournament() {
        try {

            Tournament t = new Tournament();
            Player p = new Player();
            Team team = new Team();
            p.setPlayerName("Kevin Debruyne");
            p.setPhysique(90);
            p.setPosition(17);
            p.setPassAccuracy(94);
            p.setDribbleRate(88);
            try {
                p.setPace(85);
            } catch (PaceError e) {
                System.out.println("Cannot use negative pace");
            }
            p.setGoalsScored(50);
            team.addPlayers(p);
            t.addTeam(team);
            team.setTeamName("Man City");

                        JsonWriter writer = new JsonWriter("./data/testWriterGeneralWorkroom.json");
            writer.open();
            writer.write(t);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralWorkroom.json");
            t = reader.read();
            assertEquals(1, t.numTeams());
            checkTeam(team, "Man City");
            checkPlayer(p,"Kevin Debruyne",50, 88,90,85,94,17 );

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}