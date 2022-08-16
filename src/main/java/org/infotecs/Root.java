package org.infotecs;

import java.util.Collections;
import java.util.List;

public class Root {

    private List<Student> students;
    private long maxId;

    public void setStudents(List<Student> students) {
        this.students = students;
        sortStudents();
    }

    public void setMaxId(long maxId) {
        this.maxId = maxId;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void printStudentById(long id) {
        boolean isFound = false;
        for (Student student: students) {
            if (student.getId() == id) {
                System.out.printf("ID: %d,\nname: %s\n", student.getId(), student.getName());
                isFound = true;
                break;
            }
        }

        if (!isFound) {
            System.out.println("Student with this ID was not found");
        }
    }

    public void printStudentsByName(String name) {
        boolean isFound = false;
        for (Student student: students) {
            if (name.equals(student.getName())) {
                System.out.printf("ID: %d,\nname: %s\n", student.getId(), student.getName());
                isFound = true;
            }
        }

        if (!isFound) {
            System.out.println("Students with that name were not found");
        }
    }

    public void deleteStudentById(long id) {
        for (Student student: students) {
            if (student.getId() == id) {
                students.remove(student);
                break;
            }
        }
        sortStudents();
    }

    public void addStudent(String name) {
        maxId++;
        Student newStudent = new Student(maxId, name);
        students.add(newStudent);
        sortStudents();
    }

    private void sortStudents() {
        Collections.sort(students);
    }
}
