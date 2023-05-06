package com.pensumeditor.main;

import com.pensumeditor.data.Pensum;
import com.pensumeditor.data.PositionSubject;
import com.pensumeditor.data.Subject;
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

    public void showPensumMenuInterface(Pensum pensum) {
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
            case 1 -> getSubjectInfo(pensum);
            case 2 -> addSubject(pensum);
            case 3 -> deleteSubject();
            case 4 -> replaceSubject();
            case 5 -> addSemester();
            default -> {
            }
        }
    }

    public void displayPensumInterface(String name, int subjectsNumber, int semestersNumber, List<PositionSubject> subjectArray) {
        Pensum pensum = new Pensum(name, subjectsNumber, semestersNumber, subjectArray);
        showPensumMenuInterface(pensum);

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
            case 1 -> addSubject(pensum);
            case 2 -> deleteSubject();
            case 3 -> replaceSubject();
            case 4 -> addSemester();
            default -> {
            }
        }
    }

    public void getSubjectInfo(Pensum pensum) {
        System.out.println
                (
                        """
                        Ingresa el código de la materia que deseas consultar su información: 
                        """
                );
        int code = sc.nextInt();
        int index = pensum.getSubjectArray().search(new PositionSubject(code));
        if (index == -1) {
            System.out.println
                    (
                            """
                            El código ingresado no es valido
                            """
                    );
        } else {
            System.out.println(pensum.getSubjectArray().get(index).getSubject());
        }
    }

    public void addSubject(Pensum pensum) {
        System.out.println
                (
                        """
                        Ingresa el semestre en el que quieres añadir la materia
                        """
                );
        int semestre = sc.nextInt() - 1;

        System.out.println
                (
                        """
                        Ingresa el campo en el semestre donde quieres añadir la materia
                        """
                );
        int campo = sc.nextInt() - 1;

        if ((semestre < 0 || semestre > pensum.getSemestersNumber()) && (campo < 0 || campo > pensum.getSubjectsNumber())){
            throw new IndexOutOfBoundsException("Index Out Of Bounds Exception");
        }

        System.out.println
                (
                        """
                        Ingresa un codigo de materia
                        """
                );
        int code = sc.nextInt();

        System.out.println
                (
                        """
                        Ingresa el nombre de la materia
                        """
                );
        String subjectName = sc.nextLine();

        System.out.println
                (
                        """
                        Ingresa los creditos de la materia
                        """
                );
        int credits = sc.nextInt();

        Subject addSub = new Subject(code, subjectName, credits);

        if (pensum.getPensumMatrix().get(semestre)[campo] == null) {
            pensum.getSubjectArray().add(new PositionSubject(semestre, campo, addSub));
            pensum.updateMatrixByPosition();
        }
        else{
            System.out.println("El campo elegido ya esta ocupado.");
        }
        showPensumMenuInterface(pensum);
    }

    public void deleteSubject() {

    }

    public void replaceSubject() {

    }

    public void addSemester() {

    }

}
