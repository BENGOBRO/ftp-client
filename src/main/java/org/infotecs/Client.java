package org.infotecs;

import java.util.Scanner;

public class Client {

    private static final String ACTION_ADD = "add student";
    private static final String ACTION_EXIT = "exit";
    private static final String ACTION_GET_BY_ID = "get student by id";
    private static final String ACTION_GET_BY_NAME = "get students by name";
    private static final String ACTION_DELETE_BY_ID = "delete student by id";
    private static final Scanner scanner = new Scanner(System.in);
    private static final JsonParser parser = new JsonParser();
    private static final FTPServer server = new FTPServer();
    private static Root root;

    public static void main(String[] args) {
        System.out.println("Welcome to FTP client!\n");
        selectConnectionType();
        connectToServer();
        loginIntoServer();
        selectFile();
        workWithFile();
    }

    public static void selectConnectionType() {
        System.out.println("Select connection type (active : passive)");
        while (true) {
            String type = scanner.nextLine();
            if ("passive".equals(type)) {
                server.setPassive(true);
                break;
            } else if ("active".equals(type)) {
                server.setPassive(false);
                break;
            } else {
                System.out.println("Unknown type!");
            }
        }
    }

    public static void connectToServer() {
        boolean isConnected = false;
        while (!isConnected) {
            System.out.println("Enter an IP address of the server:");
            String IP = scanner.nextLine();
            server.setIP(IP);
            isConnected = server.connect();
        }
    }

    public static void loginIntoServer() {
        boolean isLogged = false;
        while (!isLogged) {
            System.out.println("Enter a login:");
            String login = scanner.nextLine();
            System.out.println("Enter a password:");
            String password = scanner.nextLine();
            isLogged = server.signIn(login, password);
            if (isLogged) {
                System.out.println("Login completed");
            } else {
                System.out.println("Login failed");
            }
        }
    }

    public static void selectFile() {
        while (true) {
            System.out.println("Enter a file name on the FTP server:");
            String fileName = scanner.nextLine();
            System.out.println("Enter a file name on your computer " +
                    "(Such a file is needed to save data locally)");
            String localFileName = scanner.nextLine();
            if (server.downloadFile(fileName, localFileName)) {
                System.out.println("Download successful!");
                root = parser.parse(localFileName);
                break;
            }
        }
    }

    public static void workWithFile() {
        String request;
        System.out.printf("Available actions:\n%s : %s : %s : %s : %s\n",
                ACTION_GET_BY_ID, ACTION_GET_BY_NAME, ACTION_DELETE_BY_ID, ACTION_ADD, ACTION_EXIT);

        while (true) {
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
                System.out.println("Shutdown...");
                parser.write(root.getStudents(), "local.json");
                if (server.uploadFile("students.json", "local.json")) {
                    System.out.println("Upload successful!");
                };
                server.disconnect();
                break;
            } else {
                System.out.println("Unavailable request!");
            }
        }
    }

    private static int enterId() {
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

    private static String enterName()  {
        System.out.println("Enter name: ");
        return scanner.nextLine();
    }
}
