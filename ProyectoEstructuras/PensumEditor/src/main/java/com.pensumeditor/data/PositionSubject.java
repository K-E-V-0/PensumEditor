package com.pensumeditor.data;

public class PositionSubject {
    private int row;
    private int column;
    private int subjectCode;
    private Subject subject;

    public PositionSubject(int column, int row, Subject subject) {
        this.column = column;
        this.row = row;
        this.subjectCode = subject.getCode();
        this.subject = subject;
    }

    public PositionSubject(int code) {
        this.subjectCode = code;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }
}
