package Lesson_4;

public class Tank {
    private int x, y, fuel;

    public Tank() {
        this(0, 0, 100);
    }

    public Tank(int x, int y) {
        this(x, y, 100);
    }

    public Tank(int x, int y, int fuel) {
        this.x = x;
        this.y = y;
        this.fuel = fuel;
    }

    public void printPosition() {
        System.out.println("The Tank is at " + x + ", " + y + " now.");
    }

    public void goForward(int distance) {

        if (fuel >= distance) {
            fuel -= distance;
            x += distance;
        } else {
            x += fuel;
            fuel = 0;
        }
    }



    public void goBackward(int distance) {
        if (fuel >= Math.abs(distance)) {
            fuel -= Math.abs(distance);
            x -= distance;
        } else {
            x -= fuel * Math.abs(distance) / distance;
            fuel = 0;
        }
    }


}
