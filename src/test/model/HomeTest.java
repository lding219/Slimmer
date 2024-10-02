package model;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

public class HomeTest {
    private Home testHome;
    private Pet p1;
    private Pet p2;

    @BeforeEach
    void runBefore(){
        testHome = new Home();
        p1 = new Pet("Lucky");
        p2 = new Pet("Tutu");
    }
    @Test
    void testConstructor(){
        assertEquals(0, testHome.getHomeSize());
    }
    @Test
    void testAddPet(){
        assertEquals(0, testHome.getHomeSize());
        testHome.addPet(p1);
        assertEquals(1, testHome.getHomeSize());
        testHome.addPet(p2);
        assertEquals(2,testHome.getHomeSize());
        ArrayList<Pet> pets = testHome.viewHome();
        assertEquals(p1, pets.get(0));
        assertEquals(p2, pets.get(1));

    }



}
