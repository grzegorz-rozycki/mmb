/**
 * 17 maj 2015
 * Author Grzegorz Różycki <grozycki@op.pl>
 */
package pl.gitmaszyna.mmb;

import java.awt.Point;


/**
 * @author grzechu
 *
 */
public class CircleStrategy implements MovementStrategy {

    private static final double ROTATION_ANGLE = Math.PI / 180;
    private static final int CENTER_OFFSET = 10;
    private int radius;
    private Point currentLocation;


    public CircleStrategy() {
        radius = 100;
        currentLocation = new Point(0 + CircleStrategy.CENTER_OFFSET,
                radius + CircleStrategy.CENTER_OFFSET);
    }

    @Override
    public String getName() {
        return "Circle movement strategy";
    }

    @Override
    public boolean makeMove() {
        final double alfa = CircleStrategy.ROTATION_ANGLE;
        final int cx = currentLocation.x - radius;
        final int cy = currentLocation.y - radius;
        final int nx = (int) (cx * Math.cos(alfa) - cy * Math.sin(alfa) + radius);
        final int ny = (int) (cx * Math.sin(alfa) + cy * Math.cos(alfa) + radius);
        currentLocation = new Point(nx, ny);

        return true;
    }

    @Override
    public Point getLocation() {
        return currentLocation;
    }

}
