package com.pensumeditor.data;

import com.pensumeditor.implementations.List;
import com.pensumeditor.implementations.CircularArrayList;

public class Pensum {
    private String pensumName;
    private List<Subject[]> pensumMatrix;
    private int subjectsNumber;
    private int semestersNumber;

    public Pensum(String pensumName, List<Subject[]> pensumMatrix) {
        this.pensumName = pensumName;
        this.subjectsNumber = pensumMatrix.get(0).length;
        this.semestersNumber = pensumMatrix.getSize();
        this.pensumMatrix = pensumMatrix;
    }
    public Pensum(String pensumName, int subjectsNumber, int semestersNumber, List<Subject> subjectArray) {
        this.pensumName = pensumName;
        this.subjectsNumber = subjectsNumber;  //filas
        this.semestersNumber = semestersNumber;  //columnas
        updateMatrix(subjectArray);
    }

    public void updateMatrix(List<Subject> subjectArray) {
        pensumMatrix = new CircularArrayList<>();
        for (int i = 0; i < semestersNumber; i++) {
            pensumMatrix.add(new Subject[subjectsNumber]);
        }
        int indexCount = 0;
        for (int i = 0; i < semestersNumber; i++) {
            for (int j = 0; j < subjectsNumber; j++) {
                pensumMatrix.get(i)[j] = subjectArray.get(indexCount);
                indexCount ++;

            }
        }
    }

    public void constructPensum() {
        System.out.println("------------" + pensumName + "------------");
        for (int j = 0; j < subjectsNumber; j++) {
            for (int i = 0; i < semestersNumber; i++) {
                Subject subject = pensumMatrix.get(i)[j];
                if (subject == null || subject.getCode() == 0) {
                    System.out.print("-|||||||-");
                } else {
                    int code = subject.getCode();
                    if (code == 1) {
                        System.out.print("-0000001-");
                    } else {
                        System.out.print("-" + code + "-");
                    }
                }
            }
            System.out.println();
        }
        System.out.println("-----------------------------------------");
    }



}
