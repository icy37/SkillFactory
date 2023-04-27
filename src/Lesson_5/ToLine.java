package Lesson_5;

public class ToLine {
    private int[][] data;

    public ToLine(int[][] data) {
        this.data = data;
    }

    public int[] resize() {
        int totalLength = 0;
        for (int[] row : data) {
            totalLength += row.length;
        }

        int[] result = new int[totalLength];
        int index = 0;
        for (int[] row : data) {
            for (int value : row) {
                result[index] = value;
                index++;
            }
        }

        return result;
    }
}