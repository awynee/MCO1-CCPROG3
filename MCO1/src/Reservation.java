public class Reservation {
    private int day;
    private double totalPrice;
    private Guest guest;

    public Reservation(int day, double totalPrice, Guest guest) {
        this.day = day;
        this.totalPrice = totalPrice;
        this.guest = guest;
    }

    public int getDay() {
        return day;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public Guest getGuest() {
        return guest;
    }

    public void displayReservationInfo() {
        System.out.println("Reservation day: " + day);
        System.out.println("Total price: " + totalPrice);
        System.out.println("Guest: " + guest.getGuestName());
    }
}
