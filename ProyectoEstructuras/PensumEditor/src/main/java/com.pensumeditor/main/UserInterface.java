package com.pensumeditor.main;

import com.pensumeditor.data.Pensum;
import com.pensumeditor.data.PositionSubject;
import com.pensumeditor.data.Subject;
import com.pensumeditor.implementations.*;
import com.pensumeditor.pensums.Ing_Sistemas;

import java.util.Random;
import java.util.Scanner;

public class UserInterface {

    Scanner sc = new Scanner(System.in).useDelimiter("\n");
    public void startMenu() {
        System.out.println
                (
                        """
                        Bienvenido a Pensum Editor, a continuación se presentan las opciones disponibles:
                        1) Crear Pensum
                        2) Cargar Pensum Existente
                        3) Pruebas de rendimiento
                        4) Salir del programa
                        Digita el número de la opción que elegiste a continuación:
                        """
                );

        int option = sc.nextInt();
        switch (option) {
            case 1 -> createPensum();
            case 2 -> loadPensum();
            case 3 -> tests();
            default -> {
                if (option != 4) {
                    System.out.println("Opción invalida");
                    startMenu();
                }
            }
        }
    }

    public void createPensum() {
        System.out.println
                (
                        """
                        Bienvenido al menú de creación de pensums
                        Por favor ingresa el nombre del pensum:
                        """
                );
        String nombre = sc.next();
        System.out.println("Por favor ingresa el número de semestres que deseas que tenga tu pensum:");
        int semestres = sc.nextInt();
        System.out.println("Por favor ingresa el número máximo de materias que se verán por semestre: ");
        int materias = sc.nextInt();
        List<PositionSubject> positionSubjectList = new CircularArrayList<>();

        displayPensumInterface(nombre, materias, semestres, positionSubjectList);
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
        /*System.out.println
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

        displayRandomPensumInterface(materias, semestres);*/

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
                        2) Agregar materia
                        3) Eliminar materia
                        4) Reemplazar materia
                        5) Agregar semestre
                        6) Eliminar semestre
                        7) Volver al menú principal
                        """
                );
        switch (sc.nextInt()) {
            case 1 -> getSubjectInfoOption(pensum);
            case 2 -> addSubjectOption(pensum);
            case 3 -> deleteSubjectOption(pensum);
            case 4 -> replaceSubjectOption(pensum);
            case 5 -> addSemesterOption(pensum);
            case 6 -> removeSemesterOption(pensum);
            case 7 -> startMenu();
            default -> {
                System.out.println("Opción invalida, vuelvelo a intentar: ");
            }
        }
    }

    public void displayPensumInterface(String name, int subjectsNumber, int semestersNumber, List<PositionSubject> subjectArray) {
        Pensum pensum = new Pensum(name, subjectsNumber, semestersNumber, subjectArray);
        showPensumMenuInterface(pensum);

    }

    public void displayRandomPensumInterface(int subjectsNumber, int semestersNumber) {
        /*Pensum pensum = new Pensum("Random Pensum", subjectsNumber, semestersNumber);
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
            case 2 -> deleteSubject(pensum);
            case 3 -> replaceSubject();
            case 4 -> addSemester(pensum);
            default -> {
            }
        }*/
    }

    public void getSubjectInfoOption(Pensum pensum) {
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
        showPensumMenuInterface(pensum);
    }

    public void addSubjectOption(Pensum pensum) {
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

        if (pensum.getPensumMatrix().get(semestre)[campo] == null) {

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
            String subjectName = sc.next();

            System.out.println
                    (
                            """
                            Ingresa los creditos de la materia
                            """
                    );
            int credits = sc.nextInt();

            Subject addSub = new Subject(code, subjectName, credits);

            pensum.getSubjectArray().add(new PositionSubject(semestre, campo, addSub));
            pensum.updateMatrixByPosition();
        }
        else{
            System.out.println("El campo elegido ya esta ocupado.");
        }
        showPensumMenuInterface(pensum);
    }

    public void deleteSubjectOption(Pensum pensum) {
        System.out.println
                (
                        """
                        Ingresa un codigo de materia
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
            pensum.getSubjectArray().remove(index);
            pensum.updateMatrixByPosition();
        }
        showPensumMenuInterface(pensum);
    }

    public void replaceSubjectOption(Pensum pensum) {
        System.out.println
                (
                        """
                        Ingresa el semestre en el que quiere modificar la materia
                        """
                );
        int semestre = sc.nextInt() - 1;

        System.out.println
                (
                        """
                        Ingresa el campo en el semestre donde quiere modificar la materia
                        """
                );
        int campo = sc.nextInt() - 1;

        if ((semestre < 0 || semestre > pensum.getSemestersNumber()) && (campo < 0 || campo > pensum.getSubjectsNumber())){
            throw new IndexOutOfBoundsException("Index Out Of Bounds Exception");
        }

        if (pensum.getPensumMatrix().get(semestre)[campo] == null){
            System.out.println
                    (
                            """
                            Este campo esta vacio, ¿Desea añadir una materia en este espacio?
                            
                            1) Si
                            2) No
                            """
                    );
            int option = sc.nextInt();
            if (option == 1){
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
                String subjectName = sc.next();

                System.out.println
                        (
                                """
                                Ingresa los creditos de la materia
                                """
                        );
                int credits = sc.nextInt();

                Subject addSub = new Subject(code, subjectName, credits);

                pensum.getSubjectArray().add(new PositionSubject(semestre, campo, addSub));
                pensum.updateMatrixByPosition();
            }
        }
        else{
            int code = pensum.getPensumMatrix().get(semestre)[campo];
            int index = pensum.getSubjectArray().search(new PositionSubject(code));
            System.out.println("Se reemplazara: " + pensum.getSubjectArray().get(index).getSubject());
            System.out.println
                    (
                            """
                            Ingresa un codigo de materia
                            """
                    );
            int codeSub = sc.nextInt();

            System.out.println
                    (
                            """
                            Ingresa el nombre de la materia
                            """
                    );
            String subjectName = sc.next();

            System.out.println
                    (
                            """
                            Ingresa los creditos de la materia
                            """
                    );
            int credits = sc.nextInt();

            Subject addSub = new Subject(codeSub, subjectName, credits);
            pensum.getSubjectArray().set(new PositionSubject(semestre, campo, addSub), index);
            pensum.updateMatrixByPosition();
        }
        showPensumMenuInterface(pensum);
    }

    public void addSemesterOption(Pensum pensum) {
        System.out.println
                (
                        """
                        ¿En donde quieres insertar el semestre?
                        1) Al inicio de la carrera
                        2) Inmediatamente después de un semestre
                        3) Al final de la carrera
                        """
                );
        switch (sc.nextInt()) {
            case 1:
                pensum.addSemester(0);
                break;
            case 2:
                System.out.println("¿Después de qué semestre deseas que se inserte el semestre nuevo?");
                pensum.addSemester(sc.nextInt());
                break;
            case 3:
                pensum.addSemester();
                break;
            default:
        }

        pensum.updateMatrixByPosition();
        showPensumMenuInterface(pensum);
    }

    public void removeSemesterOption(Pensum pensum) {
        System.out.println
                (
                        """
                        Digita el semestre que deseas eliminar:
                        """
                );
        pensum.removeSemester(sc.nextInt());
        pensum.updateMatrixByPosition();
        showPensumMenuInterface(pensum);
    }

    public void tests() {
        System.out.println
                (
                        """
                        Bienvenido al menu de test
                        Ya que las materias se identifican por código, todos los test se hacen sobre
                        una lista de enteros que representan los códigos de materias (List<Integer>)
                        ¿Qué implementación deseas utilizar?
                        1) ArrayList (Lista dinamica)
                        2) CircularArrayList (Lista dinamica circular)
                        3) LinkedList (Lista enlazada)
                        4) DoubleLinkedList (Lista enlazada doble)
                        5) Volver al menú principal
                        """
                );
        List<Integer> dataStructure = null;
        int option = sc.nextInt();

        if (option == 1) {
            dataStructure = new ArrayList<>();
        } else if (option == 2) {
            dataStructure = new CircularArrayList<>();
        } else if (option == 3) {
            dataStructure = new LinkedList<>();
        } else if (option == 4){
            dataStructure = new DoubleLinkedList<>();
        } else if (option == 5){
            startMenu();
        }

        dataStructure.add(1);
        System.out.println("Por default la lista tiene el dato 1 agregado.");

        System.out.println("Ingresa la cantidad de datos 'n' que quieres trabajar: ");

        methodOptions(dataStructure, sc.nextInt());

    }

    public void methodOptions(List<Integer> dataStructure, int n) {
        Random random = new Random();
        System.out.println
                (
                        """
                        Las operaciones posibles son:
                       
                        1) Agregar n datos aleatorios con pushBack()
                        2) Obtener n datos en indices aleatorios con get()
                        3) Sobreescribir n datos aleatorios en indices aleatorios con set()
                        4) Encontrar n datos aleatorios con search()
                        5) Eliminar n datos con indices aleatorios con remove()
                        
                        9) Volver al menú principal
                        """
                );
        int option = sc.nextInt();
        switch (option) {
            case 1:
                long inicio = System.nanoTime();
                for (int i = 0; i < n; i ++) {
                    dataStructure.pushBack(random.nextInt(255));
                }
                long fin = System.nanoTime();
                long duracion = fin-inicio;
                System.out.println(duracion);
                break;
            case 2:
                inicio = System.nanoTime();
                for (int i = 0; i < n; i ++) {
                    dataStructure.get(random.nextInt(dataStructure.getSize()));
                }
                fin = System.nanoTime();
                duracion = fin-inicio;
                System.out.println(duracion);
                break;
            case 3:
                inicio = System.nanoTime();
                for (int i = 0; i < n; i ++) {
                    dataStructure.set(random.nextInt(255), random.nextInt(dataStructure.getSize()));
                }
                fin = System.nanoTime();
                duracion = fin-inicio;
                System.out.println(duracion);
                break;
            case 4:
                inicio = System.nanoTime();
                for (int i = 0; i < n; i ++) {
                    dataStructure.search(random.nextInt(255));
                }
                fin = System.nanoTime();
                duracion = fin-inicio;
                System.out.println(duracion);
                break;
            case 5:
               inicio = System.nanoTime();
                for (int i = 0; i < n; i ++) {
                    System.out.println(dataStructure.getSize());
                    dataStructure.remove(random.nextInt(dataStructure.getSize()));
                }
                fin = System.nanoTime();
                duracion = fin-inicio;
                System.out.println(duracion);
                break;
            default:
                if (option != 6) {
                    System.out.println("Opción invalida");
                    methodOptions(dataStructure, n);
                }
                startMenu();
        }
        //System.out.println(dataStructure);
        System.out.println("Ingresa la cantidad de datos 'n' que quieres trabajar ahora: ");
        n = sc.nextInt();
        methodOptions(dataStructure, n);
    }

}
