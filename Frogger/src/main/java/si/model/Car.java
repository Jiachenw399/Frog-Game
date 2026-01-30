package si.model;
/*
create a car class that is used to store basic instant variables and getter setter
methods, more operations is implemented in another class 'cars'
 */
import javafx.geometry.Rectangle2D;

public class Car implements Hittable {
    private boolean alive;
    private double x, y, width, height, speed;
    private boolean moveRight;
    private Rectangle2D hitBox;
    public static final int CAR_SCALE = 20;


    public Car(double x, double y, double width, double height, double speed, boolean moveRight) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = speed;
        this.moveRight = moveRight;
        hitBox = new Rectangle2D(x, y, width, height);
    }

    public boolean isMoveRight() {return moveRight; }

    public boolean isAlive() {
        return alive;
    }

    public int getPoints() {
        return 0;
    }

    public boolean isPlayer() {
        return false;
    }

    public void move(double cX, double cY) {
        x += cX;
        y += cY;
    }

    public Rectangle2D getHitBox() {
        return new Rectangle2D(x, y, CAR_SCALE , CAR_SCALE );
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getSpeed() {
        return speed;
    }

    public double getWidth() { return width;}

    public void setX(double x) {
        this.x = x;
        hitBox = new Rectangle2D(this.x, y ,width, height);
    }
}
