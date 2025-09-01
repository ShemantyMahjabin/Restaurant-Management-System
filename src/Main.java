import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RestaurantManager restaurantManager = new RestaurantManager();

        // Load restaurants from a file (you can implement this)
        try {
            restaurantManager.loadRestaurantsFromFile("restaurants.txt");
        } catch (Exception e) {
            System.out.println("Error loading restaurants: " + e.getMessage());
        }

        while (true) {
            System.out.println("Main Menu:");
            System.out.println("1) Search Restaurants");
            System.out.println("2) Search Food Items");
            System.out.println("3) Add Restaurant");
            System.out.println("4) Add Food Item to the Menu");
            System.out.println("5) Exit System");
            System.out.print("Option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    restaurantSearchMenu(scanner, restaurantManager);
                    break;
                case 2:
                    searchFoodItemsMenu(scanner, restaurantManager);
                    break;
                case 3:
                    restaurantManager.addRestaurant();
                    break;
                case 4:
                    addFoodItemToMenu(scanner, restaurantManager);
                    break;
                case 5:
                    restaurantManager.saveRestaurantsToFile();
                    System.out.println("Restaurants saved to file.");
                    break;

                    // Save restaurants to a file (you can implement this)
                    try {
                        restaurantManager.saveRestaurantsToFile();
                    } catch (Exception e) {
                        System.out.println("Error saving restaurants: " + e.getMessage());
                    }
                    System.out.println("Exiting the system...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please choose a valid option.");
            }
        }
    }

    private static void addFoodItemToMenu(Scanner scanner, RestaurantManager restaurantManager) {
    }

    private static void searchFoodItemsMenu(Scanner scanner, RestaurantManager restaurantManager) {
    }

    private static void restaurantSearchMenu(Scanner scanner, RestaurantManager restaurantManager) {

    }
}
