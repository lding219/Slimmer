package model;
import java.util.*;
import java.util.ArrayList;


public class Home {
    private ArrayList<Pet> home;

    public Home() {
        this.home = new ArrayList<>();
    }
    public void addPet(Pet pet){
        this.home.add(pet);
    }
    public ArrayList<Pet> viewHome(){
        return home;
    }
    public int getHomeSize(){
        return home.size();
    }
    
}
