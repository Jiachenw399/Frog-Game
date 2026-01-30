package si.display;

import javafx.geometry.Rectangle2D;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import si.model.*;
import ucd.comp2011j.engine.Screen;
import java.util.ArrayList;
import java.util.List;


public class GameScreen implements Screen {
    private static final long serialVersionUID = -8282302849760730222L;
    private FroggerGame game;
    private Canvas canvas;
    public Canvas getCanvas(){return canvas;}


    public GameScreen(FroggerGame game) {
        this.game = game;
        this.canvas = new Canvas(FroggerGame.SCREEN_WIDTH, FroggerGame.SCREEN_HEIGHT);
    }
private void drawShape(GraphicsContext gc, Frog p) {
    double x = p.getX();
    double y = p.getY();
    gc.setFill(Color.GREEN);
    gc.fillRect(x + 5,  y + 16, 34, 16);
    gc.fillRect(x + 12, y + 8, 20, 10);
    gc.fillRect(x + 2,  y + 18, 8, 10);
    gc.fillRect(x + 34, y + 18, 8, 10);
    gc.fillRect(x + 12, y + 30, 8, 10);
    gc.fillRect(x + 24, y + 30, 8, 10);
    gc.setFill(Color.WHITE);
    gc.fillOval(x + 14, y + 4, 8, 8);
    gc.fillOval(x + 26, y + 4, 8, 8);
    gc.setFill(Color.BLACK);
    gc.fillOval(x + 17, y + 7, 3, 3);
    gc.fillOval(x + 29, y + 7, 3, 3);
}


    private void drawCars(GraphicsContext gc, Car car) {
        double x = car.getX();
        double y = car.getY();
        double s = Car.CAR_SCALE;
        double bodyW = s * 2.4;
        double bodyH = s * 1.1;
        double bodyX = x;
        double bodyY = y + s * 0.4;

        gc.setFill(Color.YELLOW);
        gc.fillRoundRect(bodyX, bodyY, bodyW, bodyH, 8, 8);
        double roofH = bodyH * 0.55;
        double roofW = bodyW * 0.65;
        double roofX = bodyX + bodyW * 0.12;
        double roofY = bodyY - roofH + 3;

        gc.fillRoundRect(roofX, roofY, roofW, roofH, 8, 8);

        double noseW = bodyW * 0.22;
        double noseH = bodyH * 0.9;
        double noseX = bodyX + bodyW - noseW;
        double noseY = bodyY + bodyH * 0.05;

        gc.setFill(Color.ORANGE);
        gc.fillRoundRect(noseX, noseY, noseW, noseH, 10, 10);

        gc.setFill(Color.BLUE);
        double winX = roofX + roofW * 0.08;
        double winY = roofY + roofH * 0.2;
        double winW = roofW * 0.75;
        double winH = roofH * 0.55;
        gc.fillRoundRect(winX, winY, winW, winH, 6, 6);

        gc.setFill(Color.GRAY);
        double wheelR = bodyH * 0.55;
        double w1x = bodyX + bodyW * 0.20;
        double wy  = bodyY + bodyH - wheelR / 2;
        gc.fillOval(w1x, wy, wheelR, wheelR);
        double w2x = bodyX + bodyW * 0.65;
        gc.fillOval(w2x, wy, wheelR, wheelR);
    }
    private void drawLogs(GraphicsContext gc, Log log) {
        double x = log.getX();
        double y = log.getY();
        double s = Log.LOG_SCALE;
        double bodyW = 70 * s;
        double bodyH = 17.5 * s;

        gc.setFill(Color.rgb(0, 0, 0, 0.30));
        gc.fillRoundRect(x + 4 * s, y + 4 * s, bodyW, bodyH, 18 * s, 18 * s);
        gc.setFill(Color.web("#d68a5a"));
        gc.fillRoundRect(x, y, bodyW, bodyH, 18 * s, 18 * s);
        gc.setFill(Color.web("#f7efe5"));
        double dashW = 9 * s;
        double dashH = 3 * s;

        gc.fillRoundRect(x + 10 * s, y + 3 * s, dashW, dashH, 3 * s, 3 * s);
        gc.fillRoundRect(x + 24 * s, y + 6 * s, dashW * 0.8, dashH, 3 * s, 3 * s);
        gc.fillRoundRect(x + 38 * s, y + 4 * s, dashW * 0.7, dashH, 3 * s, 3 * s);
        gc.fillRoundRect(x + 52 * s, y + 7 * s, dashW * 0.9, dashH, 3 * s, 3 * s);

        double capOuter = bodyH * 1.05;
        double capInner = bodyH * 0.80;
        double capX = x + bodyW - capOuter * 0.55;
        double capY = y + (bodyH - capOuter) / 2;
        gc.setFill(Color.web("#f8eadc"));
        gc.fillOval(capX, capY, capOuter, capOuter);
        double innerX = capX + (capOuter - capInner) / 2;
        double innerY = capY + (capOuter - capInner) / 2;
        gc.setFill(Color.web("#d8895b"));
        gc.fillOval(innerX, innerY, capInner, capInner);
    }

private void drawTurtles(GraphicsContext gc, Turtle turtle) {
    double x = turtle.getX();
    double y = turtle.getY();
    double s = Turtle.TURTLE_SCALE; // size scale
    double shellW = 18 * s;
    double shellH = 10 * s;
    double shellX = x;
    double shellY = y + 4 * s;
    gc.setFill(Color.DARKGREEN);
    gc.fillOval(shellX, shellY, shellW, shellH);
    gc.setFill(Color.GREEN);
    gc.fillOval(shellX + 4*s, shellY + 3*s, 10*s, 5*s);
    double headSize = 7 * s;
    double headX = shellX + shellW - 2 * s;
    double headY = shellY + 2 * s;
    gc.setFill(Color.DARKGREEN);
    gc.fillOval(headX, headY, headSize, headSize);
    gc.setFill(Color.WHITE);
    gc.fillOval(headX + 3*s, headY + 1*s, 2.5*s, 2.5*s);
    gc.setFill(Color.BLACK);
    gc.fillOval(headX + 3.8*s, headY + 1.7*s, 1.2*s, 1.2*s);
    gc.setFill(Color.DARKGREEN);
    double legW = 3 * s;
    double legH = 4 * s;
    gc.fillRect(shellX + 2*s, shellY + 1*s, legW, legH);
    gc.fillRect(shellX + 2*s, shellY + 7*s, legW, legH);
    gc.fillRect(shellX + 12*s, shellY + 1*s, legW, legH);
    gc.fillRect(shellX + 12*s, shellY + 7*s, legW, legH);
}



