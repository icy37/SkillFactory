package Lesson_5;

public class Squirrel {
    private int[] nuts;

    public Squirrel(int[] nuts) {
        this.nuts = nuts;
    }

    public int getNuts(int index) {
        return nuts[index];
    }

    ///*
    public int maxNuts() {
        int maxOfNuts = 0, len = nuts.length;
        if (len > 1) {
            int i = 0;
            //Проверяем, можно ли подобрать орешки с первого
            if (nuts[i] > 0)
                maxOfNuts += nuts[i];
            //Проверяем, можно ли подобрать орешки со второго до последнего-предпоследнего
            for (; i < len - 2; i++) {
                if (nuts[i + 1] < 0 && nuts[i + 1] <= nuts[i + 2])
                    i++;//перепрыгиваем через пень
                maxOfNuts += nuts[i + 1];
                if (maxOfNuts < 0)
                    maxOfNuts = 0;
            }
            // Смотрим, стоит ли прыгать на последний пень
            if (i == len - 2 && nuts[i + 1] > 0) {
                maxOfNuts += nuts[i + 1];
            }
            return maxOfNuts;
        } else if (nuts[0] >= 0)
            return nuts[0];
        else
            return 0;
    }
    //*/
}