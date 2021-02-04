package model;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestTeam {

    Team t1;
    Team t2;
    @org.junit.jupiter.api.Test
    public void setNameGetNameAddPlayersGetPlayers(){
        t1 = new Team();
        t2 = new Team();
        Player p1 = new Player();
        t1.addPlayers(p1);
        t2.addPlayers(p1);
        t1.setTeamName("Tottenham HotSpurs");
        assertEquals(t1.getPlayers(), t2.getPlayers());
        assertEquals(t1.getTeamName(), "Tottenham HotSpurs");


    }
}
