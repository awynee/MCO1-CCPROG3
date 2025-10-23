import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class ManageProperty {
    private List<PropertyListing> properties;
    PropertyListing propertyListing = null;
    Menu menu = new Menu();

    public ManageProperty() {
        this.properties = new ArrayList<>();
    }

    public boolean addProperty(PropertyListing property) {
        boolean status = true;

        for(PropertyListing p : properties){
            if(property.getPropertyName().equalsIgnoreCase(p.getPropertyName())){
                status = false;
            }
        }

        if(status){
            properties.add(property);
        }

        return status;
    }


    public void changePropertyName(String oldName, String newName) {
        boolean found = false;

        for (int i = 0; i < properties.size(); i++) {
            PropertyListing p = properties.get(i);
            if (p.getPropertyName().equals(oldName)) {
                p.changePropertyName(newName);
                found = true;
            }
        }

        if (found) {
            System.out.println("Property name changed from '" + oldName + "' to '" + newName + "'.");
        } else {
            System.out.println("No property found with name '" + oldName + "'.");
        }
    }

    public void removeReservationByDay(PropertyListing property, int day) {
        boolean found = false;

        int indexToRemove = -1;

        List<Reservation> reservations = property.getReservations();

        for (int i = 0; i < reservations.size(); i++) {
            Reservation r = reservations.get(i);
            if (day >= r.getFromDay() && day <= r.getToDay()) {
                found = true;
                indexToRemove = i;
            }
        }

        if (found && indexToRemove >= 0) {
            reservations.remove(indexToRemove);
            System.out.println("Reservation for day " + day + " removed successfully from property '" + property.getPropertyName() + "'.");
        } else {
            System.out.println("No reservation found for day " + day + " in property '" + property.getPropertyName() + "'.");
        }
    }

    public void removeProperty(String propertyName) {
        boolean found = false;
        int indexToRemove = -1;

        for (int i = 0; i < properties.size(); i++) {
            PropertyListing p = properties.get(i);
            if (p.getPropertyName().equals(propertyName)) {
                found = true;
                indexToRemove = i;
            }
        }

        if (found && indexToRemove >= 0) {
            properties.remove(indexToRemove);
            System.out.println("Property '" + propertyName + "' removed successfully.");
        } else {
            System.out.println("No property found with name '" + propertyName + "'.\n");
        }
    }

    public void displayAllProperties() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== DISPLAY ALL PROPERTIES ===");
        if (properties.isEmpty()) {
            System.out.println("No properties currently managed.");
            System.out.println("X. Return to Menu\n");
            return;
        }

        for (int i = 0; i < properties.size(); i++) {
            PropertyListing p = properties.get(i);
            System.out.println((i + 1) + ". " + p.getPropertyName());
        }
        System.out.println("X. Return to Menu");
        System.out.println();
    }

    public int getAllProperties(){
        return properties.size();
    }

    public void propertyMenuChoice(){
        Scanner scanner = new Scanner(System.in);

        String input;
        int choice = -1;

        do {
            System.out.print("Enter Number to View Property Details (or X to return): ");
            input = scanner.nextLine();

            // Check if user wants to return
            if (input.equalsIgnoreCase("X")) {
                System.out.println("Returning to main menu...\n");
                return; // exits the method
            }

            // Validate numeric input
            if (!input.matches("\\d+")) {
                System.out.println("Invalid input. Please enter a valid number or 'X' to return.\n");
                continue;
            }

            choice = Integer.parseInt(input);

            if (choice < 1 || choice > properties.size()) {
                System.out.println("\nInvalid property number. Please try again.\n");
            }

        } while (choice < 1 || choice > properties.size());

        if (input.equalsIgnoreCase("X")) {
            System.out.println("Returning to main menu...\n");
        }

        PropertyListing selectedProperty = properties.get(choice - 1);

        int viewChoice = 0;
        do{
            menu.displayViewPropertyMenu(selectedProperty.getPropertyName());
            viewChoice = menu.getUserChoice(scanner);

            switch (viewChoice) {
                case 1:
                    selectedProperty.displayPropertyInfo();
                    break;
                case 2:
                    selectedProperty.displayCalendarView();
                    break;
                case 3:
                    selectedProperty.displayHighLevelPropertyInfo();
                    break;
                case 4:
                    int startDay = 0;
                    int endDay = 0;

                    while (true) {
                        System.out.print("\nEnter start day (1 to 30): ");
                        if (scanner.hasNextInt()) {
                            startDay = scanner.nextInt();
                            if (startDay >= 1 && startDay <= 30) break;
                        } else {
                            scanner.next();
                        }
                        System.out.println("Invalid start day! Try again.");
                    }

                    while (true) {
                        System.out.print("Enter end day (" + startDay + " to 30): ");
                        if (scanner.hasNextInt()) {
                            endDay = scanner.nextInt();
                            if (endDay >= startDay && endDay <= 30)
                                break;
                        } else {
                            scanner.next();
                        }
                        System.out.println("Invalid end day! Try again.");
                    }
                    scanner.nextLine();

                    selectedProperty.displayDetailedPropertyInfo(startDay, endDay);
                    break;
                case 5:
                    startDay = 0;
                    endDay = 0;

                    scanner.nextLine();
                    System.out.print("\nEnter Guest Name: ");
                    String guestName = scanner.nextLine();
                    Guest guest = new Guest(guestName);

                    while (true) {
                        System.out.print("Book day (1 to 30): ");
                        if (scanner.hasNextInt()) {
                            startDay = scanner.nextInt();
                            if (startDay >= 1 && startDay <= 30) break;
                        } else {
                            scanner.next();
                        }
                        System.out.println("Invalid start day! Try again.");
                    }

                    while (true) {
                        System.out.print("Until (" + startDay + " to 30): ");
                        if (scanner.hasNextInt()) {
                            endDay = scanner.nextInt();
                            if (endDay >= startDay && endDay <= 30)
                                break;
                        } else {
                            scanner.next();
                        }
                        System.out.println("Invalid end day! Try again.");
                    }
                    scanner.nextLine();

                    selectedProperty.addReservation(startDay, endDay, guestName);
                    break;
                case 6:
                    System.out.println("\nReturning to menu...\n");
                    break;
                default:
                    System.out.println("\nInvalid option. Please try again.");
            }
        }while(viewChoice != 6);
    }

    public List<PropertyListing> getProperties() {
        return properties;
    }

    public PropertyListing getPropertyByIndex(int index) {
        if (index >= 0 && index < properties.size()) {
            return properties.get(index);
        }
        return null;
    }
}



