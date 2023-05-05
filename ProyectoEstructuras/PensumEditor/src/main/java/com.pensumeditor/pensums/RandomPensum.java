package com.pensumeditor.pensums;

import com.pensumeditor.data.Subject;
import com.pensumeditor.implementations.CircularArrayList;
import com.pensumeditor.implementations.List;

import java.util.Random;

public class RandomPensum {
    private List<Subject> randomSubjectArray = new CircularArrayList<>();
    public RandomPensum(int lenght) {
        Random random = new Random();
        for (int i = 0; i < lenght; i ++) {
            randomSubjectArray.add(new Subject(random.nextInt(9000000) + 1000000));
        }
    }
    public List<Subject> getRandomSubjectArray() {
        return randomSubjectArray;
    }
}
