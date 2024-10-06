package model;

import java.util.*;

public class Pet {
    private String petName; // name of the pet
    private ArrayList<Food> foods; // the list of food the cat has had today

    /*
     * REQUIRES: petName has a non-zero length
     * EFFECTS: name on the pet is set to petName ; set foods be
     * an empty list
     */
    public Pet(String petName) {
        this.petName = petName;
        this.foods = new ArrayList<>();
    }

    // REQUIRES: amount>=0
    // MODIFIES: this, food
    // EFFECTS: when the new food is not in the list, add the newfood.
    // when the new food is something already in the list,
    // just increase the foodAmount.
    public void eatFood(Food newFood, int amount) {
        boolean found = false;
        String newFoodName = newFood.getFoodName();
        for (Food food : foods) {
            if (food.getFoodName().equals(newFoodName)) {
                found = true;
                food.increaseAmountBy(amount);
                break;
            }
        }
        if (!found) {
            foods.add(newFood);
            newFood.increaseAmountBy(amount);
        }
    }

    // REQUIRES: day must be a day in the week, eg. Monday
    // EFFECT: show all the food the pet has had in the given day
    public ArrayList<Food> viewDailyFoods(String day) {
        ArrayList<Food> dailyFoods = new ArrayList<>();
        for (Food food : foods) {
            if (food.getDayName().equals(day)) {
                dailyFoods.add(food);
            }
        }
        return dailyFoods;
    }

    public String getPetName() {
        return petName;

    }

    public ArrayList<Food> getFoods() {
        return foods;
    }
}