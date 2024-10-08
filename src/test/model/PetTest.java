package model;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;

public class PetTest {
    private Pet testPet;
    private Food f1;
    private Food f2;
    private Food f3;

    @BeforeEach
    void runBefore() {
        testPet = new Pet("Lucky");
        f1 = new Food("Strawberry", 1, "Tuesday");
        f2 = new Food("fish", 0, "Wednesday");
        f3 = new Food("Strawberry", 2, "Wednesday");
    }

    @Test
    void testConstructor() {
        assertEquals("Lucky", testPet.getPetName());
        assertTrue(testPet.getFoods().isEmpty());
        assertEquals(0, testPet.getLabelNumber());
    }

    // Test the case when the pet have one food for one time
    @Test
    void testEatSingleFoodSingleTime() {
        testPet.eatFood(f1, 3);
        ArrayList<Food> foods = testPet.getFoods();
        assertEquals(f1, foods.get(0));
        assertEquals(4, foods.get(0).getFoodAmount());
    }

    // Test the case when the pet have one food for multiple times
    @Test
    void testEatSingleFoodMultipleTime() {
        testPet.eatFood(f1, 3);
        testPet.eatFood(f1, 3);
        ArrayList<Food> foods = testPet.getFoods();
        assertEquals(f1, foods.get(0));
        assertEquals(7, foods.get(0).getFoodAmount());
    }

    // Test the case when the pet have multiple food for multiple times
    @Test
    void testEatMultipleFood() {
        testPet.eatFood(f1, 3);
        testPet.eatFood(f2, 3);
        ArrayList<Food> foods = testPet.getFoods();
        assertEquals(f1, foods.get(0));
        assertEquals(f2, foods.get(1));
        assertEquals(4, foods.get(0).getFoodAmount());
        assertEquals(3, foods.get(1).getFoodAmount());
    }
    // Test the case when the pet eats a food that has a document of being eaten

    @Test
    void testViewDailyFoods() {
        testPet.eatFood(f1, 3);
        testPet.viewDailyFoods("Tuesday");
        ArrayList<Food> tuesdayFoods = testPet.viewDailyFoods("Tuesday");
        assertEquals("Strawberry", tuesdayFoods.get(0).getFoodName());
        assertEquals(4, tuesdayFoods.get(0).getFoodAmount());
    }

    // Test the case when the pet did not eat on a day
    @Test
    void testViewDailyFoodsEmpty() {
        testPet.eatFood(f1, 3);
        testPet.viewDailyFoods("Wednesday");
        ArrayList<Food> wednesdayFoods = testPet.viewDailyFoods("Wednesday");
        assertTrue(wednesdayFoods.isEmpty());
    }

    // Test the case when the pet eat foods on two separate days
    @Test
    void testEatAnotherDay() {
        testPet.eatFood(f1, 3);
        testPet.eatFood(f3, 3);
        ArrayList<Food> tuesdayFoods = testPet.viewDailyFoods("Tuesday");
        ArrayList<Food> wednesdayFoods = testPet.viewDailyFoods("Wednesday");
        assertEquals(f1, tuesdayFoods.get(0));
        assertEquals(f3, wednesdayFoods.get(0));
        assertEquals(4, tuesdayFoods.get(0).getFoodAmount());
        assertEquals(5, wednesdayFoods.get(0).getFoodAmount());
    }

    // Test the set label method
    @Test
    void testSetLabel() {
        testPet.setLabelNumber(10);
        assertEquals(10, testPet.getLabelNumber());
    }

}
