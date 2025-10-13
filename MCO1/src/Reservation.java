public class Reservation {
    private int fromDay;
    private int toDay;
    private double totalPrice;
    private Guest guest;

    public Reservation(int fromDay, int toDay, double totalPrice, Guest guest) {
        this.fromDay = fromDay;
        this.toDay = toDay;
        this.totalPrice = totalPrice;
        this.guest = guest;
    }

    public int getFromDay() {
        return fromDay;}

    public int getToDay() { return toDay; }

    public double getTotalPrice() { return totalPrice; }

    public Guest getGuest() { return guest; }

    public void displayReservationInfo() {
        System.out.println("Reservation: Days " + fromDay + " to " + toDay);
        System.out.println("Total price: " + totalPrice);
        System.out.println("Guest: " + guest.getGuestName());
    }
}
