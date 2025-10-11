import java.util.List;
import java.util.ArrayList;

public class ManageProperty {
    private List<PropertyListing> properties;

    public ManageProperty() {
        this.properties = new ArrayList<>();
    }

    public void addProperty(PropertyListing property) {
        properties.add(property);
        System.out.println("Property '" + property.getPropertyName() + "' added successfully.");
    }

    public void changePropertyName(String oldName, String newName) {
        boolean found = false;

        for (int i = 0; i < properties.size(); i++) {
            PropertyListing p = properties.get(i);
            if (p.getPropertyName().equals(oldName)) {
                p.changePropertyName(newName);
                found = true;
            }
        }

        if (found) {
            System.out.println("Property name changed from '" + oldName + "' to '" + newName + "'.");
        } else {
            System.out.println("No property found with name '" + oldName + "'.");
        }
    }

    public void removeReservationByDay(PropertyListing property, int day) {
        boolean found = false;
        int indexToRemove = -1;

        List<Reservation> reservations = property.getReservations();

        for (int i = 0; i < reservations.size(); i++) {
            Reservation r = reservations.get(i);
            if (day >= r.getFromDay() && day <= r.getToDay()) {
                found = true;
                indexToRemove = i;
            }
        }

        if (found && indexToRemove >= 0) {
            reservations.remove(indexToRemove);
            System.out.println("Reservation for day " + day + " removed successfully from property '" + property.getPropertyName() + "'.");
        } else {
            System.out.println("No reservation found for day " + day + " in property '" + property.getPropertyName() + "'.");
        }
    }

    public void removeProperty(String propertyName) {
        boolean found = false;
        int indexToRemove = -1;

        for (int i = 0; i < properties.size(); i++) {
            PropertyListing p = properties.get(i);
            if (p.getPropertyName().equals(propertyName)) {
                found = true;
                indexToRemove = i;
            }
        }

        if (found && indexToRemove >= 0) {
            properties.remove(indexToRemove);
            System.out.println("Property '" + propertyName + "' removed successfully.");
        } else {
            System.out.println("No property found with name '" + propertyName + "'.");
        }
    }

    public void displayAllProperties() {
        if (properties.isEmpty()) {
            System.out.println("No properties currently managed.");
        } else {
            for (int i = 0; i < properties.size(); i++) {
                PropertyListing p = properties.get(i);
                System.out.println((i + 1) + ". " + p.getPropertyName());
            }
        }
    }

    public List<PropertyListing> getProperties() {
        return properties;
    }
}
