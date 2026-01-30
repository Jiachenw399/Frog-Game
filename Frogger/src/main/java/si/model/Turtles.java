package si.model;
import java.util.List;
/*
this class is the manager of turtles,which controls the moving and initiates turtles.
It can be combined with class Cars and class Logs, but I keep the original structure.
 */

public class Turtles implements Movable {

    private FroggerGame game;
    private List<Turtle> turtles;

    public Turtles(FroggerGame game) {
        this.game = game;
        turtles = new java.util.ArrayList<>();

        double riverTop = 60;
        double laneGap = 60;
        double turtleWidth = 45;
        double turtleHeight = 20;
        for(int j = 0; j < 4; j++) {
            for (int i = 0; i < 3; i++) {
                turtles.add(new Turtle(
                        170 * j + i * turtleWidth + 10,
                        riverTop + laneGap * 2,
                        turtleWidth,
                        turtleHeight,
                        1.3,
                        true
                ));
            }
        }
        //turtles on the first lane
        for(int j = 0; j < 4; j++) {
            for (int i = 0; i < 3; i++) {
                turtles.add(new Turtle(
                        190 * j + i * turtleWidth + 10,
                        riverTop,
                        turtleWidth,
                        turtleHeight,
                        1.4,
                        true
                ));
            }
        }
        game.setTurtles(turtles);
    }

    @Override
    public void move() {
        double screenW = FroggerGame.SCREEN_WIDTH;
        List<Turtle> turtles = game.getTurtles();
        double scale = game.getCurrentSpeedScale();
        if (turtles == null) return;
        for (Turtle t : turtles) {
            double dx = t.isMoveRight() ? t.getSpeed() * scale : -t.getSpeed() * scale;
            double newX = t.getX() + dx;

            //if it goes out of screen, appearing at the other side
            if (newX > screenW) {
                newX = -t.getWidth();
            } else if (newX < -t.getWidth()) {
                newX = screenW;
            }

            t.setX(newX);
        }
    }

    public List<Turtle> getTurtles() {
        return turtles;
    }
}