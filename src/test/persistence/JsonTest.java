package persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import model.Food;
import model.Pet;

public class JsonTest {
    protected void checkFood(String foodName, int foodAmount, String dayName, Food food) {
        assertEquals(foodName, food.getFoodName());
        assertEquals(foodAmount, food.getFoodAmount());
        assertEquals(dayName, food.getDayName());
    }

    protected void checkPet(String petName, int labelNumber, ArrayList<Food> foods, Pet pet) {
        assertEquals(petName, pet.getPetName());
        assertEquals(labelNumber, pet.getLabelNumber());
        assertEquals(foods.size(), pet.getFoods().size());
        for (int i = 0; i < foods.size(); i++) {
            Food expected = foods.get(i);
            Food actual = pet.getFoods().get(i);
            checkFood(expected.getFoodName(), expected.getFoodAmount(), expected.getDayName(), actual);
        }
    }

}
