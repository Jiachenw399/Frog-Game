package si.model;

import java.util.List;

public class River{

    private List<Log> logs;
    private List<Turtle> turtles;

    private double riverTop;
    private double riverBottom;
    private FroggerGame game;


    public River(double top, double bottom, FroggerGame game) {
        this.riverTop = top;
        this.riverBottom = bottom;
        this.game = game;
    }
    public boolean checkFrogOnRiver(Frog frog, List<Log> logs, List<Turtle> turtles) {
        double scale = game.getCurrentSpeedScale();
        double y = frog.getY();

        if (y < riverTop || y > riverBottom) {
            frog.setOnObj(false);
            frog.setCarryingSpeed(0);
            return false;
        }

        frog.setOnObj(false);
        frog.setCarryingSpeed(0);

        for (Log log : logs) {
            if (frog.getHittable().intersects(log.getHittable())) {
                frog.setOnObj(true);
                int sign = log.isMoveRight() ? 1 : -1;
                frog.setCarryingSpeed(sign * log.getSpeed() * scale);
                return false;
            }
        }


        for (Turtle t : turtles) {
            if (frog.getHittable().intersects(t.getHittable())) {
                frog.setOnObj(true);
                int sign = t.isMoveRight() ? 1 : -1;
                frog.setCarryingSpeed(sign * t.getSpeed() * scale);
                return false;
            }
        }


        return true;
    }
}
