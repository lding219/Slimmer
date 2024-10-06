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

    @BeforeEach
    void runBefore() {
        testPet = new Pet("Lucky");
        f1 = new Food("Stawberry", 1);
        f2 = new Food("fish", 0);
    }

    @Test
    void testConstructor() {
        assertEquals("Lucky", testPet.getPetName());
        assertTrue(testPet.getFoods().isEmpty());
    }
//Test the case when the pet have one food for one time
    @Test
    void testEatSingleFoodSingleTime() {
        testPet.eatFood(f1, 3);
        ArrayList<Food> foods = testPet.getFoods();
        assertEquals(f1, foods.get(0));
        assertEquals(4, foods.get(0).getFoodAmount()); 
    }
    //Test the case when the pet have one food for multiple times
    @Test
    void testEatSingleFoodMultipleTime() {
        testPet.eatFood(f1, 3);
        testPet.eatFood(f1, 3);
        ArrayList<Food> foods = testPet.getFoods();
        assertEquals(f1, foods.get(0));
        assertEquals(7, foods.get(0).getFoodAmount()); 
    }
    //Test the case when the pet have multiple food for multiple times
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
}
