package persistence;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.JSONArray;
import org.json.JSONObject;

import model.Event;
import model.EventLog;
import model.Food;
import model.Home;
import model.Pet;

// Referenced from the JsonSerialization Demo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
// Represents a reader that reads home from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads home from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Home read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        EventLog.getInstance().logEvent(new Event("Successfully read data!"));
        return parseHome(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses home from JSON object and returns it
    private Home parseHome(JSONObject jsonObject) {
        Home home = new Home();
        addPetsToHome(home, jsonObject);
        return home;
    }

    // MODIFIES: home
    // EFFECTS: parses pet from JSON object and adds them to home
    private void addPetsToHome(Home h, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("home");
        for (Object json : jsonArray) {
            JSONObject petJson = (JSONObject) json;
            addPet(h, petJson);
        }
    }

    // MODIFIES: home
    // EFFECTS: parses thingy from JSON object and adds it to home
    private void addPet(Home h, JSONObject jsonObject) {
        String petName = jsonObject.getString("petName");
        int labelNumber = jsonObject.getInt("labelNumber");
        Pet p = new Pet(petName);
        p.setLabelNumber(labelNumber);
        JSONArray foodsJson = jsonObject.getJSONArray("foods");
        addFoodsToPet(p, foodsJson);
        h.addPet(p);
    }

    // MODIFIES: pet
    // EFFECTS: parses foods from JSON object and adds it to pet
    private void addFoodsToPet(Pet pet, JSONArray jsonArray) {
        for (Object json : jsonArray) {
            JSONObject foodJson = (JSONObject) json;
            String foodName = foodJson.getString("foodName");
            int foodAmount = foodJson.getInt("foodAmount");
            String dayName = foodJson.getString("dayName");
            Food food = new Food(foodName, foodAmount, dayName);
            pet.getFoods().add(food);
        }
    }
}
