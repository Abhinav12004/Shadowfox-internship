package com.sample.javafx_demo;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

// Class representing a Student
class Student {
    private final SimpleStringProperty id;
    private final SimpleStringProperty name;
    private final SimpleIntegerProperty age;

    // Constructor to initialize a student
    public Student(String id, String name, int age) {
        this.id = new SimpleStringProperty(id);
        this.name = new SimpleStringProperty(name);
        this.age = new SimpleIntegerProperty(age);
    }

    // Getter and setter methods for id
    public SimpleStringProperty idProperty() {
        return id;
    }

    public void setId(String id) {
        this.id.set(id);
    }

    // Getter and setter methods for name
    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    // Getter and setter methods for age
    public SimpleIntegerProperty ageProperty() {
        return age;
    }

    public void setAge(int age) {
        this.age.set(age);
    }
}

// Main class for the Student Management System
public class StudentManagement extends Application {
    private TableView<Student> table;
    private ObservableList<Student> studentList;
    private TextField idField, nameField, ageField;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Student Information System");

        // Initialize the table and student list
        table = new TableView<>();
        studentList = FXCollections.observableArrayList();
        table.setItems(studentList);

        // Define table columns
        TableColumn<Student, String> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        TableColumn<Student, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        TableColumn<Student, Number> ageColumn = new TableColumn<>("Age");
        ageColumn.setCellValueFactory(cellData -> cellData.getValue().ageProperty());

        // Add columns to the table
        table.getColumns().addAll(idColumn, nameColumn, ageColumn);

        // Create a form for input fields
        GridPane form = new GridPane();
        form.setPadding(new Insets(10));
        form.setVgap(8);
        form.setHgap(10);

        // Initialize input fields
        idField = new TextField();
        nameField = new TextField();
        ageField = new TextField();

        // Add input fields and labels to the form
        form.add(new Label("ID:"), 0, 0);
        form.add(idField, 1, 0);
        form.add(new Label("Name:"), 0, 1);
        form.add(nameField, 1, 1);
        form.add(new Label("Age:"), 0, 2);
        form.add(ageField, 1, 2);

        // Create buttons for add, update, and delete actions
        Button addButton = new Button("Add");
        Button updateButton = new Button("Update");
        Button deleteButton = new Button("Delete");

        // Set button actions
        addButton.setOnAction(e -> addStudent());
        updateButton.setOnAction(e -> updateStudent());
        deleteButton.setOnAction(e -> deleteStudent());

        // Create a VBox layout and add form, buttons, and table to it
        VBox vbox = new VBox(10, form, addButton, updateButton, deleteButton, table);
        vbox.setPadding(new Insets(10));

        // Set the scene and show the stage
        Scene scene = new Scene(vbox, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Method to add a new student
    private void addStudent() {
        String id = idField.getText();
        String name = nameField.getText();
        int age = Integer.parseInt(ageField.getText());

        // Add the new student to the list
        studentList.add(new Student(id, name, age));
        clearFields();
    }

    // Method to update an existing student
    private void updateStudent() {
        Student selectedStudent = table.getSelectionModel().getSelectedItem();
        if (selectedStudent != null) {
            String id = idField.getText();
            String name = nameField.getText();
            String ageText = ageField.getText();

            // Check if the age field is empty
            if (ageText.isEmpty()) {
                showAlert("Age field cannot be empty.");
                return;
            }

            try {
                // Try to parse the age text to an integer
                int age = Integer.parseInt(ageText);

                // Update the selected student's details
                selectedStudent.setId(id);
                selectedStudent.setName(name);
                selectedStudent.setAge(age);

                // Refresh the table to show updated details
                table.refresh();
                clearFields();
            } catch (NumberFormatException e) {
                // Show an alert if the age text is not a valid number
                showAlert("Please enter a valid number for age.");
            }
        } else {
            // Show an alert if no student is selected for update
            showAlert("Please select a student to update.");
        }
    }

    // Method to delete a student
    private void deleteStudent() {
        Student selectedStudent = table.getSelectionModel().getSelectedItem();
        if (selectedStudent != null) {
            // Remove the selected student from the list
            studentList.remove(selectedStudent);
            clearFields();
        } else {
            // Show an alert if no student is selected for deletion
            showAlert("Please select a student to delete.");
        }
    }

    // Method to clear input fields
    private void clearFields() {
        idField.clear();
        nameField.clear();
        ageField.clear();
    }

    // Method to show an alert with a given message
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(message);
        alert.showAndWait();
    }
}