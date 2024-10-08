package model;

import java.util.ArrayList;

// Represents a list of pets at home
public class Home {
    private ArrayList<Pet> home;

    // EFFECTS: Constructs an empty list of pets
    public Home() {
        this.home = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: add a new pet to the list of old pets
    public void addPet(Pet pet) {
        this.home.add(pet);
    }

    public ArrayList<Pet> viewHome() {
        return home;
    }

    public int getHomeSize() {
        return home.size();
    }
    
}
