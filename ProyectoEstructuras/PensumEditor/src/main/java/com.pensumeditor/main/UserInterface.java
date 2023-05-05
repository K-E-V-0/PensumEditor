package com.pensumeditor.main;

import com.pensumeditor.data.Pensum;
import com.pensumeditor.data.Subject;
import com.pensumeditor.implementations.List;
import com.pensumeditor.pensums.Ing_Sistemas;
import com.pensumeditor.pensums.RandomPensum;

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
        RandomPensum randomPensum = new RandomPensum(semestres*materias);
        List<Subject> randomSubjectArray = (List<Subject>) randomPensum.getRandomSubjectArray();

        displayPensumInterface("Random Pensum", materias, semestres, randomSubjectArray);
    }

    public void loadComputerEngeenering() {
        Ing_Sistemas ingSistemas = new Ing_Sistemas();
        displayPensumInterface("Ingenieria de sistemas", 6, 10, ingSistemas.Generate_Ing_Sistemas_Array());
    }

    public void displayPensumInterface(String name, int subjectsNumber, int semestersNumber, List<Subject> subjectArray) {
        Pensum pensum = new Pensum(name, subjectsNumber, semestersNumber, subjectArray);
        pensum.constructPensum();
        System.out.println
            (
                """
                Opciones:
                1) Consultar información de materia por código.
                2) Agregar materia - Kev
                3) Eliminar materia - Javier
                4) Reemplazar materia
                5) Agregar semestre - Kevin 
                6) Eliminar semestre
                """
            );
        /*System.out.println
            (
                """
                Cada casilla representa el código de una materia.
                
                Si quieres realizar alguna operación sobre una de las casillas digita a continuación el código de la materia que deseas modificar, 
                en caso contario, para volver al menú inicial dígita la letra s:
                """
            );
        String optionS = sc.next();
        if (optionS.equals("s")) {
            startMenu();
        } else {
            try {
                int option = Integer.parseInt(optionS);
                int index = subjectArray.search(new Subject(option));
                if (index != -1) {
                    System.out.println("La materia seleccionada fue: ");
                } else {
                    System.out.println("No se encontró ese código de materia en el pensum");
                }
            } catch(NumberFormatException e){
                System.out.println("Entrada invalida, es necesario ingresar el código de una materia o la letra 's'");
            }
        }*/

    }

}
