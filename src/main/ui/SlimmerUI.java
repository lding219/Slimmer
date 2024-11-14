package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

public class SlimmerUI extends JFrame implements ActionListener {
    private JLabel label;
    private JButton addPetButton;
    private JButton feedButton;
    private JButton loadButton;
    private JButton saveButton;
    private JButton viewHomeButton;
    private JButton removePetButton;
    private JPanel menuPanel;
    private Home home;
    private static final String JSON_STORE = "./data/home.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private JTextField field;
    private GridBagConstraints gbc;
    private JLabel whatFood;
    private JTextField whatFoodField;
    private JTextField howMuchField;
    private JLabel howMuchFood;
    private JLabel dateLabel;
    private JButton confirmButton;
    private JComboBox<String> dayComboBox;
    private String[] days = { "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday" };
    private JComboBox<String> petComboBox;
    private JFrame feedFrame;
    private JPanel feedPanel;
    private Pet selectedPet;
    private String selectedDay;
    private JFrame removeFrame;
    private JPanel removePanel;
    private JButton confirmRemoveButton;
    private JButton viewWeeklyReport;
    private JFrame viewFrame;
    private JPanel viewPanel;
    private JButton viewButton;
    private JButton viewDailyReportButton;
    private JFrame dailyReportFrame;
    private JPanel dailyReportPanel;
    private JButton viewDailyButton;
    private GridBagConstraints gbcDaily;
    private JFrame dailyPrinterFrame;
    private JPanel dailyPrinterPanel;
    private JFrame weeklyPrinterFrame;
    private JPanel weeklyPrinterPanel;
    private ImageIcon petImageIcon;
    private JFrame homeFrame;
    private JPanel homePanel;

    //
    public SlimmerUI() {
        super("Slimmer");
        initialize();
        setUpLayout();
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
    }

    // MODIFIES: this
    // EFFECTS: helper method to initialize the application
    public void initialize() {
        petImageIcon = new ImageIcon("data/catImage.PNG");
        Image image = petImageIcon.getImage();
        Image newimg = image.getScaledInstance(140, 120, java.awt.Image.SCALE_SMOOTH);
        petImageIcon = new ImageIcon(newimg);
        menuPanel = new JPanel();
        home = new Home();
        label = new JLabel("Welcome to Slimmer!");
        label.setFont(new Font("Algerian", Font.BOLD, 40));
        viewHomeButton = new JButton("View Home");
        addPetButton = new JButton("Add Pet");
        feedButton = new JButton("Feed");
        loadButton = new JButton("Load Previous Data");
        saveButton = new JButton("Save");
        removePetButton = new JButton("Remove Pet");
        viewWeeklyReport = new JButton("View Weekly Report");
        viewDailyReportButton = new JButton("View Daily Report");
        setButtonActionCommand();
        jsonReader = new JsonReader(JSON_STORE);
        jsonWriter = new JsonWriter(JSON_STORE);
        addButtonListener();
        field = new JTextField(1);
        field.setColumns(8);
    }

    // MODIFIES: this
    // EFFECTS: helper method to set up the button action command
    public void setButtonActionCommand() {
        viewHomeButton.setActionCommand("View Home");
        addPetButton.setActionCommand("Add Pet");
        feedButton.setActionCommand("Feed");
        loadButton.setActionCommand("Load Previous Data");
        saveButton.setActionCommand("Save");
        removePetButton.setActionCommand("Remove Pet");
        viewWeeklyReport.setActionCommand("View Weekly Report");
        viewDailyReportButton.setActionCommand("View Daily Report");
    }

    // MODIFIES: this
    // EFFECTS: helper method to set up the button listener
    public void addButtonListener() {
        loadButton.addActionListener(this);
        viewHomeButton.addActionListener(this);
        saveButton.addActionListener(this);
        addPetButton.addActionListener(this);
        feedButton.addActionListener(this);
        removePetButton.addActionListener(this);
        viewWeeklyReport.addActionListener(this);
        viewDailyReportButton.addActionListener(this);
    }

