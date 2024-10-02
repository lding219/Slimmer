package model;

public class Pet {
    private String petName; // name of the pet
    private int foodAmount; // food in grams the pet has had in a day
    private int treatAmount; // treats in grams the pet has had in a day


/*
 * REQUIRES: petName has a non-zero length
 * EFFECTS: name on the pet is set to petName ; set foodAmount
 * and treatAmount = 0
 */
public Pet(String petName){
    this.petName = petName;
    foodAmount = 0;
    treatAmount = 0;
}

/*
 * REQUIRES: amount>=0
 * MODIFIERS: this
 * EFFECTS: add single food intake to the total amount of food 
 *          the pet has had today
 */
public void eatFood(int amount){
    this.foodAmount += amount;
}
/*
 * REQUIRES: amount>=0
 * MODIFIERS: this
 * EFFECTS: add single treat intake to the total amount of treat 
 *          the pet has had today
 */
public void eatTreat(int amount){
    this.treatAmount += amount;
}
public String getPetName(){
    return petName;

}
public int getFoodAmount(){
    return foodAmount;
}
public int getTreatAmount(){
    return treatAmount;
}
}