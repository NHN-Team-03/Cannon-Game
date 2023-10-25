package org.nhnacademy.tip;

public class Vector {
    int magnitude;
    int angle;
    int gravity;
    int wind;
    Point displacement;

    public Vector() {
        this(0, 0, 0, 0);
    }

    public Vector(int magnitude, int angle, int gravity, int wind) {
        this.magnitude = magnitude;
        this.angle = angle;
        this.gravity = gravity;
        this.wind = wind;
        this.displacement = new Point(0, 0);
        updateDisplacementVector();
    }

    public Vector(Point displacement) {
        this.displacement = displacement.clone();

        updateDirectionVector();
    }

    public int getMagnitude() {
        return magnitude;
    }

    public int getAngle() {
        return angle;
    }

    public int getGravity() {
        return gravity;
    }

    public int getWind() {
        return wind;
    }


    public Point getDisplacement() {
        return displacement;
    }

    public Vector set(Vector other) {
        magnitude = other.getMagnitude();
        angle = other.getAngle();
        gravity = other.getGravity();
        wind = other.getWind();
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

        updateDirectionVector();

        return this;
    }

    public Vector flipY() {
        displacement.moveTo(displacement.getX(), -displacement.getY());

        updateDirectionVector();

        return this;
    }

    protected void updateDisplacementVector() {
        displacement.moveTo(
                (int) (magnitude * Math.cos(Math.toRadians(angle))) + (wind * 3),
                (int) (magnitude * Math.sin(Math.toRadians(angle))) - (gravity * 4)
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
