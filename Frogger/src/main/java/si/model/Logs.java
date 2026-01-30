package si.model;
import java.util.ArrayList;
import java.util.List;
/*
this class is implemented the interface Movable to realize moving function and
make connection with the FroggerGame to control the game
this class is the manager of logs,which controls the moving and initiates logs.
It can be combined with class Turtles and class Cars, but I keep the original structure.
 */
public class Logs implements Movable {
    private FroggerGame game;
    private List<Log> logs;

    public Logs(FroggerGame game) {
        this.game = game;
        double riverTop = 60;
        double logHeight = 20;
        double laneGap = 60;
        logs = new ArrayList<Log>();
        // logs at the second lane
        for (int i = 0; i < 3; i++) {
            Log log = new Log(120 + i * 220, riverTop + laneGap, 120, logHeight, 0.75, false);
            logs.add(log);
        }
        game.setLogs(logs);
    }

    @Override
    public void move() {
        double screenW = FroggerGame.SCREEN_WIDTH;
        //get logs out of the list
        List<Log> allLogs = game.getLogs();
        double scale = game.getCurrentSpeedScale();
        for (Log log : allLogs) {
            double dx = log.isMoveRight() ? log.getSpeed() * scale: -log.getSpeed() * scale;
            double newX = log.getX() + dx;

            //if they move outside the screen, appearing from the other side
            if (newX > screenW) newX = -log.getWidth();
            if (newX < -log.getWidth()) newX = screenW;

            log.setX(newX);
        }
    }

    public List<Log> getLogs() {
        return logs;
    }
}

