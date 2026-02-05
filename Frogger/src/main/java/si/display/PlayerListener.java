package si.display;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import si.model.Frog;
import si.model.FroggerGame;
/*
This class is used to interact with users,to make connection with the keyboard
 */

public class PlayerListener {
    private FroggerGame game;
    private boolean pause;
    private Frog frog;

    public PlayerListener(Frog frog, FroggerGame game) {
        this.frog = frog;
        this.game = game;
    }

    public void resetPause() {
        pause = false;
    }

    public boolean hasPressedPause() {
        return pause;
    }

public void setFrog(Frog frog) {
    this.frog = frog;
}
public void setListeners(Scene scene) {
    scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent e) {
            KeyCode code = e.getCode();
            if (game.isPaused() && code != KeyCode.P){return;}
            switch (code) {
                case UP:
                    if (frog != null) {
                        frog.jumpUp();
                        game.onFrogMovedUp();
                    }
                    break;
                case DOWN:
                    if (frog != null) frog.jumpDown();
                    break;
                case LEFT:
                    if (frog != null) frog.jumpLeft();
                    break;
                case RIGHT:
                    if (frog != null) frog.jumpRight();
                    break;
                case P:
                    game.togglePause();
                    break;
                default:
                    break;
            }
            game.getHomeManager().checkFrogReachHome(frog);
        }
    });}}
