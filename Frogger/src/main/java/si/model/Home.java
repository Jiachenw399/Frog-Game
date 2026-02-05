package si.model;

import javafx.geometry.Rectangle2D;

public class Home {

    private Rectangle2D hitBox;
    private boolean occupied;

    public Home(double x, double y, double width, double height) {
        this.hitBox = new Rectangle2D(x, y, width, height);
        this.occupied = false;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    // 判断青蛙是不是到了这个家
    public boolean contains(Frog frog) {
        return hitBox.intersects(frog.getHitBox());
    }

    public Rectangle2D getHitBox() {
        return hitBox;
    }
}
