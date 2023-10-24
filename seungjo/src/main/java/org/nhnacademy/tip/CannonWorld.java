package org.nhnacademy.tip;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;


public class CannonWorld extends BoundedWorld {
    Bounds target;
    Cannon cannon;
    List<Vector> effectList;
    List<Bounds> hazardList;

    public CannonWorld(int width, int height) {
        super(width, height);
        target = null;
        effectList = new LinkedList<>();
        hazardList = new LinkedList<>();

        cannon = new Cannon(new Point(50, 50), 40, 40, Color.GREEN);
        cannon.setAngle(40);
        add(cannon);
    }

    public void setMagnitude(int magnitude) {
        cannon.setMagnitude(magnitude);
    }

    public int getMagnitude() {
        return cannon.getMagnitude();
    }

    public void setAngle(int angle) {
        cannon.setAngle(angle);
    }

    public int getAngle() {
        return cannon.getAngle();
    }

    public void fire() {
        BoundedBall ball1 = new BoundedBall(new Point(cannon.getMaxX() + 20, cannon.getMaxY() + 20), 20, Color.BLUE);
        ball1.setMotion(new Vector(cannon.getMagnitude(), cannon.getAngle()));
        add(ball1);
    }

    public void clear() {
//        for (Bounds bounds : boundsList) {
//            if (bounds instanceof Movable) {
//                cannon.setColor(Color.GREEN);
//                boundsList.remove(bounds);
//            }
//        }


        for (int i = 0; i < boundsList.size(); i++) {
            if (boundsList.get(i) instanceof Movable) {
                cannon.setColor(Color.GREEN);
                boundsList.remove(i);
                break;
            }
        }
    }

    public void setTarget(Bounds target) {
        this.target = target;
        add(target);
    }

    public void addHazard(Bounds hazard) {
        hazardList.add(hazard);
        add(hazard);
    }

    public void addEffect(Vector effect) {
        effectList.add(effect);

        for (Bounds bounds : boundsList) {
            if (bounds instanceof Movable) {
                ((Movable) bounds).addEffect(effect);
            }
        }
    }

    @Override
    public void add(Bounds bounds) {
        super.add(bounds);

        if (bounds instanceof Bounded) {
            ((Bounded) bounds).addCollisionEventListener(event -> {
                if (target != null) {
                    if (event.getDestination() == target) {
                        if (target instanceof Drawable) {
                            ((Drawable) target).setColor(Color.RED);
                            ((Movable) bounds).stop();
                        }
                    } else if (event.getDestination() == cannon) {
                        if (target instanceof Drawable) {
                            ((Drawable) cannon).setColor(Color.RED);
                            ((Movable) bounds).stop();
                        }
                    } else if (hazardList.contains(event.getDestination())) {
                        ((Movable) bounds).stop();
                    }
                }
            });
        }

        for (Vector effect : effectList) {
            if (bounds instanceof Movable) {
                ((Movable) bounds).addEffect(effect);
            }
        }
    }
}
