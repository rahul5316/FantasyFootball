package persistence;

import model.Tournament;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
// Reference: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

public class JsonReaderTest extends JsonTest {


    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Tournament t = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }


    //./data/testReaderEmptyWorkRoom.json
    @Test
    void testReaderEmptyListOfTeams() {
        JsonReader reader = new JsonReader("./data/testWriterEmptyWorkroom.json");
        try {
            Tournament t = new Tournament();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyWorkroom.json");
            writer.open();
            writer.write(t);
            writer.close();

            t = reader.read();
            assertEquals(0, t.numTeams());

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }



}
