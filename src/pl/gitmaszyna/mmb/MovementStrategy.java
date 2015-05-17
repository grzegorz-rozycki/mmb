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
public interface MovementStrategy {

    public String getName();

    public boolean makeMove();

    public Point getLocation();
}
