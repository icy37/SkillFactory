package Practical_week_2.Figure;

public class Parallelogram extends  Quadrangle implements  Figure{

    double alpha;
    double beta;


    public Parallelogram(int a, int b, double alpha, double beta, String color) {
        super(a, b, color);
        this.alpha = alpha;
        this.beta = beta;
    }

    @Override
    public double area() {
        return Math.max(a,b) * getHeight();
    }

    @Override
    public double perimeter() {
        return 2 * (a + b);
    }

    @Override
    public double getLargeDiagonal() {
        double d1 = Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2) + 2 * a * b * Math.cos(Math.toRadians(alpha)));
        double d2 = Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2) + 2 * a * b * Math.cos(Math.toRadians(beta)));
        return Math.max(d1,d2);
    }

    @Override
    public double getHeight() {
        return Math.min(a * Math.sin(Math.toRadians(alpha)), b * Math.sin(Math.toRadians(beta)));
    }

    @Override
    public String getColor() {
        return color;
    }
}
