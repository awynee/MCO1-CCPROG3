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

    public void addProperty(PropertyListing property) {
        properties.add(property);
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
            System.out.println("No properties currently managed.\n");
            return;
        }

        for (int i = 0; i < properties.size(); i++) {
            PropertyListing p = properties.get(i);
            System.out.println((i + 1) + ". " + p.getPropertyName());
        }
        System.out.println("X. Return to Menu");
        System.out.println();
        System.out.print("Enter Number to View Property Details: ");
        String input = scanner.nextLine();

        if (input.equalsIgnoreCase("X")) {
            System.out.println("Returning to main menu...\n");
            return;
        }

        if (!input.matches("\\d+")) {
            System.out.println("Invalid input. Please enter a valid number or 'X' to return.\n");
            return;
        }

        int choice = Integer.parseInt(input);

        if (choice < 1 || choice > properties.size()) {
            System.out.println("Invalid property number.\n");
            return;
        }

        PropertyListing selectedProperty = properties.get(choice - 1);

        boolean viewing = true;
        while (viewing) {
            menu.displayViewPropertyMenu(selectedProperty.getPropertyName());

            System.out.print("Enter your choice: ");
            String viewChoice = scanner.nextLine();

            switch (viewChoice) {
                case "1":
                    selectedProperty.displayCalendarView();
                    break;
                case "2":
                    selectedProperty.displayHighLevelPropertyInfo();
                    break;
                case "3":
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
                        System.out.print("Enter end day (" + startDay + "to 30): ");
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
                case "4":
                    break;
                case "5":
                    viewing = false;
                    break;

                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    public List<PropertyListing> getProperties() {
        return properties;
    }
}
