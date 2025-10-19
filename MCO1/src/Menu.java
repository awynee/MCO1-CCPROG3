import java.util.Scanner;

public class Menu {
    public void displayMainMenu() {
        System.out.println("=== MAIN MENU ===");
        System.out.println("1. Create Property");
        System.out.println("2. Manage Properties");
        System.out.println("3. View Properties");
        System.out.println("4. Exit");
    }

    public int getUserChoice(Scanner scanner) {
        System.out.print("Enter choice: ");
        return scanner.nextInt();
    }

    public void displayViewPropertyMenu(String propertyName){
        System.out.println("\n=== VIEW PROPERTY MENU ===");
        System.out.println("Currently Viewing: " + propertyName);
        System.out.println("1. Calendar View");
        System.out.println("2. High Level Property Information");
        System.out.println("3. Detailed Information");
        System.out.println("4. Book Property");
        System.out.println("5. Return to Menu");
    }

    public void displayManagePropertyMenu(){
        System.out.println("=== MANAGE PROPERTY MENU ===");
        System.out.println("1. Change Property Name");
        System.out.println("2. Update Property Base Price per Night");
        System.out.println("3. Remove Property Reservations");
        System.out.println("4. Remove Property");
        System.out.println("5. Return to Menu");
    }
}
