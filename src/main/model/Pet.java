package model;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import persistence.Writable;

// Represents a pet having a name, a
// label number, and a list of foods the pet has had during
// the week.
public class Pet implements Writable {
    private int labelNumber; // label of the pet
    private String petName; // name of the pet
    private ArrayList<Food> foods; // the list of food the pet has had during the whole week

    /*
     * REQUIRES: petName has a non-zero length
     * EFFECTS: name on the pet is set to petName ; set foods be
     * an empty list
     */
    public Pet(String petName) {
        labelNumber = 0;
        this.petName = petName;
        this.foods = new ArrayList<>();
    }

    // REQUIRES: amount>=0
    // MODIFIES: this, food
    // EFFECTS: when the new food is not in the list, add newfood to foods.
    // when the new food is something already in the list,
    // just increase the foodAmount.
    public void eatFood(Food newFood, int amount) {
        if (foods.contains(newFood)) {
            newFood.increaseAmountBy(amount);
        } else {
            foods.add(newFood);
            newFood.increaseAmountBy(amount);
        }
        EventLog.getInstance()
                .logEvent(new Event("Feed " + this.getPetName() + " " + newFood.getFoodName() + " by " + amount));
    }

    // REQUIRES: day must be a day in the week, eg. Monday
    // MODIFIES: this
    // EFFECT: show all the food the pet has had in the given day
    public ArrayList<Food> viewDailyFoods(String day) {
        ArrayList<Food> dailyFoods = new ArrayList<>();
        for (Food food : foods) {
            if (food.getDayName().equals(day)) {
                dailyFoods.add(food);
            }
        }
        EventLog.getInstance().logEvent(new Event("View foods " + this.getPetName() + " ate on " + day));
        return dailyFoods;
    }

    public String getPetName() {
        return petName;

    }

    public int getLabelNumber() {
        return labelNumber;
    }

    public void setLabelNumber(int labelNumber) {
        this.labelNumber = labelNumber;
    }

    public ArrayList<Food> getFoods() {
        return foods;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("petName", this.petName);
        json.put("labelNumber", this.labelNumber);
        json.put("foods", foodsToPet());
        return json;
    }

    // EFFECTS: returns things in this workroom as a JSON array
    private JSONArray foodsToPet() {
        JSONArray jsonArray = new JSONArray();

        for (Food f : foods) {
            jsonArray.put(f.toJson());
        }

        return jsonArray;
    }
}