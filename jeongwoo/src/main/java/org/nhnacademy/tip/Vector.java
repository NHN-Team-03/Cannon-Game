package org.nhnacademy.tip;

public class Vector {
    int magnitude;
    int angle;
    Point displacement;

    int gravity;

    int wind;

    public Vector() {
        this(0, 0);
    }

    public Vector(int magnitude, int angle) {
        this.gravity = 1;
        this.wind = 1;
        this.magnitude = magnitude;
        this.angle = angle;
        this.displacement = new Point(0, 0);
        updateDisplacementVector();
    }

    public Vector(int magnitude, int angle, int gravity, int wind) {
        this(magnitude, angle);
        this.wind = wind;
        this.gravity = gravity;
        updateDisplacementVector();
    }

    public Vector(Point displacement) {
        this.displacement = displacement.clone();

        updateDirectionVector();
    }

    public void setGravity(int gravity) {
        this.gravity = gravity;
    }

    public int getMagnitude() {
        return magnitude;
    }

    public int getAngle() {
        return angle;
    }

    public Point getDisplacement() {
        return displacement;
    }

    public int getGravity() {
        return gravity;
    }

    public Vector set(Vector other) {
        gravity = other.getGravity();
        magnitude = other.getMagnitude();
        angle = other.getAngle();
        displacement.moveTo(other.displacement);
        return this;
    }

    public Vector add(Vector other) {
        displacement.move(other.getDisplacement());

        updateDirectionVector();

        return this;
    }

    public Vector multiply(int scale) {
        magnitude *= scale;

        updateDisplacementVector();

        return this;
    }

    public Vector rotate(int angle) {
        this.angle += angle;

        updateDisplacementVector();

        return this;
    }

    public Vector flipX() {
        displacement.moveTo(-displacement.getX(), displacement.getY());
        wind = -wind;
        updateDirectionVector();

        return this;
    }

    public Vector flipY() {
        displacement.moveTo(displacement.getX(), - displacement.getY());

        updateDirectionVector();

        return this;
    }

    protected void updateDisplacementVector() {
        displacement.moveTo(
                (int) (magnitude * Math.cos(Math.toRadians(angle)) + wind),
                (int) (magnitude * Math.sin(Math.toRadians(angle)) - gravity)
        );

    }

    protected void updateDirectionVector() {
        magnitude = (int) Math.sqrt(Math.pow(displacement.getX(), 2) + Math.pow(displacement.getY(), 2));
        if (magnitude != 0) {
            angle = (int) Math.toDegrees(Math.acos((double) displacement.getX() / magnitude));
            if (displacement.getY() < 0) {
                angle = -angle;
            }
        } else {
            angle = 0;
        }
    }
}