    // MODIFIES: this
    // EFFECTS: helper method to set up the layout for the menu panel
    public void setUpLayout() {
        menuPanel.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        menuPanel.add(label, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        menuPanel.add(field, gbc);
        buttonSetUp();
        add(menuPanel);
        setPreferredSize(new Dimension(600, 800));
    }

    // MODIFIES: this
    // EFFECTS: helper method to set up the buttons on the menu panel
    public void buttonSetUp() {
        gbc.gridx = 1;
        gbc.gridy = 1;
        menuPanel.add(addPetButton, gbc);
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 2;
        menuPanel.add(removePetButton, gbc);
        gbc.gridy = 3;
        menuPanel.add(viewHomeButton, gbc);
        gbc.gridy = 4;
        menuPanel.add(feedButton, gbc);
        gbc.gridy = 5;
        menuPanel.add(loadButton, gbc);
        gbc.gridy = 6;
        menuPanel.add(saveButton, gbc);
        gbc.gridy = 7;
        menuPanel.add(viewWeeklyReport, gbc);
        gbc.gridy = 8;
        menuPanel.add(viewDailyReportButton, gbc);
    }

    // EFFECTS: handle the action events when buttons are pressed
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (Arrays.asList("Load Previous Data", "View Home", "Save", "Add Pet", "Feed", "Remove Pet")
                .contains(command)) {
            menuActionPerformed(e);
        } else if (Arrays.asList("View", "View Daily", "Confirm Remove", "Confirm").contains(command)) {
            popUpActionPerformed(e);
        } else if (Arrays.asList("View Daily Report", "View Weekly Report").contains(command)) {
            reportActionPerformed(e);
        }

    }

