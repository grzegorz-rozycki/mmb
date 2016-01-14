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
    private int angle = 0;
    private int radius = 100;
    private Point currentLocation = null;

    @Override
    public String getName() {
        return "Circle movement";
    }

    @Override
    public boolean makeMove() {
        double radians = Math.toRadians(this.angle);
        int x = (int)(this.radius * Math.cos(radians) + this.radius);
        int y = (int)(this.radius * Math.sin(radians) + this.radius);
        this.currentLocation = new Point(x, y);
        this.angle = (++this.angle % 360);

        return true;
    }

    @Override
    public Point getLocation() {
        return this.currentLocation;
    }
}
