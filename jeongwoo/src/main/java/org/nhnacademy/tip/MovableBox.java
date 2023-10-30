package org.nhnacademy.tip;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

public class MovableBox extends Box implements Movable {
    boolean stop;
    final Vector motion;
    final List<Vector> effectList;

    public MovableBox(Point location, int width, int height, Color color) {
        super(location, width, height, color);

        stop = false;
        motion = new Vector();
        effectList = new LinkedList<>();
    }

    public void start() {
        stop = false;
    }

    public void stop() {
        stop = true;
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
        if (!stop) {
            location.move(motion.getDisplacement());
        }
    }


    @Override
    public void run() {

    }
}
