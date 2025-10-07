import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter property name: ");
        String propertyName = input.nextLine();

        System.out.print("Enter Base price (default 1500, enter if no specific price): ");
        String priceInput = input.nextLine();

        PropertyListing property;

        //checks if user inputs a price or not
        if (priceInput.isEmpty()) {
            property = new PropertyListing(propertyName); // Default base price
        } else {
            double basePrice = Double.parseDouble(priceInput);//new price oif not base 1500
            property = new PropertyListing(propertyName, basePrice);
        }

        //Asking for available dates
        System.out.print("How many available dates? (1–30): ");
        int totalDates = input.nextInt();

        while (totalDates < 1 || totalDates > 30) {
            System.out.print("Invalid date. Please enter between 1 and 30: ");
            totalDates = input.nextInt();
        }

        //adding available days for listing
        for (int i = 0; i < totalDates; i++) {
            System.out.print("Enter available day (1–30): ");
            int day = input.nextInt();
            property.addAvailableDate(day);
        }

        // Display confirmation and property info
        System.out.println("\nProperty created\n");
        property.displayPropertyInfo();

        //display calendar view
        property.displayCalendarView();

        input.close();
    }
}