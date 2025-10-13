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
                    String propertyName = scanner.next();

                    propertyListing = new PropertyListing(propertyName);
                    manageProperty.addProperty(propertyListing);
                    System.out.println("Property '" + propertyName + "' created successfully!\n");
                    break;
                case 2:
                    System.out.println("you chose " + choice);
                    break;
                case 3:
                    System.out.println("\nYou Chose: " + choice + "\n");
                    manageProperty.displayAllProperties();
                    break;
                default:
                    System.out.println("\nInvalid Option! Please try again!\n");
            }
        }while(choice != 4);
    }
}