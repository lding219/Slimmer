package persistence;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import model.Home;
import model.Pet;
import model.Food;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            Home h = new Home();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyHome() {
        try {
            Home h = new Home();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyHome.json");
            writer.open();
            writer.write(h);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyHome.json");
            h = reader.read();
            assertEquals(0, h.getHomeSize());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralHome() {
        try {
            Home h = new Home();
            h.addPet(new Pet("Lucky"));
            ArrayList<Pet> pets = h.viewHome();
            Pet lucky = pets.get(0);
            lucky.eatFood(new Food("apple", 0, "Monday"), 10);
            lucky.eatFood(new Food("bone", 0, "Tuesday"), 1);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralHome.json");
            writer.open();
            writer.write(h);
            writer.close();
            JsonReader reader = new JsonReader("./data/testWriterGeneralHome.json");
            h = reader.read();
            assertEquals(1, h.getHomeSize());
            lucky.setLabelNumber(1);
            checkPet("Lucky", 1, lucky.getFoods(), lucky);
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

}
