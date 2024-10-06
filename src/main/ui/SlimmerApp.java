package ui;

import java.util.Scanner;

// Pets food intake documentation app
public class SlimmerApp {
    private boolean isProgramRunning;
    private Scanner scanner;

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
        // stub
    }

    // EFFECTS: displays a list of commands that can be used in the main menu
    public void displayMenu() {
        // stub
    }

    // EFFECTS: processes the user's input in the main menu
    public void processMenuCommands(String input) {
        // stub
    }

    // EFFECTS: add a new pet to home
    public void addNewPet(String petName) {
        // stub
    }

    // EFFECTS: choose a pet from home
    public void choosePet(String petName) {
        // stub
    }

    // EFFECTS: displays all pets at home at a time
    public void viewHome() {
        // stub
    }

    // EFFETCS: displays the weekly report
    public void viewWeeklyReport() {
        // stub
    }

    // EFFETCS: displays the daily report
    public void viewDailyReport() {
        // stub
    }

    // EFFECTS: feed the chosen pet
    public void feed(String food, int amount, String day) {
        // stub
    }

    // EFFECTS: prints out a line of dashes to act as a divider
    private void printDivider() {
        System.out.println("------------------------------------");
    }
}
