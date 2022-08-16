package org.infotecs;

import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.List;

public class DP {

    @DataProvider
    public static Object[][] someIP() {
        return new Object[][] {{"192.168.30.1", false}, {"192.168.31.143", true}};
    }

    @DataProvider
    public static Object[][] someLoginsAndPasswords() {
        return new Object[][] {{"user", "qwerty", false}, {"Michail", "12345678", true}};
    }

    @DataProvider
    public static Object[][] someFileNames() {
        return new Object[][] {{"students.json", "local.json", true}, {"qpoqw.txt", "", false}};
    }

    @DataProvider
    public static Object[][] parsingResults() {
        Student student1 = new Student(1, "Student1");
        Student student2 = new Student(2, "Student2");
        Student student3 = new Student(3, "student3");

        List<Student> students = new ArrayList<>();
        students.add(student1);
        students.add(student2);
        students.add(student3);

        return new Object[][] {{students, true}};
    }
}
