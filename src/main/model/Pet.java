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
public Pet(String petName){
    this.petName = petName;
    this.foods = new ArrayList<>();
}
// REQUIRES: amount>=0
// MODIFIES: this, food
// EFFECTS: the food is added to the list of the food the pet has had,
// and docoment the amount.
public void eatFood(Food food, int amount){ 
    this.foods.add(food);
    food.increaseAmountBy(amount);
}

public String getPetName(){
    return petName;

}
public ArrayList<Food> getFoods(){
    return foods;
}
}