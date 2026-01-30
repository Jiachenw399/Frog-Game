package si.model;
/*
create a turtle class that is used to store basic instant variables and getter setter
methods, more operations is implemented in another class 'turtles'
 */
import javafx.geometry.Rectangle2D;

public class Turtle {
    private double x, y, width, height, speed;
    private boolean moveRight;
    private Rectangle2D hitBox;
    public static final int TURTLE_SCALE = 2;

    public Turtle(double x, double y, double width, double height, double speed, boolean moveRight){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = speed;
        this.moveRight = moveRight;
        hitBox = new Rectangle2D(x, y, width, height);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public double getSpeed() {
        return speed;
    }

    public boolean isMoveRight(){
        return moveRight;
    }

    public void setX(double x) {
        this.x = x;
        hitBox = new Rectangle2D(this.x, y, width, height);
    }

    public Rectangle2D getHittable() {
        return hitBox;
    }
}
