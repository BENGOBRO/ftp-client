package org.infotecs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Root {

    private List<Student> students;

    public void setStudents(List<Student> students) {
        Collections.sort(students);
        this.students = students;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void printStudentById(long id) {
        for (Student student: students) {
            if (student.getId() == id) {
                System.out.printf("ID: %d,\nname: %s\n", student.getId(), student.getName());
            }
        }
    }

    public void printStudentsByName(String name) {
        for (Student student: students) {
            if (name.equals(student.getName())) {
                System.out.printf("ID: %d,\nname: %s\n", student.getId(), student.getName());
            }
        }
    }

    @Override
    public String toString() {
        return "Root{" +
                "students=" + students +
                '}';
    }
}
