package org.nhnacademy.tip;

public class MovableWorld extends World {
    long interval;

    public MovableWorld(int width, int height) {
        super(width, height);
        interval = 100;
    }

    public long getInterval() {
        return interval;
    }

    public void setInterval(long interval) {
        this.interval = interval;
    }

    public void add(Bounds bounds) {
        super.add(bounds);
        if (bounds instanceof Movable) {
            ((Movable) bounds).start();
            Thread boundThread = new Thread((Movable) bounds);
            boundThread.start();
        }
    }


    public void run(long seconds) {
        long startTime = System.currentTimeMillis();

        while (!Thread.interrupted() && (System.currentTimeMillis() < startTime + seconds * 1000)) {
            try {
                repaint();
                Thread.sleep(interval);
            } catch (InterruptedException ignore) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
