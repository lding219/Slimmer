package persistence;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import model.Home;
import model.Pet;
import model.Food;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Home h = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyHome() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyHome.json");
        try {
            Home h = reader.read();
            assertEquals(0, h.getHomeSize());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralHome() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralHome.json");
        try {
            Home h = reader.read();
            assertEquals(1, h.getHomeSize());
            ArrayList<Pet> pets = h.viewHome();
            ArrayList<Food> foodsLucky = new ArrayList<>();
            Food apple = new Food("apple", 10, "Monday");
            Food bone = new Food("bone", 1, "Tuesday");
            foodsLucky.add(apple);
            foodsLucky.add(bone);
            checkPet("Lucky", 1, foodsLucky, pets.get(0));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

}
