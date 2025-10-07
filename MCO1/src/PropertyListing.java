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

    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
    }

    public void displayPropertyInfo(){
        System.out.println("Property Name: " + this.propertyName);
        System.out.println("Base Price: " + this.basePrice);
        System.out.println("Available Dates: " + this.availableDates);
        System.out.println("Reserved Dates: " + this.reservations.size());
    }

    public void displayHighLevelPropertyInfo(){
        double estimatedEarning = 0;
        System.out.println("Property Name: " + this.propertyName);
        System.out.println("Number of Available Dates: " + this.availableDates.size());
        System.out.println("Estimated Earnings for the Month: " + estimatedEarning);
    }

    public void displayCalendarView(){
        System.out.println("Calendar View:");
        for(int day = 1; day <= 30; day++){
            String status;
            if(availableDates.contains(day)){
                status = "Available";
            }
            else{
                status = "Unavailable";
            }

            System.out.printf(day + ": " + status + "\t");

            if(day % 5 == 0){
                System.out.println();
            }
        }
        System.out.println();
    }

    public void displayDetailedPropertyInfo(){
    }

    public void displayDateInfo(){
    }

    public void displayReservationInfo(){
    }

    public String getPropertyName() { return propertyName; }

    public double getBasePrice() { return basePrice; }

    public List<Integer> getAvailableDates() { return availableDates; }


}
