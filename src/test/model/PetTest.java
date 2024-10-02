package model;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class PetTest {
    private Pet testPet;
    
    @BeforeEach
    void runBefore() {
        testPet = new Pet("Lucky");
    }

    @Test
    void testConstructor() {
       assertEquals("Lucky", testPet.getPetName());
       assertEquals(0, testPet.getFoodAmount());
       assertEquals(0, testPet.getTreatAmount());
    }
    @Test
    void testEatFood(){
        testPet.eatFood(20);
        assertEquals(20, testPet.getFoodAmount());
        testPet.eatFood(10);
        assertEquals(30, testPet.getFoodAmount());
    }
    @Test
    void testEatTreats(){
        testPet.eatTreat(20);
        assertEquals(20, testPet.getTreatAmount());
        testPet.eatTreat(10);
        assertEquals(30, testPet.getTreatAmount());
    }

}
