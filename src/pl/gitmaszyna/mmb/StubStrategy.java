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
public class StubStrategy implements MovementStrategy {

    @Override
    public boolean makeMove() {
        return true;
    }

    @Override
    public Point getLocation() {
        return new Point(100, 100);
    }

    @Override
    public String getName() {
        return "Movement strategy stub";
    }
}
