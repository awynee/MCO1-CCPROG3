import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {
        Menu menu = new Menu();
        Scanner scanner = new Scanner(System.in);
        PropertyListing propertyListing = null;
        ManageProperty manageProperty = new ManageProperty();
        int choice = 0;
        String input = "0";

        do{
            menu.displayMainMenu();
            choice = menu.getUserChoice(scanner);

            switch(choice){
                case 1:
                    System.out.println("\nYou Chose: " + choice);
                    menu.clearScreen();
                    System.out.println("=== CREATING PROPERTY ===");
                    System.out.print("Enter Property Name: ");
                    scanner.nextLine();
                    String propertyName = scanner.nextLine();

                    propertyListing = new PropertyListing(propertyName);
                    if(manageProperty.addProperty(propertyListing)){
                        System.out.println("Property '" + propertyName + "' created successfully!\n");
                        menu.pause(1000);
                        menu.clearScreen();
                    }
                    else{
                        System.out.println("Property '" + propertyName + "' already exists!\n");
                        menu.pause(1000);
                        menu.clearScreen();
                    }

                    break;
                case 2:
                    System.out.println("\nYou Chose: " + choice + "\n");
                    menu.clearScreen();

                    int manageChoice = 0;
                    do {
                        // Display submenu
                        menu.displayManagePropertyMenu();
                        manageChoice = menu.getUserChoice(scanner);
                        scanner.nextLine();
                        
                        menu.clearScreen();

                        switch (manageChoice) {
                            case 1:
                                // Change Property Name
                                if (manageProperty.getProperties().isEmpty()) {
                                    System.out.println("No properties available. Please create one first.\n");
                                    break;
                                }

                                choice = -1;
                                boolean returning = false;

                                do {
                                    manageProperty.displayAllProperties();
                                    System.out.print("Enter the Number of the Property to be Renamed (or X to return): ");
                                    input = scanner.next();

                                    // Check if user wants to return
                                    if (input.equalsIgnoreCase("x")) {
                                        System.out.println("Returning to main menu...\n");
                                        menu.pause(1000);
                                        menu.clearScreen();
                                        returning = true;
                                        break; // exits the method
                                    }

                                    // Validate numeric input
                                    if (!input.matches("\\d+")) {
                                        System.out.println("Invalid input. Please enter a valid number or 'X' to return.\n");
                                        menu.pause(1000);
                                        menu.clearScreen();
                                        continue;
                                    }

                                    choice = Integer.parseInt(input);

                                    if (choice < 1 || choice > manageProperty.getAllProperties()) {
                                        System.out.println("Invalid property number. Please try again.\n");
                                        menu.pause(1000);
                                        menu.clearScreen();
                                    }

                                } while (choice < 1 || choice > manageProperty.getAllProperties());

                                if(!returning){
                                    PropertyListing selectedProperty = manageProperty.getPropertyByIndex(choice - 1);
                                    if (selectedProperty != null) {
                                        selectedProperty.changePropertyName(manageProperty.getProperties());
                                        manageProperty.displayUpdatedProperties();
                                        menu.pause(1500);
                                        menu.clearScreen();
                                    } else {
                                        System.out.println("Invalid property number.\n");
                                    }
                                }
                                break;

                            case 2:
                                // === UPDATE BASE PRICE ===
                                if (manageProperty.getProperties().isEmpty()) {
                                    System.out.println("No properties available. Please create one first.\n");
                                    break;
                                }

                                choice = -1;
                                returning = false;
                                
                                do {
                                    manageProperty.displayAllProperties();
                                    System.out.print("Enter the number of the property to update base price (or X to return): ");
                                    input = scanner.next();

                                    // Check if user wants to return
                                    if (input.equalsIgnoreCase("x")) {
                                        System.out.println("Returning to main menu...\n");
                                        menu.pause(1000);
                                        menu.clearScreen();
                                        returning = true;
                                        break; // exits the method
                                    }

                                    // Validate numeric input
                                    if (!input.matches("\\d+")) {
                                        System.out.println("Invalid input. Please enter a valid number or 'X' to return.\n");
                                        menu.pause(1000);
                                        menu.clearScreen();
                                        continue;
                                    }

                                    choice = Integer.parseInt(input);

                                    if (choice < 1 || choice > manageProperty.getAllProperties()) {
                                        System.out.println("Invalid property number. Please try again.\n");
                                        menu.pause(1000);
                                        menu.clearScreen();
                                    }

                                } while (choice < 1 || choice > manageProperty.getAllProperties());

                                if(!returning){
                                    PropertyListing propertyToUpdate = manageProperty.getPropertyByIndex(choice - 1);
                                    double newPrice = 0;
                                    if (propertyToUpdate != null) {
                                        if(propertyToUpdate.hasActiveReservations()){
                                            System.out.println("Cannot update base price while there are existing reservations.\n");
                                            menu.pause(1000);
                                            menu.clearScreen();
                                        }
                                        else{
                                            System.out.print("Enter new base price: P");
                                            newPrice = scanner.nextDouble();
                                            scanner.nextLine(); // consume newline
                                            propertyToUpdate.updateBasePrice(newPrice);
                                            menu.pause(1000);
                                            menu.clearScreen();
                                        }
 
                                    } else {
                                        menu.pause(1000);
                                        menu.clearScreen();
                                        System.out.println("Invalid property number.\n");
                                    }
                                }
                                break;
                            case 3:
                                // === REMOVE ALL RESERVATIONS ===
                                if (manageProperty.getProperties().isEmpty()) {
                                    System.out.println("No properties available. Please create one first.\n");
                                    break;
                                }

                                choice = -1;
                                returning = false;

                                do {
                                    manageProperty.displayAllProperties();
                                    System.out.print("Enter the number of the property to remove reservations (or X to return): ");
                                    input = scanner.next();

                                    // Check if user wants to return
                                    if (input.equalsIgnoreCase("x")) {
                                        System.out.println("Returning to main menu...\n");
                                        menu.pause(1000);
                                        menu.clearScreen();
                                        returning = true;
                                        break; // exits the method
                                    }

                                    // Validate numeric input
                                    if (!input.matches("\\d+")) {
                                        System.out.println("Invalid input. Please enter a valid number or 'X' to return.\n");
                                        menu.pause(1000);
                                        menu.clearScreen();
                                        continue;
                                    }

                                    choice = Integer.parseInt(input);

                                    if (choice < 1 || choice > manageProperty.getAllProperties()) {
                                        System.out.println("Invalid property number. Please try again.\n");
                                        menu.pause(1000);
                                        menu.clearScreen();
                                    }

                                } while (choice < 1 || choice > manageProperty.getAllProperties());
                                
                                if(!returning){
                                    PropertyListing propertyForReservation = manageProperty.getPropertyByIndex(choice - 1);
                                    if (propertyForReservation != null) {
                                        propertyForReservation.removeReservationByDay(0);
                                        menu.pause(1000);
                                        menu.clearScreen();
                                        // 'day' parameter is ignored now, since method removes all reservations
                                    } else {
                                        System.out.println("Invalid property number.\n");
                                        menu.pause(1000);
                                        menu.clearScreen();
                                    }
                                }
                                break;

                            case 4:
                                choice = -1;
                                returning = false;

                                
                                do {
                                    manageProperty.displayAllProperties();
                                    System.out.print("Enter the number of the property to remove reservations (or X to return): ");
                                    input = scanner.next();

                                    // Check if user wants to return
                                    if (input.equalsIgnoreCase("x")) {
                                        System.out.println("Returning to main menu...\n");
                                        menu.pause(1000);
                                        menu.clearScreen();
                                        returning = true;
                                        break; // exits the method
                                    }

                                    // Validate numeric input
                                    if (!input.matches("\\d+")) {
                                        System.out.println("Invalid input. Please enter a valid number or 'X' to return.\n");
                                        menu.pause(1000);
                                        menu.clearScreen();
                                        continue;
                                    }

                                    choice = Integer.parseInt(input);

                                    if (choice < 1 || choice > manageProperty.getAllProperties()) {
                                        System.out.println("Invalid property number. Please try again.\n");
                                        menu.pause(1000);
                                        menu.clearScreen();
                                    }

                                } while (choice < 1 || choice > manageProperty.getAllProperties());

                                if(!returning){
                                    if (choice < 1 || choice > manageProperty.getProperties().size()) {
                                        System.out.println("Invalid property number.\n");
                                        menu.pause(1000);
                                        menu.clearScreen();
                                    } else {
                                        PropertyListing removed = manageProperty.getProperties().remove(choice - 1);
                                        System.out.println("Property '" + removed.getPropertyName() + "' has been removed successfully.\n");
                                        menu.pause(1000);
                                        menu.clearScreen();
                                    }
                                }
                                break;

                            case 5:
                                System.out.println("Returning to main menu...\n");
                                menu.pause(1000);
                                menu.clearScreen();
                                break;

                            default:
                                System.out.println("Invalid choice. Please try again.\n");
                        }
                    } while (manageChoice != 5);
                    break;

                case 3:
                    System.out.println("\nYou Chose: " + choice + "\n");
                    manageProperty.displayAllProperties();
                    manageProperty.propertyMenuChoice();
                    break;
                case 4:
                    System.out.println("Exiting program...");
                    break;
                default:
                    System.out.println("\nInvalid Option. Please try again.\n");
            }
        }while(choice != 4);
    }
}