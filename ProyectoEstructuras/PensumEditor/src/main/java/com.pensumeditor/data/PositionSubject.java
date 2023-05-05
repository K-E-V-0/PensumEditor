package com.pensumeditor.data;

public class PositionSubject {
    private int fila;
    private int columna;
    private Subject materia;

    public PositionSubject(int fila, int columna, Subject materia) {
        this.fila = fila;
        this.columna = columna;
        this.materia = materia;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    public Subject getMateria() {
        return materia;
    }

    public void setMateria(Subject materia) {
        this.materia = materia;
    }
}
