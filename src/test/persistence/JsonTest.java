package persistence;

import model.Player;
import model.Team;

import static org.junit.jupiter.api.Assertions.assertEquals;
// Reference: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class JsonTest {
    protected void checkTeam(Team team, String name) {
        assertEquals(name, team.getTeamName());
    }
    protected void checkPlayer(Player p, String name, int goals, int dribble, int physique, int pace, int passAccuracy, int position ) {
            assertEquals(name, p.getPlayerName());
            assertEquals(goals, p.getGoalsScored());
            assertEquals(dribble, p.getDribbleRate());
            assertEquals(physique, p.getPhysique());
            assertEquals(pace, p.getPace());
            assertEquals(passAccuracy, p.getPassAccuracy());
            assertEquals(position, p.getPosition());
    }
    }
