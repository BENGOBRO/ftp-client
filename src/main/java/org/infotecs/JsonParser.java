package org.infotecs;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class JsonParser {

    private static final String TAG_ID = "id";
    private static final String TAG_NAME = "name";
    private static final String TAG_ROOT = "students";

    public Root parse(String fileName) {

        Root root = new Root();
        List<Student> students = new ArrayList<>();
        JSONParser parser = new JSONParser();

        try (FileReader reader = new FileReader(fileName)) {
            JSONObject jsonRoot = (JSONObject) parser.parse(reader);
            JSONArray jsonStudents = (JSONArray) jsonRoot.get(TAG_ROOT);

            for (Object item: jsonStudents) {
                JSONObject jsonStudent = (JSONObject) item;
                long id = (long) jsonStudent.get(TAG_ID);
                String name = (String) jsonStudent.get(TAG_NAME);
                Student student = new Student(id, name);
                students.add(student);
            }

            root.setStudents(students);

            return root;

        } catch (Exception e) {
            System.out.printf("Error: %s", e.toString());
        }

        return null;
    }
}
