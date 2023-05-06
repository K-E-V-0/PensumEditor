package com.pensumeditor.data;

import com.pensumeditor.implementations.List;
import com.pensumeditor.implementations.CircularArrayList;

import java.util.Random;

public class Pensum {
    private String pensumName;
    private List<Integer[]> pensumMatrix = new CircularArrayList<>();
    private List<PositionSubject> subjectArray;
    private List<Integer> codeArray;
    private int subjectsNumber;
    private int semestersNumber;

    public Pensum(String pensumName, int subjectsNumber, int semestersNumber, List<PositionSubject> subjectArray) {
        this.pensumName = pensumName;
        this.subjectsNumber = subjectsNumber;    // Filas
        this.semestersNumber = semestersNumber;    // Columnas
        this.subjectArray = subjectArray;

        for (int i = 0; i < semestersNumber; i ++) {
            pensumMatrix.add(new Integer[subjectsNumber]);
        }

        for (int i = 0; i < subjectArray.getSize(); i ++) {
            pensumMatrix.get(subjectArray.get(i).getColumn())[subjectArray.get(i).getRow()] = subjectArray.get(i).getSubject().getCode();
        }
    }

    // This constructor only works with randomized pensum generated
    public Pensum(String pensumName, int subjectsNumber, int semestersNumber) {
        this.pensumName = pensumName;
        this.subjectsNumber = subjectsNumber;    // Filas
        this.semestersNumber = semestersNumber;    // Columnas
        this.codeArray = new CircularArrayList<>();

        Random random = new Random();

        for (int i = 0; i < semestersNumber; i ++) {
            pensumMatrix.add(new Integer[subjectsNumber]);
        }

        for (int i = 0; i < subjectsNumber; i ++) {
            for (int j = 0; j < semestersNumber; j ++) {
                int randomCode = random.nextInt(9000000) + 1000000;
                pensumMatrix.get(j)[i] = randomCode;
                codeArray.add(randomCode);
            }
        }
    }

    public void updateMatrixByCode(List<Integer> codeArray) {
        int index = 0;
        for (int i = 0; i < subjectsNumber; i ++) {
            for (int j = 0; j < semestersNumber; j ++) {
                pensumMatrix.get(j)[i] = codeArray.get(index);
                index ++;
            }
        }
    }

    public void updateMatrixByPosition() {
        for (int i = 0; i < semestersNumber; i ++) {
            pensumMatrix.set(new Integer[subjectsNumber], i);
        }

        for (int i = 0; i < subjectArray.getSize(); i ++) {
            pensumMatrix.get(subjectArray.get(i).getColumn())[subjectArray.get(i).getRow()] = subjectArray.get(i).getSubject().getCode();
        }
    }

    public String getPensumName() {
        return pensumName;
    }

    public void setPensumName(String pensumName) {
        this.pensumName = pensumName;
    }

    public int getSubjectsNumber() {
        return subjectsNumber;
    }

    public void setSubjectsNumber(int subjectsNumber) {
        this.subjectsNumber = subjectsNumber;
    }

    public int getSemestersNumber() {
        return semestersNumber;
    }

    public void setSemestersNumber(int semestersNumber) {
        this.semestersNumber = semestersNumber;
    }

    public List<Integer[]> getPensumMatrix() {
        return pensumMatrix;
    }

    public void setPensumMatrix(List<Integer[]> pensumMatrix) {
        this.pensumMatrix = pensumMatrix;
    }

    public List<PositionSubject> getSubjectArray() {
        return subjectArray;
    }

    public void setSubjectArray(List<PositionSubject> subjectArray) {
        this.subjectArray = subjectArray;
    }

    public List<Integer> getCodeArray() {
        return codeArray;
    }

    public void setCodeArray(List<Integer> codeArray) {
        this.codeArray = codeArray;
    }

    public void constructPensum() {
        System.out.println("------------------------" + pensumName + "------------------------");
        for (int j = 0; j < subjectsNumber; j++) {
            for (int i = 0; i < semestersNumber; i++) {
                Integer code = pensumMatrix.get(i)[j];
                if (code == null) {
                    System.out.print("-|||||||-");
                } else {
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
