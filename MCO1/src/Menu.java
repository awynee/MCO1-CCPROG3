import java.util.Scanner;

public class Menu {
    public void displayMainMenu() {
        System.out.println("=== MAIN MENU ===");
        System.out.println("1. Create Property");
        System.out.println("2. Reserve Property");
        System.out.println("3. Manage Properties");
        System.out.println("4. View Properties");
        System.out.println("5. Exit");
    }

    public int getUserChoice(Scanner scanner) {
        System.out.print("Enter choice: ");
        return scanner.nextInt();
    }
}
