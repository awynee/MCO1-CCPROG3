import java.util.List;
import java.util.ArrayList;

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

    public void updateBasePrice(double newPrice) {
        this.basePrice = newPrice;
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

    public String getPropertyName() { return propertyName; }

    public double getBasePrice() { return basePrice; }

    public List<Integer> getAvailableDates() { return availableDates; }

    public List<Reservation> getReservations() {
        return reservations;
    }

}
