package model;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FoodTest {
    private Food testFood;

    @BeforeEach
    void runBefore() {
        testFood = new Food("Fish", 4, "monday");
    }

    @Test
    void testConstructor() {
        assertEquals("Fish", testFood.getFoodName());
        assertEquals(4, testFood.getFoodAmount());
        assertEquals("monday", testFood.getDayName());
    }

    @Test
    void testIncreaseFoodAmount() {
        testFood.increaseAmountBy(10);
        assertEquals(14, testFood.getFoodAmount());
    }
}
