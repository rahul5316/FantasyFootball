package model;



import exception.PaceError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    // delete or rename this class!
   Player p1;
   Player p2;

@BeforeEach
public void Setup(){
    p1 = new Player();
    p2 = new Player();
}

    @org.junit.jupiter.api.Test
    public void playerToString()  {
        p1.setPlayerName("Ronaldo");
        p1.setPhysique(80);
        try {
            p1.setPace(94);
        } catch (PaceError e) {
            System.out.println("Cannot use negative pace");
        }
        p1.setPassAccuracy(90);
        p1.setDribbleRate(92);
        p1.setGoalsScored(6);
        p1.setPosition(5);
        assertEquals(p1.toString(),"Player{" + "playerName='" + p1.getPlayerName() + '\'' + ", goalsScored=" + p1.getGoalsScored()
                + ", pace=" + p1.getPace() + ", dribbleRate=" + p1.getDribbleRate() + ", passAccuracy=" + p1.getPassAccuracy()
                + ", position=" + p1.getPosition() + ", physique=" + p1.getPhysique() + '}' );

        p2.setPlayerName("Ronaldo");
        p2.setPhysique(80);
        try {
            p2.setPace(94);
        } catch (PaceError e) {
            System.out.println("Cannot use negative pace");
        }
        p2.setPassAccuracy(90);
        p2.setDribbleRate(92);
        p2.setGoalsScored(6);
        p2.setPosition(5);
        assertEquals(p2.toString(),"Player{" + "playerName='" + p2.getPlayerName() + '\'' + ", goalsScored=" + p2.getGoalsScored()
                + ", pace=" + p2.getPace() + ", dribbleRate=" + p2.getDribbleRate() + ", passAccuracy=" + p2.getPassAccuracy()
                + ", position=" + p2.getPosition() + ", physique=" + p2.getPhysique() + '}' );

    }

    @Test
    public void testPace()  {
        try {
            p2.setPace(150);
        } catch (PaceError e) {
            System.out.println("Cannot use negative pace");
        }
        assertTrue(p2.getPace() == 100);

    }






}