    public void drawOccupiedHomes(GraphicsContext gc) {
        gc.setFill(Color.DARKGREEN);
        double margin = 5;
        final List<Home> homes = new ArrayList<>();
        for (Home home : game.getHomeManager().getHomes()) {
            if (home.isOccupied()) {
                Rectangle2D r = home.getHitBox();
                gc.fillRect(
                        r.getMinX() + margin,
                        r.getMinY() + margin,
                        r.getWidth()  - 2 * margin,
                        r.getHeight() - 2 * margin
                );
            }
        }
    }

    public void paint() {
        double height = FroggerGame.SCREEN_HEIGHT;
        GraphicsContext gc = this.canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, FroggerGame.SCREEN_WIDTH, FroggerGame.SCREEN_HEIGHT);
        //clear the screen
        if (game != null) {
            gc.setFill(Color.BLACK);
            gc.fillRect(0, height/2, FroggerGame.SCREEN_WIDTH, height/2);
            //fill the screen at bottom with black color
            gc.setFill(Color.DEEPSKYBLUE);
            gc.fillRect(0, 0, FroggerGame.SCREEN_WIDTH, height/2);
            //fill the upper screen with blue(river)
            gc.setFill(Color.BROWN);
            gc.fillRect(0, height*0.45, FroggerGame.SCREEN_WIDTH, height*0.1);
            gc.setFill(Color.BROWN);
            gc.fillRect(0, height*0.9, FroggerGame.SCREEN_WIDTH, height*0.1);
            //fill the color in brown in the middle and at the start
            gc.setFill(Color.LIMEGREEN);
            int home = 6; // five homes in total
            double homeWidth = 60;
            double homeHeight = 40;
            double topMargin = 5;
            double space = FroggerGame.SCREEN_WIDTH / home;
            for (int i = 0; i < home; i++) {
                double x = i * space + space / 2 - homeWidth / 2;
                gc.fillRect(x, topMargin, homeWidth, homeHeight);
            }
            drawOccupiedHomes(gc);
            gc.fillRect(0, 0, FroggerGame.SCREEN_WIDTH, topMargin);
            gc.fillRect(0, topMargin, 40, homeHeight);
            gc.fillRect(FroggerGame.SCREEN_WIDTH*0.95, topMargin, 40, homeHeight);
            //fill the 'home' color

            gc.setFill(Color.WHITESMOKE);
            gc.setTextAlign(TextAlignment.LEFT);
            //the text color
            gc.setTextBaseline(VPos.TOP);
            gc.setFont(new Font("Arial", 24));
            gc.fillText("Lives: " + game.getLives(), 0, 0);
            gc.setTextAlign(TextAlignment.RIGHT);
            gc.fillText("Score: " + game.getPlayerScore(), FroggerGame.SCREEN_WIDTH, 0);
            int time = game.getLevelTimer().getTimeRemainingInt();
            gc.fillText("Time: " + time, FroggerGame.SCREEN_WIDTH / 2 - 60, 30);
            gc.fillText("Level: " + (game.getCurrentLevel()+1) ,
                    FroggerGame.SCREEN_WIDTH / 2 + 60,
                    30);

            for (Car car : game.getCars()) {
                drawCars(gc, car);
            }

            for (Log log : game.getLogs()) {
                drawLogs(gc, log);
            }

            for(Turtle turtle : game.getTurtles()){
                drawTurtles(gc, turtle);
            }
            drawShape(gc, game.getFrog());

            if ((game.isPaused() || !game.isPlayerAlive()) && game.getLives() > 0) {
                gc.setTextAlign(TextAlignment.CENTER);
                gc.setTextBaseline(VPos.CENTER);
                gc.setFont(new Font("Arial", 24));
                gc.setFill(Color.GREEN);
                gc.fillText("Press 'p' to continue ", FroggerGame.SCREEN_WIDTH/2, FroggerGame.SCREEN_HEIGHT/2);

            } else if (!game.isPlayerAlive() && game.getLives() == 0) {
                gc.setTextAlign(TextAlignment.CENTER);
                gc.setTextBaseline(VPos.CENTER);
                gc.setFont(new Font("Arial", 48));
                gc.setFill(Color.GREEN);gc.fillText("Game over ", FroggerGame.SCREEN_WIDTH/2, FroggerGame.SCREEN_HEIGHT/2);
            }
        }
    }
}
