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

class Student {
    private final SimpleStringProperty id;
    private final SimpleStringProperty name;
    private final SimpleIntegerProperty age;

    public Student(String id, String name, int age) {
        this.id = new SimpleStringProperty(id);
        this.name = new SimpleStringProperty(name);
        this.age = new SimpleIntegerProperty(age);
    }

    public SimpleStringProperty idProperty() {
        return id;
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public SimpleIntegerProperty ageProperty() {
        return age;
    }

    public void setAge(int age) {
        this.age.set(age);
    }
}

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

        table = new TableView<>();
        studentList = FXCollections.observableArrayList();
        table.setItems(studentList);

        TableColumn<Student, String> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        TableColumn<Student, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        TableColumn<Student, Number> ageColumn = new TableColumn<>("Age");
        ageColumn.setCellValueFactory(cellData -> cellData.getValue().ageProperty());

        table.getColumns().addAll(idColumn, nameColumn, ageColumn);
        GridPane form = new GridPane();
        form.setPadding(new Insets(10));
        form.setVgap(8);
        form.setHgap(10);

        idField = new TextField();
        nameField = new TextField();
        ageField = new TextField();

        form.add(new Label("ID:"), 0, 0);
        form.add(idField, 1, 0);
        form.add(new Label("Name:"), 0, 1);
        form.add(nameField, 1, 1);
        form.add(new Label("Age:"), 0, 2);
        form.add(ageField, 1, 2);

        Button addButton = new Button("Add");
        Button updateButton = new Button("Update");
        Button deleteButton = new Button("Delete");

        addButton.setOnAction(e -> addStudent());
        updateButton.setOnAction(e -> updateStudent());
        deleteButton.setOnAction(e -> deleteStudent());

        VBox vbox = new VBox(10, form, addButton, updateButton, deleteButton, table);
        vbox.setPadding(new Insets(10));

        Scene scene = new Scene(vbox, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void addStudent() {
        String id = idField.getText();
        String name = nameField.getText();
        int age = Integer.parseInt(ageField.getText());

        studentList.add(new Student(id, name, age));
        clearFields();
    }

    private void updateStudent() {
        Student selectedStudent = table.getSelectionModel().getSelectedItem();
        if (selectedStudent != null) {
            String id = idField.getText();
            String name = nameField.getText();
            String ageText = ageField.getText();
            if (ageText.isEmpty()) {
                showAlert("Age field cannot be empty.");
                return;
            }

            try {
                int age = Integer.parseInt(ageText);
                selectedStudent.setId(id);
                selectedStudent.setName(name);
                selectedStudent.setAge(age);
                table.refresh();
                clearFields();
            } catch (NumberFormatException e) {
                showAlert("Please enter a valid number for age.");
            }
        } else {
            showAlert("Please select a student to update.");
        }
    }

    private void deleteStudent() {
        Student selectedStudent = table.getSelectionModel().getSelectedItem();
        if (selectedStudent != null) {
            studentList.remove(selectedStudent);
            clearFields();
        } else {
            showAlert("Please select a student to delete.");
        }
    }

    private void clearFields() {
        idField.clear();
        nameField.clear();
        ageField.clear();
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
