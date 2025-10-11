import java.util.List;
import java.util.ArrayList;

public class PropertyListing {
    private String propertyName;
    private double basePrice;
    private List<Integer> availableDates;
    private List<Reservation> reservations;

    public PropertyListing(String propertyName) {
        this.propertyName = propertyName;
        basePrice = 1500;
        this.availableDates = new ArrayList<>();
        this.reservations = new ArrayList<>();
    }

    public PropertyListing(String propertyName, Double basePrice) {
        this.propertyName = propertyName;
        this.basePrice = basePrice;
        this.availableDates = new ArrayList<>();
        this.reservations = new ArrayList<>();
    }

    public boolean addAvailableDate(int day) {
        if(day < 1 || day > 30){
            System.out.println("Invalid day: Must be between 1 and 30.");
            return false;
        }
        if(availableDates.size() > 30){
            System.out.println("Must have a maximum of 30 available dates.");;
            return false;
        }
        if(availableDates.contains(day)){
            System.out.println("Day" + day + " is already available.");
            return false;
        }

        availableDates.add(day);
        return true;
    }

    public void updateBasePrice(double newPrice) {
        this.basePrice = newPrice;
    }

    public void addReservation(int fromDay, int toDay, double totalPrice, Guest guest) {
        boolean dayTaken = false;
        int i = 0;

        while (i < reservations.size()) {
            Reservation r = reservations.get(i);
            if ((day >= r.getFromDay() && day <= r.getToDay())) {
                dayTaken = true;
            }
            i++;
        }

        if (dayTaken) {
            System.out.println("Error: Day " + day + " is already booked!");
        } else {
            Reservation newReservation = new Reservation(fromDay, toDay, totalPrice, guest);
            reservations.add(newReservation);
            System.out.println("Reservation successfully added for day " + day);
        }
    }

    public void displayPropertyInfo(){
        System.out.println("Property Name: " + this.propertyName);
        System.out.println("Base Price: " + this.basePrice);
        System.out.println("Available Dates: " + this.availableDates);
        System.out.println("Reserved Dates: " + this.reservations.size());
    }

    public void displayHighLevelPropertyInfo(){
        double estimatedEarning = 0;
        for (Reservation r : reservations) {
            estimatedEarning += r.getTotalPrice();
        }
        System.out.println("Property Name: " + this.propertyName);
        System.out.println("Number of Available Dates: " + this.availableDates.size());
        System.out.println("Estimated Earnings for the Month: " + estimatedEarning);
    }

    public void displayCalendarView() {
        System.out.println("Calendar View:");

        for (int day = 1; day <= 30; day++) {
            String status = "Unavailable";
            boolean isBooked = false;

            if (availableDates.contains(day)) {
                status = "Available";
            }

            for (Reservation r : reservations) {
                if (day >= r.getFromDay() && day <= r.getToDay()) {
                    status = "Booked (" + r.getGuest().getGuestName() + ")";
                }
            }

            System.out.printf(day + ": " + status + "\t");

            if (day % 5 == 0) {
                System.out.println();
            }
        }
        System.out.println();
    }


    public void displayDetailedPropertyInfo(int startDay, int endDay){
        for (int day = startDay; day <= endDay; day++) {
            boolean isBooked = false;

            for (Reservation r : reservations) {
                if (day >= r.getFromDay() && day <= r.getToDay()) {
                    isBooked = true;
                }
            }

            if (availableDates.contains(day) && !isBooked) available++;
            else if (isBooked) booked++;
        }

        System.out.println("Days " + startDay + " to " + endDay + ":");
        System.out.println("Available Dates: " + available);
        System.out.println("Booked Dates: " + booked);
    }

    public void displayDateInfo(int day){
        String status;

        if(availableDates.contains(day)){
            status = "Available";
        }
        else{
            status = "Unavailable";
        }

        System.out.println("Day " + day);
        System.out.println("Price: " + this.basePrice);
        System.out.println("Status: " + status);
    }

    public String getPropertyName() { return propertyName; }

    public double getBasePrice() { return basePrice; }

    public List<Integer> getAvailableDates() { return availableDates; }
}
