package model;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

public class HomeTest {
    private Home testHome;
    private Pet p1;
    private Pet p2;

    @BeforeEach
    void runBefore() {
        testHome = new Home();
        p1 = new Pet("Lucky");
        p2 = new Pet("Tutu");
    }

    @Test
    void testConstructor() {
        assertEquals(0, testHome.getHomeSize());
    }

    @Test
    void testAddPet() {
        assertEquals(0, testHome.getHomeSize());
        testHome.addPet(p1);
        assertEquals(1, testHome.getHomeSize());
        testHome.addPet(p2);
        assertEquals(2, testHome.getHomeSize());
        ArrayList<Pet> pets = testHome.viewHome();
        assertEquals(p1, pets.get(0));
        assertEquals(p2, pets.get(1));

    }

    @Test
    void testRemovePet() {
        testHome.addPet(p1);
        testHome.addPet(p2);
        assertEquals(2, testHome.getHomeSize());
        ArrayList<Pet> pets = testHome.viewHome();
        assertEquals(p1, pets.get(0));
        assertEquals(p2, pets.get(1));
        testHome.removePet(p1);
        ArrayList<Pet> petsAfterRemove = testHome.viewHome();
        assertEquals(1, testHome.getHomeSize());
        assertEquals(p2, petsAfterRemove.get(0));
    }

    @Test
    void testGetPet() {
        testHome.addPet(p1);
        testHome.addPet(p2);
        ArrayList<Pet> pets = testHome.viewHome();
        Pet petOne = testHome.getPet("Lucky");
        assertEquals(petOne, pets.get(0));
        Pet petTwo = testHome.getPet("Tutu");
        assertEquals(petTwo, pets.get(1));
        assertNull(testHome.getPet("Pika"));
    }

}
