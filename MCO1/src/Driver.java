import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {
        int choice = 0;
        Menu menu = new Menu();
        Scanner scanner = new Scanner(System.in);
        PropertyListing propertyListing = null;
        ManageProperty manageProperty = new ManageProperty();

        do{
            menu.displayMainMenu();
            choice = menu.getUserChoice(scanner);

            switch(choice){
                case 1:
                    System.out.println("\nYou Chose: " + choice);
                    System.out.println("\n=== CREATING PROPERTY ===");
                    System.out.print("Enter Property Name: ");
                    String propertyName = scanner.nextLine();
                    propertyName = scanner.nextLine();

                    propertyListing = new PropertyListing(propertyName);
                    manageProperty.addProperty(propertyListing);
                    System.out.println("Property '" + propertyName + "' created successfully!\n");
                    break;
                case 2:
                    System.out.println("\nYou Chose: " + choice + "\n");

                    int manageChoice;
                    do {
                        // Display submenu
                        menu.displayManagePropertyMenu();
                        manageChoice = menu.getUserChoice(scanner);

                        switch (manageChoice) {
                            case 1:
                                // Change Property Name
                                if (manageProperty.getProperties().isEmpty()) {
                                    System.out.println("No properties available. Please create one first.\n");
                                    break;
                                }

                                manageProperty.displayAllProperties();
                                System.out.print("Enter the number of the property you want to rename: ");
                                int index = scanner.nextInt();
                                scanner.nextLine(); // consume newline

                                PropertyListing selectedProperty = manageProperty.getPropertyByIndex(index - 1);
                                if (selectedProperty != null) {
                                    selectedProperty.changePropertyName(manageProperty.getProperties());
                                    System.out.println("\nUpdated Property List:");
                                    manageProperty.displayAllProperties();
                                } else {
                                    System.out.println("Invalid property number.\n");
                                }
                                break;

                            case 2:
                                // === UPDATE BASE PRICE ===
                                if (manageProperty.getProperties().isEmpty()) {
                                    System.out.println("No properties available. Please create one first.\n");
                                    break;
                                }

                                manageProperty.displayAllProperties();
                                System.out.print("Enter the number of the property to update base price: ");
                                int indexToUpdate = scanner.nextInt();
                                scanner.nextLine(); // consume newline

                                PropertyListing propertyToUpdate = manageProperty.getPropertyByIndex(indexToUpdate - 1);
                                if (propertyToUpdate != null) {
                                    System.out.print("Enter new base price: P");
                                    double newPrice = scanner.nextDouble();
                                    scanner.nextLine(); // consume newline

                                    propertyToUpdate.updateBasePrice(newPrice);
                                } else {
                                    System.out.println("Invalid property number.\n");
                                }
                                break;
                            case 3:
                                // === REMOVE ALL RESERVATIONS ===
                                if (manageProperty.getProperties().isEmpty()) {
                                    System.out.println("No properties available. Please create one first.\n");
                                    break;
                                }

                                manageProperty.displayAllProperties();
                                System.out.print("Enter the number of the property to clear all reservations: ");
                                int indexForReservation = scanner.nextInt();
                                scanner.nextLine(); // consume newline

                                PropertyListing propertyForReservation = manageProperty.getPropertyByIndex(indexForReservation - 1);
                                if (propertyForReservation != null) {
                                    propertyForReservation.removeReservationByDay(0);
                                    // 'day' parameter is ignored now, since method removes all reservations
                                } else {
                                    System.out.println("Invalid property number.\n");
                                }
                                break;

                            case 4:
                                manageProperty.displayAllProperties();
                                System.out.print("Enter the number of the property you want to remove: ");
                                int removeIndex = scanner.nextInt();
                                scanner.nextLine(); // consume newline

                                if (removeIndex < 1 || removeIndex > manageProperty.getProperties().size()) {
                                    System.out.println("Invalid property number.\n");
                                } else {
                                    PropertyListing removed = manageProperty.getProperties().remove(removeIndex - 1);
                                    System.out.println("Property '" + removed.getPropertyName() + "' has been removed successfully.\n");
                                }
                                break;

                            case 5:
                                System.out.println("Returning to main menu...\n");
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