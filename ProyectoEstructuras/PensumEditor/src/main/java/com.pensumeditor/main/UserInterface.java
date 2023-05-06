package com.pensumeditor.main;

import com.pensumeditor.data.Pensum;
import com.pensumeditor.data.PositionSubject;
import com.pensumeditor.implementations.List;
import com.pensumeditor.pensums.Ing_Sistemas;

import java.util.Scanner;

public class UserInterface {

    Scanner sc = new Scanner(System.in);
    public void startMenu() {
        System.out.println
                (
                        """
                        Bienvenido a Pensum Editor, a continuación se presentan las opciones disponibles:
                        1) Crear Pensum
                        2) Cargar Pensum Existente
                        3) Generar Pensum Aleatorio
                        4) Salir del programa
                        Digita el número de la opción que elegiste a continuación:
                        """
                );

        int option = sc.nextInt();
        switch (option) {
            case 1 -> createPensum();
            case 2 -> loadPensum();
            case 3 -> generatePensum();
            default -> {
                if (option != 4) {
                    System.out.println("Opción invalida");
                }
            }
        }
    }

    public void createPensum() {
        System.out.println("Creando...");
    }

    public void loadPensum() {
        System.out.println
                (
                        """
                        Los pensum disponibles actualmente son:
                        1) Ingenieria de sistemas y computacion
                        Digita el número de la opción que elegiste a continuación:
                        """
                );
        switch (sc.nextInt()) {
            case 1 -> loadComputerEngeenering();
            default -> {
            }
        }
    }

    public void generatePensum() {
        System.out.println
                (
                        """
                        Ingresa el número de semestres que deseas que tenga el pensum:
                        """
                );
        int semestres = sc.nextInt();
        System.out.println
                (
                        """
                        Ingresa el número de materias por semestre que deseas que tenga el pensum:
                        """
                );
        int materias = sc.nextInt();

        displayRandomPensumInterface(materias, semestres);

    }

    public void loadComputerEngeenering() {
        Ing_Sistemas ingSistemas = new Ing_Sistemas();
        displayPensumInterface("Ingenieria de sistemas", 6, 10, ingSistemas.Generate_Ing_Sistemas_Matrix());
    }

    public void displayPensumInterface(String name, int subjectsNumber, int semestersNumber, List<PositionSubject> subjectArray) {
        Pensum pensum = new Pensum(name, subjectsNumber, semestersNumber, subjectArray);
        pensum.constructPensum();
        System.out.println
                (
                        """
                        Opciones:
                        1) Consultar información de materia
                        2) Agregar materia - Kev
                        3) Eliminar materia - Javier
                        4) Reemplazar materia
                        5) Agregar semestre - Kevin
                        6) Volver al menú principal
                        """
                );
        switch (sc.nextInt()) {
            case 1 -> getSubjectInfo(Integer.valueOf(sc.next()));
            case 2 -> addSubject();
            case 3 -> deleteSubject();
            case 4 -> replaceSubject();
            case 5 -> addSemester();
            default -> {
            }
        }

    }

    public void displayRandomPensumInterface(int subjectsNumber, int semestersNumber) {
        Pensum pensum = new Pensum("Random Pensum", subjectsNumber, semestersNumber);
        pensum.constructPensum();
        System.out.println
                (
                        """
                        Opciones:
                        1) Agregar materia - Kev
                        2) Eliminar materia - Javier
                        3) Reemplazar materia
                        4) Agregar semestre - Kevin 
                        5) Volver al menú principal
                        """
                );
        switch (sc.nextInt()) {
            case 1 -> addSubject();
            case 2 -> deleteSubject();
            case 3 -> replaceSubject();
            case 4 -> addSemester();
            default -> {
            }
        }
    }

    public void getSubjectInfo(int code) {

    }

    public void addSubject() {

    }

    public void deleteSubject() {

    }

    public void replaceSubject() {

    }

    public void addSemester() {

    }

}
