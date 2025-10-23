import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class PropertyListing {
    private String propertyName;
    private double basePrice;
    private List<Integer> availableDates;
    private List<Reservation> reservations;

    public PropertyListing(String propertyName) {
        this(propertyName, 1500);
    }

    public PropertyListing(String propertyName, double basePrice) {
        this.propertyName = propertyName;
        this.basePrice = basePrice;
        this.availableDates = new ArrayList<>();
        this.reservations = new ArrayList<>();

        for (int i = 1; i <= 30; i++) {
            availableDates.add(i);
        }
    }

    public void addReservation(int fromDay, int toDay, String guestName) {
        boolean dayTaken = false;

        for (Reservation r : reservations) {
            if (!(toDay < r.getFromDay() || fromDay > r.getToDay())) {
                dayTaken = true;
                break;
            }
        }

        if (dayTaken) {
            System.out.println("Error: Days " + fromDay + " to " + toDay + " are already booked!");
        }
        else {
            for (int day = fromDay; day <= toDay; day++) {
                availableDates.remove(Integer.valueOf(day));
            }

            int totalDays = toDay - fromDay + 1;
            double totalPrice = totalDays * basePrice;
            Guest guest = new Guest(guestName);
            Reservation newReservation = new Reservation(fromDay, toDay, totalPrice, guest);
            reservations.add(newReservation);

            System.out.println("Reservation confirmed for " + guestName + " from day " + fromDay + " to " + toDay);
        }
    }


    public void changePropertyName(String newName) {
        this.propertyName = newName;
    }


    public void displayPropertyInfo() {
        System.out.println("Property Name: " + this.propertyName);
        System.out.println("Base Price: " + this.basePrice);
        System.out.println("Available Dates: " + this.availableDates);
        System.out.println("Number of Reservations: " + this.reservations.size());
    }

    public void displayHighLevelPropertyInfo() {
        double estimatedEarning = 0;
        for (Reservation r : reservations) {
            estimatedEarning += r.getTotalPrice();
        }

        System.out.println("\nProperty Name: " + this.propertyName);
        System.out.println("Number of Available Dates: " + this.availableDates.size());
        System.out.println("Estimated Earnings for the Month: " + estimatedEarning);
    }

    public void displayCalendarView() {
        System.out.println("\nCalendar View:");

        for (int day = 1; day <= 30; day++) {
            String status = "Bookable";

            if (availableDates.contains(day)) {
                status = "Unavailable";
            }

            for (Reservation r : reservations) {
                if (day >= r.getFromDay() && day <= r.getToDay()) {
                    status = "Booked (" + r.getGuest().getGuestName() + ")";
                    break;
                }
            }

            System.out.printf(day + ": " + status + "\t");

            if (day % 5 == 0) {
                System.out.println();
            }
        }
        System.out.println();
    }

    public void displayDetailedPropertyInfo(int startDay, int endDay) {
        int available = 0;
        int booked = 0;

        for (int day = startDay; day <= endDay; day++) {
            boolean isBooked = false;

            for (Reservation r : reservations) {
                if (day >= r.getFromDay() && day <= r.getToDay()) {
                    isBooked = true;
                    break;
                }
            }
            if (availableDates.contains(day) && !isBooked) {
                available++;
            } else if (isBooked) {
                booked++;
            }
        }

        System.out.println("\nDays " + startDay + " to " + endDay + ":");
        System.out.println("Available Dates: " + available);
        System.out.println("Booked Dates: " + booked);
    }

    public void displayDateInfo(int day) {
        String status = "Unavailable";

        for (Reservation r : reservations) {
            if (day >= r.getFromDay() && day <= r.getToDay()) {
                status = "Booked (" + r.getGuest().getGuestName() + ")";
                break;
            }
        }

        if (availableDates.contains(day) && !status.startsWith("Booked")) {
            status = "Available";
        }

        System.out.println("Day " + day);
        System.out.println("Price: " + this.basePrice);
        System.out.println("Status: " + status);
    }

    public void changePropertyName(List<PropertyListing> allProperties){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter new Property Name: ");
        String newPropertyName = scanner.nextLine();

        //Checks for blank names
        if (newPropertyName.isEmpty()){
            System.out.println("Property cannot have a blank name!");
            return;
        }
        //Checks for duplicate property names
        for(PropertyListing property : allProperties){
            if(property.getPropertyName().equalsIgnoreCase(newPropertyName)){
                System.out.println("Property already exsists. Please choose another name");
            }
        }

        System.out.println("Changed Property name from " + this.propertyName + " to " + newPropertyName);
        this.setPropertyName(newPropertyName);
    }

    //CHECKS FOR ACTIVE RESERVATIONS
    public boolean hasActiveReservations() {
        return reservations != null && !reservations.isEmpty();
    }

    public boolean updateBasePrice(double newPrice) {
        // Check if new price is valid
        if (newPrice < 100.00) {
            System.out.println("Base price must be at least ₱100.00.");
            return false;
        }

        // Check if there are any active reservations
        if (hasActiveReservations()) {
            System.out.println("Cannot update base price while there are existing reservations.");
            return false;
        }

        // Update base price
        this.basePrice = newPrice;
        System.out.printf("Base price successfully updated to ₱%.2f%n", newPrice);
        return true;
    }



    public void removeReservationByDay(int day) {
        if (reservations == null || reservations.isEmpty()) {
            System.out.println("No reservations found for this property.");
            return;
        }

        // Return all reserved days back to availableDates
        for (Reservation r : reservations) {
            for (int d = r.getFromDay(); d <= r.getToDay(); d++) {
                if (!availableDates.contains(d)) {
                    availableDates.add(d);
                }
            }
        }
        // Sort available dates after restoring them
        availableDates.sort(Integer::compareTo);

        // Clear all reservations
        reservations.clear();
        System.out.println("All reservations for this property have been removed successfully.");
    }


    public String getPropertyName() { return propertyName; }

    public double getBasePrice() { return basePrice; }

    public List<Integer> getAvailableDates() { return availableDates; }

    public List<Reservation> getReservations() {
        return reservations;
    }

    //setter
    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

}
