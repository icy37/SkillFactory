package Lesson_5;

public class LimitingRectangle {

    private int xMin, xMax, yMin, yMax;
   private int[][] points;

    public LimitingRectangle(int[][] ints) {
        this.points = ints;
        xMin = points[0][0];
        xMax = points[0][0];
        yMin = points[0][1];
        yMax = points[0][1];
        for (int i = 1; i < points.length; i++) {
            int x = points[i][0];
            int y = points[i][1];
            if (x < xMin) {
                xMin = x;
            } else if (x > xMax) {
                xMax = x;
            }
            if (y < yMin) {
                yMin = y;
            } else if (y > yMax) {
                yMax = y;
            }
        }
    }

    public int getWidth() {
        return xMax - xMin;
    }

    public int getHeight() {
        return yMax - yMin;
    }

    public String getBorders() {
        return yMin + ", " + yMax + ", " + xMin + ", " + xMax;
    }
}
