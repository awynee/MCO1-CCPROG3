import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {
        int choice = 0;
        Menu menu = new Menu();
        Scanner scanner = new Scanner(System.in);

        do{
            menu.displayMainMenu();
            choice = menu.getUserChoice(scanner);

            switch(choice){
                case 1:
                    System.out.println("you chose " + choice);
                    break;
                case 2:
                    System.out.println("you chose " + choice);
                    break;
                case 3:
                    System.out.println("you chose " + choice);
                    break;
                case 4:
                    System.out.println("you chose " + choice);
                    break;
            }
        }while(choice != 5);
    }
}