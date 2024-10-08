package ui;

import java.util.ArrayList;
import java.util.Scanner;
import model.*;

// My code of this class is referencing TellerApp and FlashcardReviewer
// Pets food intake documentation app
public class SlimmerApp {
    private boolean isProgramRunning;
    private Scanner scanner;
    private ArrayList<Pet> home;

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
        System.out.println("d: View daily report of a pet before feeding");
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
            case "d":
                viewDailyReport();
                break;
            default:
                System.out.println("Invalid option inputted. Please try again.");
        }
        printDivider();
    }

    // EFFECTS: prompts the user to add a new pet to home
    public void addNewPet() {
        System.out.println("Please enter the pet's name");
        String petName = this.scanner.nextLine();
        Pet pet = new Pet(petName);
        this.home.add(pet);
        System.out.println("\nNew pet successfully added!");
    }

    // EFFECTS: displays all pets at home at a time,
    // promopts user to enter a pet label view the
    // weekly report.
    // if the pet is in home, it will return a weekly report
    public void viewHome() {
        if (!home.isEmpty()) {
            labelPrinter();
            System.out.println("Please enter the pet you want to view the weekly report");
            int reportNumber = this.scanner.nextInt();
            this.scanner.nextLine();
            for (Pet pet : home) {
                if (reportNumber == pet.getLabelNumber()) {
                    weeklyReportPrinter(pet);
                }
            }
        } else {
            System.out.println("Empty Home! Please add a pet!");
            displayMenu();

        }
    }

    // EFFETCS: displays the daily report if the label
    // user entered is in home
    public void viewDailyReport() {
        labelPrinter();
        boolean found = false;
        System.out.println("Please enter the pet you want to view the daily report");
        int dailyNum = this.scanner.nextInt();
        this.scanner.nextLine();
        System.out.println("Please enter the day you want to check. eg. Monday");
        String dayName = this.scanner.nextLine();
        for (Pet pet : home) {
            if (dailyNum == (pet.getLabelNumber())) {
                found = true;
                dailyReportPrinter(pet, dayName);
            }
        }
     if (!found) {
        System.out.println("There is no such pet in your home, please add it first!");
    }
    }

    // EFFETCS: print the weekly report based on printing daily reports
    public void weeklyReportPrinter(Pet pet) {
        System.out.println("Weekly Food Intake Report for " + pet.getPetName() + ":");
        String[] days = { "Monday", "Tuesday", "Wednesday",
                "Thursday", "Friday", "Saturday", "Sunday" };
        for (String day : days) {
            dailyReportPrinter(pet, day);

        }
    }

    // EFFETCS: print the daily report based on the food name and food
    // amount the pet has had on a given day
    private void dailyReportPrinter(Pet pet, String day) {
        ArrayList<Food> dailyFoods = pet.viewDailyFoods(day);
        if (dailyFoods.isEmpty()) {
            System.out.println(day + ": Alert! Ate nothing");
        } else {
            System.out.print(day + ": ");
            for (Food food : dailyFoods) {
                System.out
                        .print(pet.getPetName() + " ate " + food.getFoodName() + " by " + food.getFoodAmount() + "\n");
            }
            System.out.println();
        }
    }

    // EFFECTS: label the pets at home, prompts the user to feed the pet
    // with using the number labelled
    public void feed() {
        labelPrinter();
        System.out.println("Please enter the pet you want to feed");
        int petNumber = this.scanner.nextInt();
        this.scanner.nextLine();
        boolean included = false;
        for (Pet pet : home) {
            if (petNumber == pet.getLabelNumber()) {
                included = true;
                System.out.println("What food do you want to feed?");
                String foodName = this.scanner.nextLine();
                System.out.println("How much?");
                int foodAmount = this.scanner.nextInt();
                this.scanner.nextLine();
                System.out.println("On what day? eg. Monday");
                String day = this.scanner.nextLine();
                pet.eatFood(new Food(foodName, 0, day), foodAmount);
                System.out.println("Yummy!");
                break;
            }
        }
        if (!included) {
            System.out.println("There is no such pet at home!");
        }
    }

    // MODIFIES: pet, this
    // EFFECTS: label the pets at home
    public void labelPets() {
        int i = 1;
        for (Pet pet : home) {
            pet.setLabelNumber(i);
            i++;
        }
    }

    public void labelPrinter() {
        if (!home.isEmpty()) {
            labelPets();
            System.out.println("You have these pets at home: ");
            for (Pet pet : home) {
                System.out.println(pet.getLabelNumber() + ". " + pet.getPetName());
            }
        } else {
            System.out.println("Home is empty! Please add a pet!");
        }
    }

    // EFFECTS: prints out a line of dashes to act as a divider
    private void printDivider() {
        System.out.println("------------------------------------");
    }
}
