package Lesson_5;

public class Separator {
    private int [] array;



    public Separator(int[] array) {
        this.array = array;
    }

    public int[] odd() {
        int oddCount = 0;
        for (int num : array) {
            if (num % 2 == 1) {
                oddCount++;
            }
        }
        int[] oddArray = new int[oddCount];
        int oddIndex = 0;
        for (int num : array) {
            if (num % 2 == 1) {
                oddArray[oddIndex] = num;
                oddIndex++;
            }
        }
        return oddArray;
    }

    public int[] even() {
        int evenCount = 0;
        for (int num : array) {
            if (num % 2 == 0) {
                evenCount++;
            }
        }
        int[] evenArray = new int[evenCount];
        int evenIndex = 0;
        for (int num : array) {
            if (num % 2 == 0) {
                evenArray[evenIndex] = num;
                evenIndex++;
            }
        }
        return evenArray;
    }



}
