package ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import model.*;
import model.exception.LogException;
import persistence.JsonReader;
import persistence.JsonWriter;

// My code of this class is referencing TellerApp and FlashcardReviewer
// Pets food intake documentation app
public class SlimmerApp implements LogPrinter {
    private boolean isProgramRunning;
    private Scanner scanner;
    private Home home;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private static final String JSON_STORE = "./data/home.json";

    // run the slimmer app
    public SlimmerApp() throws FileNotFoundException {
        this.jsonWriter = new JsonWriter(JSON_STORE);
        this.jsonReader = new JsonReader(JSON_STORE);
        this.home = new Home();
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
        if (input.equals("q")) {
            try {
                printLog(EventLog.getInstance());
            } catch (LogException e) {
                System.out.println("Log error!");
            }
            isProgramRunning = false;
            System.out.println("\nGoodbye!");
        } else {
            processMenuCommands(input);
        }

    }

    // MODIFIES: this
    // EFFECT: initialize the slimmer app with the starting values
    public void init() {
        this.home = new Home();
        this.scanner = new Scanner(System.in);
        this.isProgramRunning = true;
    }

    // EFFECTS: displays a list of commands that can be used in the main menu
    public void displayMenu() {
        System.out.println("Please select an option:\n");
        System.out.println("l: Load previous data");
        System.out.println("a: Add a new pet");
        System.out.println("v: Visit my home");
        System.out.println("d: View daily report of a pet before feeding");
        System.out.println("f: Feed my pet");
        System.out.println("s: Save before quit");
        System.out.println("q: quit without save");
        printDivider();
    }

    // EFFECTS: processes the user's input in the main menu
    public void processMenuCommands(String input) {
        switch (input) {
            case "l":
                load();
                break;
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
            case "s":
                saveBeforeQuit();
                break;
            default:
                System.out.println("Invalid option inputted. Please try again.");
        }
        printDivider();
    }

    // MODIFIES: this
    // EFFTECS: load home from file
    public void load() {
        try {
            home = jsonReader.read();
            System.out.println("Loaded " + "home" + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // EFFECTS: save home to file
    public void saveBeforeQuit() {
        try {
            jsonWriter.open();
            jsonWriter.write(home);
            jsonWriter.close();
            System.out.println("Saved " + "home" + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // EFFECTS: prompts the user to add a new pet to home
    public void addNewPet() {
        System.out.println("Please enter the pet's name");
        String petName = this.scanner.nextLine();
        Pet pet = new Pet(petName);
        this.home.addPet(pet);
        System.out.println("\nNew pet successfully added!");
    }

    // EFFECTS: displays all pets at home at a time,
    // promopts user to enter a pet label to view the
    // weekly report.
    // if the pet is in home, it will return a weekly report
    public void viewHome() {
        ArrayList<Pet> pets = home.viewHome();
        if (!pets.isEmpty()) {
            labelPrinter();
            System.out.println("Please enter the pet you want to view the weekly report");
            int reportNumber = this.scanner.nextInt();
            this.scanner.nextLine();
            for (Pet pet : pets) {
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
        ArrayList<Pet> pets = home.viewHome();
        labelPrinter();
        boolean found = false;
        System.out.println("Please enter the pet you want to view the daily report");
        int dailyNum = this.scanner.nextInt();
        this.scanner.nextLine();
        System.out.println("Please enter the day you want to check. eg. Monday");
        String dayName = this.scanner.nextLine();
        for (Pet pet : pets) {
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
            System.out.println(day + ": ");
            for (Food food : dailyFoods) {
                System.out.println(
                        pet.getPetName() + " ate " + food.getFoodName() + " by " + food.getFoodAmount() + "\n");
            }
            System.out.println();
        }
    }

    // EFFECTS: label the pets at home, prompts the user to feed the pet
    // with using the number labelled
    public void feed() {
        ArrayList<Pet> pets = home.viewHome();
        labelPrinter();
        System.out.println("Please enter the pet you want to feed");
        int petNumber = this.scanner.nextInt();
        this.scanner.nextLine();
        boolean included = false;
        for (Pet pet : pets) {
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
        ArrayList<Pet> pets = home.viewHome();
        int i = 1;
        for (Pet pet : pets) {
            pet.setLabelNumber(i);
            i++;

        }
    }

    // MODIFIES: this
    // EFFECTS: print the label and the pet names.
    // if there is no pets at home, asks the user to add a pet
    public void labelPrinter() {
        ArrayList<Pet> pets = home.viewHome();
        if (!pets.isEmpty()) {
            labelPets();
            System.out.println("You have these pets at home: ");
            for (Pet pet : pets) {
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

    public static void main(String[] args) {
        try {
            new SlimmerApp();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }
    }

    @Override
    public void printLog(EventLog el) throws LogException {
        for (Event next : el) {
            System.out.println(next.toString());
        }
    }
}
