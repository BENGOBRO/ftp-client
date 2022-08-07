package org.infotecs;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonParser {

    private static final String TAG_ID = "id";
    private static final String TAG_NAME = "name";
    private static final String TAG_ROOT = "students";

    //private static long maxId = Long.MAX_VALUE;

    public Root parse(String fileName) {

        Root root = new Root();
        long maxId = Long.MIN_VALUE;
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
                maxId = Long.max(maxId, id);
            }

            root.setStudents(students);
            root.setMaxId(maxId);

            return root;

        } catch (Exception e) {
            System.out.printf("Error: %s", e.toString());
        }

        return null;
    }

    public void write(List<Student> students, String fileName) {

        JSONObject jsonRoot = new JSONObject();
        JSONArray jsonStudents = new JSONArray();

        for (Student student: students) {
            JSONObject item = new JSONObject();
            item.put(TAG_ID, student.getId());
            item.put(TAG_NAME, student.getName());
            jsonStudents.add(item);
        }

        jsonRoot.put(TAG_ROOT, jsonStudents);

        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(jsonRoot.toJSONString());
        } catch (IOException e){
            System.out.printf("Error: %s", e.toString());
        }


    }
}
