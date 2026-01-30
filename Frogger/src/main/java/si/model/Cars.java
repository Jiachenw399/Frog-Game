package si.model;
/*
this class is the manager of cars,which controls the moving and initiates cars.
It can be combined with class Turtles and class Logs, but I keep the original structure.
 */

import java.util.ArrayList;
import java.util.List;

public class Cars implements Movable {
    private List<Car> cars;
    private final FroggerGame game;

    public Cars(FroggerGame g) {
        game = g;
        cars = new ArrayList<Car>();
        double carWidth = 40;
        double carHeight = 30;
        double roadTop = FroggerGame.SCREEN_HEIGHT * 0.6;
        double roadHeight = 50;

        //cars in the upper lane;
        for (int i = 0; i < 4; i++) {
            Car a;
            a = new Car(60 + i * 180, roadTop - 15, carWidth, carHeight, 0.77, false);
            cars.add(a);

        }
        //cars in the middle lane
        for (int i = 0; i < 3; i++) {
            Car a;
            a = new Car(120 + i * 220, roadTop + roadHeight, carWidth, carHeight, 0.75, true);
            cars.add(a);
        }
        //cars in the bottom lane
        for (int i = 0; i < 4; i++) {
            Car a;
            a = new Car(80 + i * 200, roadTop + roadHeight * 2, carWidth, carHeight, 0.80, false);
            cars.add(a);
        }
        game.setCars(cars);
    }

@Override
public void move() {
    double screenW = game.getScreenWidth();
    double scale = game.getCurrentSpeedScale();
    List<Car> cars = game.getCars();
    for (Car car : cars) {
        //if (car == null || !car.isAlive()) continue;
        double dx = car.isMoveRight() ? car.getSpeed() * scale: -car.getSpeed() * scale;; // move 3 every time
        double newX = car.getX() + dx;

        //if move outside the screen, getting into it again
        if (newX > screenW) {
            newX = -car.getWidth();
        } else if (newX < -car.getWidth()) {
            newX = screenW;
        }

        car.setX(newX);
    }
}

    public List<Hittable> getHittable() {
        return new ArrayList<Hittable>(cars);
    }

    public int getBottom() {
        return 0;
    }

    public List<Car> getCars() {
        return cars;
    }

}
