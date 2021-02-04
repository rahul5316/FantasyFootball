package model;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestTournament {
    Tournament tour1;
    Tournament tour2;
    Team t1;
    @org.junit.jupiter.api.Test
    public void addAndGetTeam(){
        tour1 = new Tournament();
        tour2 = new Tournament();
        t1= new Team();
        tour1.addTeam(t1);
        tour2.addTeam(t1);
        assertEquals(tour1.getTeams(),tour2.getTeams());


    }
}
