package Practical_week_2.Figure;

public class Vector2D extends  Vector {


    public Vector2D(int x1, int y1, int x2, int y2) {
        super(x1, y1, 0, x2, y2, 0);
    }

    @Override
    public double getLength() {
        return Math.sqrt(Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2));
    }

    @Override
    public String getVectorCords() {
        return (x2 - x1) + " " + (y2 - y1);
    }

    public double getScalarProduct(Vector2D vector) {
        String cords = getVectorCords();
        String cords2 = vector.getVectorCords();

        return Integer.parseInt(cords.split(" ") [0]) * Integer.parseInt(cords2.split(" ") [0]) +
                Integer.parseInt(cords2.split(" ") [1]) * Integer.parseInt(cords.split(" ") [1]);
    }

   /* public double getScalarProduct(Vector2D vector) {
        return (x2 - x1) * (vector.x2 - vector.x1) + (y2 - y1) * (vector.y2 - vector.y1);
    }*/

/*    public double getAngle(Vector2D vector) {
        double length1 = getLength();
        double length2 = vector.getLength();

        if (length1 == 0 || length2 == 0) {
            return -2.0;
        }

        double scalarProduct = getScalarProduct(vector);
        return Math.acos(scalarProduct / (length1 * length2));
    }*/

    public double getAngle(Vector2D vector) {
        if (getLength() != 0 && vector.getLength() != 0) {
            return getScalarProduct(vector) / this.getLength() * vector.getLength();
        } else return -2.0;
    }

}

