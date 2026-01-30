package si.model;

import javafx.geometry.Rectangle2D;

public interface Hittable{
	public boolean isAlive();
	public int getPoints();
	public boolean isPlayer();
	public Rectangle2D getHitBox();
}

