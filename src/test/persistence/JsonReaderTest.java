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
            assertEquals(2, h.getHomeSize());
            ArrayList<Pet> pets = h.viewHome();
            ArrayList<Food> foodsLucky = new ArrayList<>();
            ArrayList<Food> foodsTutu = new ArrayList<>();
            Food apple = new Food("apple", 10, "Monday");
            Food bone = new Food("bone", 1, "Tuesday");
            Food treats = new Food("treats", 5, "Wednesday");
            Food fish = new Food("fish", 2, "Friday");
            foodsLucky.add(apple);
            foodsLucky.add(bone);
            foodsTutu.add(treats);
            foodsTutu.add(fish);
            checkPet("Lucky", 1, foodsLucky, pets.get(0));
            checkPet("Tutu", 2, foodsTutu, pets.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

}
