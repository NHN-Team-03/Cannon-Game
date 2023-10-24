package org.nhnacademy.tip;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

public class MovableBox extends Box implements Movable {
    Thread thread;
    long interval;
    final Vector motion;
    final List<Vector> effectList;

    public MovableBox(Point location, int width, int height, Color color) {
        super(location, width, height, color);

        thread = new Thread(this);
        motion = new Vector();
        effectList = new LinkedList<>();
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
    public void setInterval(long interval) {
        this.interval = interval;
    }


    @Override
    public void run() {

        long startTime = System.currentTimeMillis();

        while (!Thread.interrupted() && (System.currentTimeMillis() < startTime + interval * 1000)) {
            try {
                next();
                Thread.sleep(interval);
            } catch (InterruptedException ignore) {
                Thread.currentThread().interrupt();
            }
        }

    }
}
