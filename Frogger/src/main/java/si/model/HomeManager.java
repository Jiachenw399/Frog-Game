package si.model;
/*
this class is used to implement the "home" function, including initiate their position, checking if the frog reach the home,
and checking if the home is occupied.
 */

import java.util.ArrayList;
import java.util.List;

public class HomeManager {
    private FroggerGame game;
    private final List<Home> homes = new ArrayList<>();

    public HomeManager(FroggerGame game) {
        this.game = game;
        initHomes();
    }

    //initiate 5 homes
    public void initHomes() {
        homes.clear();
        double topMargin = 5;
        double homeHeight = 40;
        homes.add(new Home( 98, topMargin, 60, homeHeight));
        homes.add(new Home(226, topMargin, 60, homeHeight));
        homes.add(new Home(354, topMargin, 60, homeHeight));
        homes.add(new Home(482, topMargin, 60, homeHeight));
        homes.add(new Home(610, topMargin, 60, homeHeight));
    }

    //a method used to check if frog get into home
    public void checkFrogReachHome(Frog frog) {
        // if the frog is not reaching the home, no operation
        if (frog.getY() > 60) {
            return;
        }
        //if the frog reaches one of homes(not occupied), record it and occupy it
        for (Home home : homes) {
            if (!home.isOccupied() && home.contains(frog)) {
                onFrogReachHome(home, frog);
                return;  // break after get into one home
            }
        }
        if (allHomesOccupied()) {
            game.startNextLevel();
        }
        frog.setAlive(false);

    }


    private void onFrogReachHome(Home home, Frog frog) {
        home.setOccupied(true);
        game.getLevelTimer().addExtraTime(30);
        game.addScore(500);
        if (allHomesOccupied()) {
            game.addScore(10000);   //10,000 points for completing a game
            game.addScore(game.getLevelTimer().getTimeRemainingInt() * 250); //250 points for every extra second
            game.startNextLevel();
            return;
        }

        //if the frog get into home, another frog appears at home
        frog.reset(FroggerGame.SCREEN_WIDTH / 2 - 5, 463
        );
        game.resetFurthestLane();

    }
    public List<Home> getHomes() {
        return homes;
    }


    //judging if all homes are occupied
    public boolean allHomesOccupied() {
        for (Home home : homes) {
            if (!home.isOccupied()) return false;
        }
        return true;
    }

}
