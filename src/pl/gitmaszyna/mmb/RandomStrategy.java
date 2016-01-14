package pl.gitmaszyna.mmb;

import java.awt.Point;


public class RandomStrategy implements MovementStrategy {
    private int maxX;
    private int maxY;
    private Point point;


    public RandomStrategy(int maxX, int maxY) {
        this.maxX = maxX;
        this.maxY = maxY;
    }

    public RandomStrategy() {
        this(300, 200);
    }

    @Override
    public String getName() {
        return "Random movement";
    }

    @Override
    public boolean makeMove() {
        int x = (int)(Math.random() * this.maxX);
        int y = (int)(Math.random() * this.maxY);
        this.point = new Point(x, y);

        return true;
    }

    @Override
    public Point getLocation() {
        return this.point;
    }
}
