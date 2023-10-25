package org.nhnacademy.tip;

public class MovableWorld extends World implements Runnable {
    long interval;
    Thread thread;

    public MovableWorld(int width, int height) {
        super(width, height);
        interval = 10;
        thread = new Thread(this);
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
            ((Movable) bounds).addEffect(new Vector());
            ((Movable) bounds).start();
        }
    }

    public void start() {
        thread.start();
    }

    public void stop() {
        thread.interrupt();
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
    }
}
