package si.model;

public class LevelTimer {

    private static final double START_TIME = 30.0;

    private double timeRemaining;
    private long lastUpdateNanos;
    private boolean running;

    public LevelTimer() {
        reset();
    }
    public void reset() {
        timeRemaining = START_TIME;
        lastUpdateNanos = System.nanoTime();
        running = true;
    }
    public void update() {

        long now = System.nanoTime();
        if (!running) {
            lastUpdateNanos = now;
            return;
        }
        double deltaSeconds = (now - lastUpdateNanos) / 1_000_000_000.0;
        lastUpdateNanos = now;

        timeRemaining -= deltaSeconds;
        if (timeRemaining < 0) {
            timeRemaining = 0;
            running = false;
        }
    }

    public int getTimeRemainingInt() {
        return (int) Math.ceil(timeRemaining);
    }

    public boolean isTimeUp() {
        return timeRemaining <= 0;
    }

    public void addExtraTime(double seconds) {
        timeRemaining += seconds;
        lastUpdateNanos = System.nanoTime();
        running = true;
    }

    public void pause() {
        running = false;
    }

    public void resume() {
        running = true;
        lastUpdateNanos = System.nanoTime();
    }
}
