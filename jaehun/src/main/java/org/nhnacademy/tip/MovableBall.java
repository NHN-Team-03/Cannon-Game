package org.nhnacademy.tip;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

public class MovableBall extends Ball implements Movable {
    final Vector motion;
    final List<Vector> effectList;
    Thread thread;
    long interval;

    public MovableBall(Point location, int radius, Color color) {
        super(location, radius, color);

        motion = new Vector();
        effectList = new LinkedList<>();
        thread = new Thread(this);
        interval = 0;
    }

    public void start() {
        thread.start();
    }

    public void stop() {
        thread.interrupt();
    }

    public void setInterval(long interval) {
        this.interval = interval;
    }


    public void setMotion(Vector motion) {
        this.motion.set(motion);
    }

    public Vector getMotion() {
        return motion;
    }

    public void addEffect(Vector effect) {
        effectList.add(effect);
    }

    public void next() {
        for (Vector effect : effectList) {
            getMotion().add(effect);
        }

        location.move(motion.getDisplacement());
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                next();
                Thread.sleep(interval);
            } catch (InterruptedException ignore) {
                Thread.currentThread().interrupt();
            }

        }
    }
}
