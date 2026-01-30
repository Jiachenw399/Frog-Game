package si.model;


import java.util.ArrayList;
import java.util.List;

public class Level {
    private Cars cars;
    private Logs logs;
    private Turtles turtles;
    private double speedScale;
    private FroggerGame game;

    public Level(double ss, FroggerGame g) {
        game = g;
        this.speedScale = ss;
        reset();
    }

    public double getSpeedScale() {
        return speedScale;
    }


    public List<Hittable> getHittable() {
        List<Hittable> targets = new ArrayList<Hittable>();
        targets.addAll(cars.getHittable());
        return targets;
    }

    public void reset() {
        cars = new Cars(game);
        logs = new Logs(game);
        turtles = new Turtles(game);
    }

    public void move() {
        cars.move();
        logs.move();
        turtles.move();
    }

}
