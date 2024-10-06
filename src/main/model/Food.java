package model;

public class Food {
    private int foodAmount; //the food amount the pet has had
    private String foodName;//the name of the food

    // REQUIRES: foodAmount >=0, foodName has a non-zero length
    // EFFECTS: constructs food with foodName and foodAmount
    // with zero funding applied
public Food(String foodName, int foodAmount){
    this.foodName = foodName;
    this.foodAmount = foodAmount;
}
    // REQUIRES: amount >=0
    // MODIFIES: this
    // EFFECTS: increase the foodAmount for this food by amount
public void increaseAmountBy(int amount){
    this.foodAmount += amount;
}
public String getFoodName(){
    return foodName;
}
public int getFoodAmount(){
    return foodAmount;
}

}


