package si.model;


import javafx.geometry.Rectangle2D;

public class Frog implements Hittable {
    private double x, y;
    private Rectangle2D hitBox;
    private double oneStep = 56.8;
    private boolean alive = true;
    private boolean isOnObj = false;//judging weather the frog is standing on logs or turtles
    private double carryingSpeed;//the speed when carried by objects on the river

    public Frog(double x, double y) {
        this.x = FroggerGame.SCREEN_WIDTH/2 - 20;
        this.y = 463;
        hitBox = new Rectangle2D(this.x, this.y, 30, 30);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public boolean isHit(Car b) {
        boolean hit = hitBox.intersects(b.getHitBox());
        if (hit) {
            alive = false;
        }
        return hit;
    }

    public void moveBy(double dx, double dy) {
        this.x += dx;
        this.y += dy;

        hitBox = new Rectangle2D(this.x, this.y, 30, 30);
    }

    public void update(double dt) {
        if (!alive) return;

        if (isOnObj) {
            this.x += carryingSpeed * dt;
            // 更新碰撞箱
            this.hitBox = new Rectangle2D(this.x, this.y, hitBox.getWidth(), hitBox.getHeight());
        }
    }







    public boolean isAlive() {
        return alive;
    }


//go back to the start place if the frog is hit
public void reset(double x, double y) {
    this.x = x;
    this.y = y;
    this.alive = true;
    this.isOnObj = false;
    this.carryingSpeed = 0;
    this.hitBox = new Rectangle2D(this.x, this.y, hitBox.getWidth(), hitBox.getHeight());
}

    public int getPoints() {
        return -100;
    }

    public boolean isPlayer() {
        return true;
    }

    @Override
    public Rectangle2D getHitBox() {
        return hitBox;
    }

    public void move(double x1, double y1) {
        Rectangle2D newBox = new Rectangle2D(x + x1, y+y1,
                hitBox.getWidth(), hitBox.getHeight());
        if (FroggerGame.getScreenBounds().contains(newBox)) {
            hitBox = newBox;
            this.x += x1;
            this.y += y1;
        }
    }
//used for PlayerListener to control the direction
    public void jumpUp()    { move(0, -oneStep); }
    public void jumpDown()  { move(0,  oneStep); }
    public void jumpLeft()  { move(-oneStep, 0); }
    public void jumpRight() { move( oneStep, 0); }

    public void setAlive(boolean b) {
        this.alive = b;
    }

    public void setOnObj(boolean b) {
        this.isOnObj = b;
    }

    public void setCarryingSpeed(double i) {
        this.carryingSpeed = i;
    }

    public Rectangle2D getHittable() {
        return hitBox;
    }
}
