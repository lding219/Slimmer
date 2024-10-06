package ui;

import java.util.ArrayList;
import java.util.Scanner;
import model.*;

// Pets food intake documentation app
public class SlimmerApp {
    private boolean isProgramRunning;
    private Scanner scanner;
    private ArrayList<Pet> home;
    private Pet pet;

    // run the slimmer app
    public SlimmerApp() {
        init();
        printDivider();
        System.out.println("Welcome to Slimmer!");
        printDivider();
        while (this.isProgramRunning) {
            handleMenu();
        }
    }

    // EFFECTS: displays and processes inputs for the main menu
    public void handleMenu() {
        displayMenu();
        String input = this.scanner.nextLine();
        processMenuCommands(input);
    }

    // MODIFIES: this
    // EFFECT: initialize the slimmer app with the starting values
    public void init() {
        this.home = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        this.isProgramRunning = true;
    }

    // EFFECTS: displays a list of commands that can be used in the main menu
    public void displayMenu() {
        System.out.println("Please select an option:\n");
        System.out.println("a: Add a new pet");
        System.out.println("v: Visit my home");
        System.out.println("f: Feed my pet");
        printDivider();
    }

    // EFFECTS: processes the user's input in the main menu
    public void processMenuCommands(String input) {
        printDivider();
        switch (input) {
            case "a":
                addNewPet();
                break;
            case "v":
                viewHome();
                break;
            case "f":
                feed();
                break;
            default:
                System.out.println("Invalid option inputted. Please try again.");
        }
        printDivider();
    }

    // EFFECTS: add a new pet to home
    public void addNewPet() {
        System.out.println("Please enter the pet's name");
        String petName = this.scanner.nextLine();
        Pet pet = new Pet(petName);
        this.home.add(pet);
        System.out.println("\nNew pet successfully added!");
    }

    // EFFECTS: displays all pets at home at a time,
    // user can choose a pet by entering the name
    // if the name is in home, it will return a weekly report
    public void viewHome() {
        for (Pet pet : home) {
            System.out.println(pet.getPetName());
        }
        if (!home.isEmpty()) {
            System.out.println("Please enter the pet name you want to view the weekly report");
            String reportName = this.scanner.nextLine();
            for (Pet pet : home) {
                if (reportName.equals(pet.getPetName())) {
                    weeklyReportPrinter(pet);
                }
            }
        }
        else{System.out.println("Empty Home! Please add a pet!");
        displayMenu();

        }
    }
   
    public void weeklyReportPrinter(Pet pet){
        System.out.println("Weekly Food Intake Report for" + pet.getPetName() + ":");
        String[] days = {"Monday", "Tuesday", "Wednesday", 
        "Thursday", "Friday", "Saturday", "Sunday"};
        for (String day : days) {
            dailyReportPrinter(pet, day);
            
        }
    }

    private void dailyReportPrinter(Pet pet, String day) {
        ArrayList<Food> dailyFoods = pet.viewDailyFoods(day);
        if (dailyFoods.isEmpty()) {
            System.out.println(day + ": Alert! Ate nothing");
    }else {
        System.out.print(day + ": ");
        for (Food food : dailyFoods) {
            System.out.print(food.getFoodName() + " (" + food.getFoodAmount() + "), ");
        }
        System.out.println();
    }}

    // EFFECTS: feed the chosen pet
    public void feed() {
        System.out.println("Please enter the name of the pet you want to feed");
        String petName = this.scanner.nextLine();
        boolean thereItIs = false;// check if the pet is in home
        for (Pet pet : home) {
            if (petName.equals(pet.getPetName())) {
                thereItIs = true;
                System.out.println("What food do you want to feed?");
                String foodName = this.scanner.nextLine();
                System.out.println("How much?");
                int foodAmount = this.scanner.nextInt();
                this.scanner.nextLine();
                System.out.println("On what day? eg. Monday");
                String day = this.scanner.nextLine();
                Food food = new Food(foodName, 0, day);
                pet.eatFood(food, foodAmount);
                System.out.println("Yummy!");
                break;
            }
        }
        if (!thereItIs) {
            System.out.println("Invalid option inputted. Please try again.");
        }
    }
    // EFFECTS: prints out a line of dashes to act as a divider
    private void printDivider() {
        System.out.println("------------------------------------");
    }
}