    // EFFECTS: helper method to set up the ActionPerformed for the menu
    public void menuActionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Load Previous Data":
                load();
                break;
            case "View Home":
                viewHome();
                break;
            case "Save":
                save();
                break;
            case "Add Pet":
                addPet();
                break;
            case "Feed":
                feed();
                break;
            case "Remove Pet":
                removePet();
                break;
        }
    }

    // EFFECTS: helper method to set up the ActionPerformed for pop up windows
    public void popUpActionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "View":
                view();
                break;
            case "View Daily":
                viewDaily();
                break;
            case "Confirm Remove":
                confirmRemove();
                break;
            case "Confirm":
                confirm();
                break;
        }
    }

    // EFFECT: helper method to set up the ActionPerformed for report buttons
    public void reportActionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "View Weekly Report":
                viewWeeklyReport();
                break;
            case "View Daily Report":
                viewDailyReport();
                break;
        }
    }

    // REQUIRES: home cannot be empty
    // MODIFIES: this
    // EFFECTS: set up the remove frame and remove panel
    public void removePet() {
        removeFrameSetUp();
        removePanelSetUp();
    }

    // MODIFIES: this
    // EFFECTS: helper method to set up the remove frame
    public void removeFrameSetUp() {
        removeFrame = new JFrame("Remove Pet");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        removeFrame.setSize(400, 500);
        removeFrame.setLocationRelativeTo(this);
    }

    // MODIFIES: this
    // EFFECTS: helper method to set up the remove panel
    public void removePanelSetUp() {
        removePanel = new JPanel();
        confirmRemoveButton = new JButton("Confirm Remove");
        confirmRemoveButton.setActionCommand("Confirm Remove");
        confirmRemoveButton.addActionListener(this);

        removePanel.setLayout(new BoxLayout(removePanel, BoxLayout.Y_AXIS));
        removePanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        JLabel removLabel = new JLabel("Please select the pet you want to remove:");
        removePanel.add(removLabel);
        petComboBoxSetUp();
        removePanel.add(petComboBox);
        removePanel.add(confirmRemoveButton);
        removeFrame.add(removePanel);
        removeFrame.setVisible(true);
    }

    // REQUIRES: home is not empty, selectedPet is not null
    // MODIFIES: this, home
    // EFFECTS: remove the selected pet from home
    public void confirmRemove() {
        String petChoice = (String) petComboBox.getSelectedItem();
        selectedPet = home.getPet(petChoice);
        home.removePet(selectedPet);
    }

    // MODIFIES: this
    // EFFECTS: load home from data package
    public void load() {
        try {
            home = jsonReader.read();
            JLabel loadJLabel = new JLabel("Successfully loaded!");
            gbc.gridy = 12;
            menuPanel.add(loadJLabel, gbc);
            revalidate();
            repaint();
        } catch (IOException e) {
            JLabel noLoadJLabel = new JLabel("Not able to load!");
            gbc.gridy = 12;
            menuPanel.add(noLoadJLabel, gbc);
            revalidate();
            repaint();
        }
    }

    // MODIFIES: this
    // EFFECTS: set up the pop up window when the user click view weekly report
    // button
    public void viewWeeklyReport() {
        weeklyFrameSetUp();
        weeklyPanelSetUp();
    }

    // MODIFIES: this
    // EFFECTS: helper method to set up the weekly frame
    public void weeklyFrameSetUp() {
        viewFrame = new JFrame("Weekly Report");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        viewFrame.setSize(400, 500);
        viewFrame.setLocationRelativeTo(this);
    }

    // MODIFIES: this
    // EFFECTS: helper method to set up the weekly panel
    public void weeklyPanelSetUp() {
        viewPanel = new JPanel();
        viewButton = new JButton("View");
        viewButton.setActionCommand("View");
        viewButton.addActionListener(this);

        viewPanel.setLayout(new BoxLayout(viewPanel, BoxLayout.Y_AXIS));
        viewPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel viewLabel = new JLabel("Please select the pet you want to view weekly report:");
        viewPanel.add(viewLabel);

        petComboBoxSetUp();
        viewPanel.add(petComboBox);
        viewPanel.add(viewButton);
        viewFrame.add(viewPanel);
        viewFrame.setVisible(true);
    }

    // REQUIRES: home is not empty, selectedPet is not null
    // MODIFIES: this
    // EFFECTS: print out the weekly report for the selected pet
    public void view() {
        weeklyPrinterFrameSetUp();
        for (String day : days) {
            ArrayList<Food> dailyFoods = selectedPet.viewDailyFoods(day);
            if (dailyFoods.isEmpty()) {
                JLabel eatNothingJLabel = new JLabel(day + ": Alert! Ate nothing");
                weeklyPrinterPanel.add(eatNothingJLabel);
            } else {
                JLabel headerLabel = new JLabel(day + ": ");
                weeklyPrinterPanel.add(headerLabel);
                for (Food food : dailyFoods) {
                    JLabel dailyJLabel = new JLabel(
                            selectedPet.getPetName() + " ate " + food.getFoodName() + " by " + food.getFoodAmount()
                                    + "\n");
                    weeklyPrinterPanel.add(dailyJLabel);
                }
            }
            weeklyPrinterPanel.revalidate();
            weeklyPrinterPanel.repaint();
        }
    }

    // MODIFIES: this
    // EFFECTS: helper method to set up the weekly printer frame
    public void weeklyPrinterFrameSetUp() {
        String petChoice = (String) petComboBox.getSelectedItem();
        selectedPet = home.getPet(petChoice);
        weeklyPrinterFrame = new JFrame(petChoice + "'s weekly report");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        weeklyPrinterFrame.setSize(400, 500);
        weeklyPrinterFrame.setLocationRelativeTo(this);
        weeklyPrinterPanel = new JPanel();
        weeklyPrinterPanel.setLayout(new BoxLayout(weeklyPrinterPanel, BoxLayout.Y_AXIS));
        weeklyPrinterFrame.add(weeklyPrinterPanel);
        weeklyPrinterFrame.setVisible(true);
    }

    // REQUIRES: selctedPet and selectedDay are not null
    // MODIFIES: this
    // EFFECTS: print out the daily report according to the pet
    // and the day the user selected on the daily printer panel
    public void viewDaily() {
        dailyPrinterFrameSetUp();
        String petChoice = (String) petComboBox.getSelectedItem();
        selectedPet = home.getPet(petChoice);
        selectedDay = (String) dayComboBox.getSelectedItem();
        ArrayList<Food> dailyFoods = selectedPet.viewDailyFoods(selectedDay);
        if (dailyFoods.isEmpty()) {
            JLabel eatNothingJLabel = new JLabel(selectedDay + ": Alert! Ate nothing");
            dailyPrinterPanel.add(eatNothingJLabel, gbcDaily);
        } else {
            JLabel headerLabel = new JLabel(selectedDay + ": ");
            dailyPrinterPanel.add(headerLabel, gbcDaily);
            for (Food food : dailyFoods) {
                JLabel dailyJLabel = new JLabel(
                        selectedPet.getPetName() + " ate " + food.getFoodName() + " by " + food.getFoodAmount() + "\n");
                dailyPrinterPanel.add(dailyJLabel, gbcDaily);
            }
        }
        dailyPrinterPanel.revalidate();
        dailyPrinterPanel.repaint();
    }

    // MODIFIES: this
    // EFFECTS: helper method to set up the daily printer frame
    public void dailyPrinterFrameSetUp() {
        String petChoice = (String) petComboBox.getSelectedItem();
        selectedPet = home.getPet(petChoice);
        selectedDay = (String) dayComboBox.getSelectedItem();
        dailyPrinterFrame = new JFrame(petChoice + "'s " + selectedDay + " report");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        dailyPrinterFrame.setSize(400, 500);
        dailyPrinterFrame.setLocationRelativeTo(this);
        dailyPrinterPanel = new JPanel();
        dailyPrinterPanel.setLayout(new BoxLayout(dailyPrinterPanel, BoxLayout.Y_AXIS));
        dailyPrinterFrame.add(dailyPrinterPanel);
        dailyPrinterFrame.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: set up the pop up windows for view daily report
    public void viewDailyReport() {
        dailyFrameSetUp();
        dailyPanelSetUp();
    }

    // MODIFIES: this
    // EFFECTS: helper method to set up the daily frame
    public void dailyFrameSetUp() {
        dailyReportFrame = new JFrame("Daily Report");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        dailyReportFrame.setSize(400, 500);
        dailyReportFrame.setLocationRelativeTo(this);

    }

    // MODIFIES: this
    // EFFECTS: helper method to set up the daily panel
    public void dailyPanelSetUp() {
        dailyReportPanel = new JPanel(new GridBagLayout());
        viewDailyButton = new JButton("View Daily");
        viewDailyButton.setActionCommand("View Daily");
        viewDailyButton.addActionListener(this);
        gbcDaily = new GridBagConstraints();
        gbcDaily.fill = GridBagConstraints.HORIZONTAL;
        gbcDaily.insets = new Insets(5, 5, 5, 5);
        dailyReportPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        gbcDaily.gridx = 0;
        gbcDaily.gridy = 0;
        JLabel viewLabel = new JLabel("Please select the pet you want to view daily report:");
        dailyReportPanel.add(viewLabel, gbcDaily);
        petComboBoxSetUp();
        dayComboBox = new JComboBox<>(days);
        gbcDaily.gridy = 1;
        dailyReportPanel.add(petComboBox, gbcDaily);
        gbcDaily.gridy = 2;
        dailyReportPanel.add(dayComboBox, gbcDaily);
        gbcDaily.gridy = 3;
        dailyReportPanel.add(viewDailyButton, gbcDaily);
        dailyReportFrame.add(dailyReportPanel);
        dailyReportFrame.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: displays all the pets at home
    public void viewHome() {
        ArrayList<Pet> pets = home.viewHome();
        if (!pets.isEmpty()) {
            homeFrameSetUp();
            for (Pet pet : pets) {
                JLabel petLabel = new JLabel(pet.getPetName());
                homePanel.add(petLabel);
                JLabel petImageLabel = new JLabel(petImageIcon);
                homePanel.add(petImageLabel);
            }
            homeFrame.add(homePanel);
            homeFrame.setVisible(true);
        } else {
            gbc.gridy = 11;
            JLabel noPetJLabel = new JLabel("No pets at home, please add a pet first.");
            menuPanel.add(noPetJLabel, gbc);
            revalidate();
            repaint();
        }
    }

    // MODIFIES: this
    // EFFECTS: helper method to set up the home frame
    public void homeFrameSetUp() {
        homeFrame = new JFrame("Home");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        homeFrame.setSize(600, 600);
        homeFrame.setLocationRelativeTo(this);
        homePanel = new JPanel();
        homePanel.setLayout(new BoxLayout(homePanel, BoxLayout.Y_AXIS));
        homePanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        JLabel homeLabel = new JLabel("You have these pets at home:");
        homePanel.add(homeLabel);
    }

    // MODIFIES: this
    // EFFECTS: helper method to set up the feed frame
    public void feedFrameSetUp() {
        feedFrame = new JFrame("Feed Pets");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        feedFrame.setSize(400, 500);
        feedFrame.setLocationRelativeTo(this);
    }

    // MODIFIES: this
    // EFFECTS: helper method to set up the pet combo box
    public void petComboBoxSetUp() {
        ArrayList<Pet> pets = home.viewHome();
        petComboBox = new JComboBox<>();
        for (Pet pet : pets) {
            petComboBox.addItem(pet.getPetName());
        }
    }

    // MODIFIES: this
    // EFFECTS: helper method to set up the feedPanel
    public void feedPanelSetUp() {
        feedPanel = new JPanel();
        feedPanel.setLayout(new BoxLayout(feedPanel, BoxLayout.Y_AXIS));
        feedPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        JLabel petLabel = new JLabel("Please select the pet you want to feed:");
        feedPanel.add(petLabel);
        petComboBoxSetUp();
        whatFood = new JLabel("What food do you want to feed?");
        whatFoodField = new JTextField();
        howMuchField = new JTextField();
        howMuchFood = new JLabel("By how much?");
        dateLabel = new JLabel("What day is it today?");
        confirmButton = new JButton("Confirm");
        dayComboBox = new JComboBox<>(days);
        feedPanel.add(petComboBox);
        feedPanel.add(dateLabel);
        feedPanel.add(dayComboBox);
        feedPanel.add(whatFood);
        feedPanel.add(whatFoodField);
        feedPanel.add(howMuchFood);
        feedPanel.add(howMuchField);
        feedPanel.add(confirmButton);
        feedFrame.add(feedPanel);
        feedFrame.setVisible(true);
    }

    // EFFECT: set up the popped up feedFrame when pets at home is not empty
    // print out instructions otherwise.
    public void feed() {
        ArrayList<Pet> pets = home.viewHome();
        if (!pets.isEmpty()) {
            feedFrameSetUp();
            feedPanelSetUp();
            confirmButton.setActionCommand("Confirm");
            confirmButton.addActionListener(this);
        } else {
            gbc.gridy = 11;
            JLabel noPetJLabel = new JLabel("No pets at home, please add a pet first.");
            menuPanel.add(noPetJLabel, gbc);
            revalidate();
            repaint();
        }
    }

    // REQUIRES: selectedPet, selectedDay, foodName, foodAmount are not null
    // MODIFIES: this, selectedPet
    // EFFECTS: feed the selectedPet by the given amount
    private void confirm() {
        String petChoice = (String) petComboBox.getSelectedItem();
        selectedPet = home.getPet(petChoice);
        selectedDay = (String) dayComboBox.getSelectedItem();
        String foodName = whatFoodField.getText();
        Integer foodAmount = Integer.parseInt(howMuchField.getText());
        Food food = new Food(foodName, 0, selectedDay);
        selectedPet.eatFood(food, foodAmount);
        JOptionPane.showMessageDialog(feedFrame, "Successfully fed " + selectedPet.getPetName() + "!");
    }

    // EFFECTS: save the home to file
    public void save() {
        try {
            jsonWriter.open();
            jsonWriter.write(home);
            jsonWriter.close();
            gbc.gridy = 9;
            JLabel saveLabel = new JLabel("Successfully saved!");
            menuPanel.add(saveLabel, gbc);
            revalidate();
            repaint();
        } catch (FileNotFoundException e) {
            gbc.gridy = 10;
            JLabel cannotSave = new JLabel("File not found!");
            menuPanel.add(cannotSave, gbc);
            revalidate();
            repaint();
        }
    }

    // MODIFIES: this
    // EFFECTS: add a pet to home
    private void addPet() {
        if (field.getText() != "") {
            ArrayList<Pet> pets = home.viewHome();
            ArrayList<String> petNames = new ArrayList<>();
            for (Pet p : pets) {
                petNames.add(p.getPetName());
            }
            if (!petNames.contains(field.getText())) {
                Pet pet = new Pet(field.getText());
                home.addPet(pet);
            }
        }
    }

    // starts the application
    public static void main(String[] args) {
        new SlimmerUI();
    }
}
