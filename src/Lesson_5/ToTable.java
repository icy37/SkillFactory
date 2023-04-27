package Lesson_5;

public class ToTable {
    private int[] data;
    private int x;
    private int y;

    public ToTable(int[] data, int x, int y) {
        this.data = data;
        this.x = x;
        this.y = y;
    }

    public int[][] resize() {
        int[][] result = new int[x][y];
        int dataIndex = 0;

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                result[i][j] = data[dataIndex];
                dataIndex++;
            }
        }

        return result;
    }
}
