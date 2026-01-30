package si.display;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import si.model.Frog;
import ucd.comp2011j.engine.GameManager;
import si.model.FroggerGame;
import ucd.comp2011j.engine.ScoreKeeper;

public class ApplicationStart  extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        StackPane root = new StackPane();
        Scene scene = new Scene(root, FroggerGame.SCREEN_WIDTH, FroggerGame.SCREEN_HEIGHT);
        FroggerGame game = new FroggerGame();
        Frog frog = game.getFrog();
        PlayerListener playerListener = new PlayerListener(frog, game);
        playerListener.setListeners(scene);
        game.setPlayerListener(playerListener);
        MenuListener menuListener = new MenuListener();
        menuListener.setListeners(scene);
        primaryStage.setTitle("Frogger Game");
        GameScreen gameScreen = new GameScreen(game);
        MenuScreen menuScreen = new MenuScreen();
        ScoreKeeper scoreKeeper = new ScoreKeeper("scores.txt");
        GameManager mmm = new GameManager(game, root, menuListener, menuScreen,new AboutScreen(),new ScoreScreen(scoreKeeper), gameScreen, scoreKeeper);
        menuScreen.paint();
        primaryStage.setScene(scene);
        primaryStage.show();
        mmm.run();
    }
}