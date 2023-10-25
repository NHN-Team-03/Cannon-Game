package org.nhnacademy.tip;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

public class MovableBox extends Box implements Movable {
    final Vector motion;
    final List<Vector> effectList;
    Thread thread;
    long interval;

    public MovableBox(Point location, int width, int height, Color color) {
        super(location, width, height, color);

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

    public void setMotion(Vector motion) {
        this.motion.set(motion);
    }

    @Override
    public void setInterval(long interval) {
        this.interval = interval;
    }

    public Vector getMotion() {
        return motion;
    }

    public void addEffect(Vector effect) {
        effectList.add(effect);
    }

    public void next() {
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
