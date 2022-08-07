package org.infotecs;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    private static final String ACTION_ADD = "add";
    private static final String ACTION_EXIT = "exit";
    private static final String ACTION_GET_BY_ID = "get student by id";
    private static final String ACTION_GET_BY_NAME = "get students by name";
    private static final String ACTION_DELETE_BY_ID = "delete student by id";
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String request = "";

        JsonParser parser = new JsonParser();
        Root root = parser.parse("students.json");
        System.out.println("Welcome to FTP client!\n");
        System.out.printf("Available actions:\n%s\n%s\n%s\n%s\n%s\n",
                ACTION_GET_BY_ID, ACTION_GET_BY_NAME, ACTION_DELETE_BY_ID, ACTION_ADD, ACTION_EXIT);


        while (!"exit".equals(request)) {
            System.out.println("Enter a request: ");
            request = scanner.nextLine();

            if (request.equals(ACTION_GET_BY_ID)) {
                root.printStudentById(enterId());
            } else if (request.equals(ACTION_GET_BY_NAME)) {
                root.printStudentsByName(enterName());
            } else if (request.equals(ACTION_DELETE_BY_ID)) {
                root.deleteStudentById(enterId());
            } else if (request.equals(ACTION_ADD)) {
                root.addStudent(enterName());
            } else if (request.equals(ACTION_EXIT)) {
                System.out.println("Disabling");
                parser.write(root.getStudents(), "students.json");
            } else {
                System.out.println("Unavailable request!");
            }
        }
    }

    public static int enterId() {
        while (true) {
            System.out.print("Enter id: ");
            String str = scanner.nextLine();
            try {
                return Integer.parseInt(str);
            } catch (NumberFormatException e) {
                System.out.println("Error: invalid format of id. Enter integer number.");
            }
        }
    }

    public static String enterName()  {
        System.out.println("Enter name: ");
        return scanner.nextLine();
    }
}
