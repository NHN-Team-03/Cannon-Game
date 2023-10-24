package org.nhnacademy.tip;

public class MovableWorld extends World implements Runnable {
    Thread thread;
    long interval;

    public MovableWorld(int width, int height) {
        super(width, height);
        interval = 100;
        thread = new Thread(this);
    }

    public void start() {
        thread.start();
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
            ((Movable) bounds).setInterval(interval);
            ((Movable) bounds).start();
        }
    }


    @Override
    public void run() {
        while (!Thread.interrupted()) {
            try {
                repaint();
                Thread.sleep(interval);
            } catch (InterruptedException ignore) {
                Thread.currentThread().interrupt();
            }
        }

        for (Bounds bounds : getBoundsList()) {
            if (bounds instanceof Movable) {
                ((Movable) bounds).stop();
            }
        }

        boundsList.clear();
    }
}
