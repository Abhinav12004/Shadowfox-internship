import java.util.ArrayList;
import java.util.Scanner;

// Class representing a Contact
class Contact {
    String name;
    String phoneNumber;
    String email;

    // Constructor to initialize a contact
    public Contact(String name, String phoneNumber, String email) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }
}

public class Main {
    // List to store contacts
    static ArrayList<Contact> contacts = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        // Main loop to display menu and handle user choices
        do {
            System.out.println("\nContact Management System");
            System.out.println("1. Add Contact");
            System.out.println("2. View Contacts");
            System.out.println("3. Update Contact");
            System.out.println("4. Delete Contact");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            // Handle user choice
            switch (choice) {
                case 1:
                    addContact();
                    break;
                case 2:
                    viewContacts();
                    break;
                case 3:
                    updateContact();
                    break;
                case 4:
                    deleteContact();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);

        scanner.close();
    }

    // Method to add a new contact
    public static void addContact() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter phone number: ");
        String phoneNumber = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        contacts.add(new Contact(name, phoneNumber, email));
        System.out.println("Contact added successfully.");
    }

    // Method to view all contacts
    public static void viewContacts() {
        if (contacts.isEmpty()) {
            System.out.println("No contacts found.");
        } else {
            for (Contact contact : contacts) {
                System.out.println("Name: " + contact.name);
                System.out.println("Phone Number: " + contact.phoneNumber);
                System.out.println("Email: " + contact.email);
                System.out.println();
            }
        }
    }

    // Method to update an existing contact
    public static void updateContact() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the name of the contact to update: ");
        String name = scanner.nextLine();

        for (Contact contact : contacts) {
            if (contact.name.equals(name)) {
                System.out.print("Enter new phone number: ");
                contact.phoneNumber = scanner.nextLine();
                System.out.print("Enter new email: ");
                contact.email = scanner.nextLine();
                System.out.println("Contact updated successfully.");
                return;
            }
        }

        System.out.println("Contact not found.");
    }

    // Method to delete a contact
    public static void deleteContact() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the name of the contact to delete: ");
        String name = scanner.nextLine();

        for (Contact contact : contacts) {
            if (contact.name.equals(name)) {
                contacts.remove(contact);
                System.out.println("Contact deleted successfully.");
                return;
            }
        }

        System.out.println("Contact not found.");
    }
}