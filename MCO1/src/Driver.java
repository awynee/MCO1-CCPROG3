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


                    menu.displayManagePropertyMenu();
                    choice = menu.getUserChoice(scanner);
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
                    System.out.println("\nInvalid Option! Please try again!\n");
            }
        }while(choice != 4);
    }
}