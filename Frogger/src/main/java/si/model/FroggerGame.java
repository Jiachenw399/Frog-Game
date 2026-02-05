package si.model;

import ucd.comp2011j.engine.Game;
import javafx.geometry.Rectangle2D;
import si.display.PlayerListener;
import java.util.ArrayList;
import java.util.List;
/*
This class is the main class to launch the game, every function has to be called here
 */

public class FroggerGame implements Game {
    private int playerLives;
    private int playerScore;
    private int furthestLaneThisFrog = 0;
    public static final int SCREEN_WIDTH = 768;
    public static final int SCREEN_HEIGHT = 512;
    private static final Rectangle2D SCREEN_BOUNDS = new Rectangle2D(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
    private ArrayList<Hittable> targets;
    private PlayerListener listener;
    private Frog frog ;
    private Level[] level;
    private int currentLevel = 0;
    private boolean finishedAllLevels = false;
    public static final double STEP_SIZE = 56.8;
    private List<Log> logs;
    private Logs logsManager;
    private List<Turtle> turtles;
    private Turtles turtlesManager;
    private List<Car> cars;
    private Cars carsManager;
    private boolean state = false;
    private River riverManage;
    private static final double RIVER_TOP = 40;
    private static final double RIVER_BOTTOM = SCREEN_HEIGHT/2 - SCREEN_HEIGHT * 0.1;
    private HomeManager homeManager;
    private LevelTimer levelTimer = new LevelTimer();
    private boolean paused = false;

    private void checkCarCollisions() {
        if (!frog.isAlive() || state) return;
        for (Car car : cars) {
            if (frog.isHit(car)) {
                System.out.println("Frog got hit!");
                state = true;
                break;
            }
        }
    }

    public FroggerGame() {
        logsManager = new Logs(this);
        turtlesManager = new Turtles(this);
        carsManager = new Cars(this);
        logs = logsManager.getLogs();
        turtles = turtlesManager.getTurtles();
        cars = carsManager.getCars();
        riverManage = new River(
                RIVER_TOP,
                RIVER_BOTTOM, this);
        frog = new Frog(SCREEN_WIDTH/2 - 20, 463);
        homeManager = new HomeManager(this);
        startNewGame();
    }
    public List<Log> getLogs() {return logs;}
    public List<Turtle> getTurtles() {return turtles;}
    public List<Car> getCars() {return cars;}

    @Override
    public int getPlayerScore() {return playerScore;}
    public int getLives() {return playerLives;}
    public void checkForPause() {
        if (listener.hasPressedPause()) {
            paused = !paused;
            listener.resetPause();
        }
    }

    @Override
    public void updateGame() {
        levelTimer.update();
        if (!isPaused()) {
            targets.clear();
            targets.addAll(level[currentLevel].getHittable());
            targets.add(frog);
            level[currentLevel].move();
            carsManager.move();

            checkCarCollisions();
            if (state) {
                resetDestroyedPlayer();
                frog.setAlive(true);
                state = false;
            }
            boolean fellIntoWater = riverManage.checkFrogOnRiver(frog, logs, turtles);
            if (fellIntoWater) {
                resetDestroyedPlayer();
            }
            if (levelTimer.isTimeUp()){
                levelTimer.addExtraTime(30);
                resetDestroyedPlayer();
            }
            homeManager.checkFrogReachHome(frog);
            frog.update(1);
        }
    }

    public static Rectangle2D getScreenBounds() {
        return SCREEN_BOUNDS;
    }

    @Override
    public boolean isPaused() {
        return paused;
    }

    @Override
    public void startNewGame() {
        paused = true;
        targets = new ArrayList<Hittable>();
        playerLives = 3;
        playerScore = 0;
        levelTimer.reset();
        currentLevel = 0;
        finishedAllLevels = false;
        frog = new Frog(FroggerGame.SCREEN_WIDTH/2 - 5, 463);
        if (listener != null) {
            listener.setFrog(frog);
        }
        level = new Level[5];
        level[0] = new Level(1.0,  this);
        level[1] = new Level(1.3,  this);
        level[2] = new Level(1.7,  this);
        level[3] = new Level(2.2,  this);
        level[4] = new Level(3.3,  this);
        homeManager.initHomes();
    }
    public double getCurrentSpeedScale() {
        return level[currentLevel].getSpeedScale();
    }

    @Override
    public boolean isLevelFinished() {return homeManager.allHomesOccupied();}
    @Override
    public boolean isPlayerAlive() {return frog.isAlive();}

    @Override
    public void resetDestroyedPlayer() {
        System.out.println("Resetting player");
        playerLives = playerLives - 1;
        levelTimer.reset();
        frog.reset(FroggerGame.SCREEN_WIDTH/2 - 5, 463);
    }

    @Override
    public void moveToNextLevel() {
        paused = true;
        currentLevel++;
        frog.reset(FroggerGame.SCREEN_WIDTH/2 - 5, 463);

    }
    @Override
    public boolean isGameOver() {return playerLives <= 0 || finishedAllLevels;}
    public int getScreenWidth() {return SCREEN_WIDTH;}
    public int getScreenHeight() {return SCREEN_HEIGHT;}

    public Frog getFrog() {return frog;}
    public void setPlayerListener(PlayerListener listener) {this.listener = listener;}

    public void setLogs(List<Log> logs) {
        this.logs = logs;
    }
    public void setCars(List<Car> cars) {this.cars = cars;}
    public void setTurtles(List<Turtle> turtles){this.turtles = turtles;}


    public HomeManager getHomeManager() {return homeManager;}

    public LevelTimer getLevelTimer() {return levelTimer;}
    public void startNextLevel() {
        if (currentLevel >= level.length - 1) {
            System.out.println("You've passed all levels");
            finishedAllLevels = true;
            return;
        }
        currentLevel++;
        level[currentLevel].reset();
        homeManager.initHomes();
        frog.reset(FroggerGame.SCREEN_WIDTH / 2 - 5, 463);
        furthestLaneThisFrog = 0;
        levelTimer.reset();
    }

    public int getCurrentLevel() {return currentLevel;}
    public void onFrogMovedUp() {
        int lane = laneFromY(frog.getY());

        if (lane > furthestLaneThisFrog) {
            playerScore += 150;
            furthestLaneThisFrog = lane;
        }
    }
    private int laneFromY(double y) {return (int)((463 - y) / STEP_SIZE);}
    public void addScore(int score) {playerScore += score;}
    public void resetFurthestLane() {furthestLaneThisFrog = 0;}
    public void togglePause() {
        paused = !paused;
        if (paused) {
            levelTimer.pause();
        } else {
            levelTimer.resume();
        }
    }
}


