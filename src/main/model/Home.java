package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;
import java.util.ArrayList;

// Represents a home includes a list of pets
public class Home implements Writable {
    private ArrayList<Pet> home;

    // EFFECTS: Constructs an empty list of pets
    public Home() {
        this.home = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: add a new pet to the list of old pets
    public void addPet(Pet pet) {
        this.home.add(pet);
        EventLog.getInstance().logEvent(new Event("Successfully added " + pet.getPetName()));
    }

    // MODIFIES: this
    // EFFECTS: remove a pet from the list of old pets
    public void removePet(Pet pet) {
        home.remove(pet);
        EventLog.getInstance().logEvent(new Event("Successfully removed " + pet.getPetName()));
    }

    public ArrayList<Pet> viewHome() {
        return home;
    }

    public int getHomeSize() {
        return home.size();
    }

    public Pet getPet(String petName) {
        for (Pet p : home) {
            if (p.getPetName().equals(petName)) {
                return p;
            }
        }
        return null;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("home", homeToJson());
        return json;
    }

    // EFFECTS: returns things in this workroom as a JSON array
    private JSONArray homeToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Pet p : home) {
            jsonArray.put(p.toJson());
        }

        return jsonArray;
    }

}
