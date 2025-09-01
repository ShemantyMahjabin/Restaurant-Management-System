import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) throws Exception {
        String INPUT_FILE_NAME="C:\\Users\\shema\\IdeaProjects\\Restaurent Database System\\src\\restaurant.txt";
        String INPUT_FOOD_FILE_NAME="C:\\Users\\shema\\IdeaProjects\\Restaurent Database System\\src\\menu.txt";
        RestaurantDatabase restaurantDatabase = new RestaurantDatabase();
        Restaurant res=new Restaurant();
        try {

            BufferedReader reader1 = new BufferedReader(new FileReader(INPUT_FILE_NAME));
            BufferedReader reader2 = new BufferedReader(new FileReader(INPUT_FOOD_FILE_NAME));



            String line;
            while ((line = reader1.readLine()) != null) {

                String[] restaurantData = line.split(",(?!\\s)");
                int id = Integer.parseInt(restaurantData[0]);
                String name = restaurantData[1];
                double score = Double.parseDouble(restaurantData[2]);
                String price = restaurantData[3];
                String zipCode = restaurantData[4];
                List<String> categories = new ArrayList<>();
                for (int i = 5; i < restaurantData.length; i++) {
                    if (!restaurantData[i].isEmpty()) {
                        categories.add(restaurantData[i]);
                    }
                }
                restaurantDatabase.addRestaurant(id, name, score, price, zipCode, categories);

            }
            reader1.close();
            ;




            while ((line = reader2.readLine()) != null) {
                String[] foodData = line.split(",(?!\\s)");
                int restaurantId = Integer.parseInt(foodData[0]);
                String category = foodData[1];
                String name = foodData[2];
                double price = Double.parseDouble(foodData[3]);
                Restaurant restaurant=restaurantDatabase.getRestaurantById(restaurantId);
                if(restaurant!=null)
                {
                    Food food=new Food(restaurantId,category,name,price);
                    restaurant.addToMenu(food);
                }
                //restaurantDatabase.addFood(restaurantId, category, name, price);

            }
            reader2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }







        Scanner scanner = new Scanner(System.in);




        int option;
        int choice;
        int choiceFoodMenu;

        do {
            System.out.println("Main Menu:");
            System.out.println("1)Search Restaurants");
            System.out.println("2)Search Food Items");
            System.out.println("3)Add Restaurant");
            System.out.println("4)Add Food Item to the Menu");
            System.out.println("5)Exit System");
            System.out.print("Option: ");

            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:

                  do {
                       System.out.println("Restaurant Searching Options: ");
                       System.out.println("1)By Name");
                       System.out.println("2)By Score");
                       System.out.println("3)By Category");
                       System.out.println("4)By Price");
                       System.out.println("5)By Zip Code");
                       System.out.println("6)Different Category Wise List of Restaurants");
                       System.out.println("7)Back to Main Menu");
                       System.out.print("Enter your choice: ");

                        choice = scanner.nextInt();
                       scanner.nextLine();

                       switch (choice) {

                           case 1:
                               System.out.print("Enter restaurant name: ");
                               String nameSearch = scanner.nextLine();
                               List<Restaurant> foundRestaurantName = restaurantDatabase.searchRestaurantsByName(nameSearch);
                               if (foundRestaurantName.isEmpty()) {
                                   System.out.println("No such restaurant with this name");
                               } else {
                                   for (Restaurant restaurant : foundRestaurantName) {
                                       restaurantDatabase.displayRestaurantDetails(restaurant);
                                   }
                               }
                               break;

                           case 2:
                               System.out.print("Enter minimum score: ");
                               double minScore = scanner.nextDouble();
                               System.out.print("Enter maximum score: ");
                               double maxScore = scanner.nextDouble();
                               List<Restaurant> foundRestaurants = restaurantDatabase.searchRestaurantsByScore(minScore, maxScore);
                               if (foundRestaurants.isEmpty()) {
                                   System.out.println("No such restaurant with this score range");
                               } else {
                                   System.out.println("Found restaurant(s) within the score range [" + minScore + " - " + maxScore + "]:");
                                   for (Restaurant restaurant : foundRestaurants) {
                                       restaurantDatabase.displayRestaurantDetails(restaurant);
                                   }
                               }
                               break;
                           case 3:
                               System.out.print("Enter category: ");
                               String categorySearch = scanner.nextLine();
                               List<Restaurant> foundRestaurantCat = restaurantDatabase.searchRestaurantsByCategory(categorySearch);
                               if (foundRestaurantCat.isEmpty()) {
                                   System.out.println("No such restaurant with this category");
                               } else {
                                   System.out.println("Found restaurant(s) with the specified category: ");
                                   for (Restaurant restaurant : foundRestaurantCat) {
                                       restaurantDatabase.displayRestaurantDetails(restaurant);
                                   }
                               }
                               break;
                           case 4:
                               System.out.print("Enter price($,$$ or $$$): ");
                               String priceSearch = scanner.nextLine();
                               List<Restaurant> foundRestaurantPri = restaurantDatabase.searchRestaurantsByPrice(priceSearch);
                               if (foundRestaurantPri.isEmpty()) {
                                   System.out.println("No such restaurant with this price");
                               } else {
                                   System.out.println("Found restaurant(s) with the specified price: " + priceSearch);
                                   for (Restaurant restaurant : foundRestaurantPri) {
                                       restaurantDatabase.displayRestaurantDetails(restaurant);
                                   }
                               }
                               break;
                           case 5:
                               System.out.print("Enter Zip Code: ");
                               String zipCodeSearch = scanner.nextLine();
                               List<Restaurant> foundRestaurantZip = restaurantDatabase.searchRestaurantsByZipCode(zipCodeSearch);
                               if (foundRestaurantZip.isEmpty()) {
                                   System.out.println("No such restaurant with this zip code");
                               } else {
                                   System.out.println("Found restaurant(s) with the specified zip code: " + zipCodeSearch);
                                   for (Restaurant restaurant : foundRestaurantZip) {
                                       restaurantDatabase.displayRestaurantDetails(restaurant);
                                   }
                               }
                               break;
                           case 6:
                               String categoryWiseList = restaurantDatabase.displayCategoryWiseList();
                               System.out.println(categoryWiseList);
                               break;

                           case 7:
                               System.out.println("going back to main");
                               break;
                           default:
                               System.out.println("Invalid choice. Please select a valid option.");


                       }
                   }while (choice!=7);


                    break;

                case 2:

                    do {

                        System.out.println("\nFood Item Searching Options: ");
                        System.out.println("1)By Name");
                        System.out.println("2)By Name in a Given Restaurant ");
                        System.out.println("3)By Category");
                        System.out.println("4)By Category in a Given Restaurant ");
                        System.out.println("5)By Price Range");
                        System.out.println("6)By Price Range in a Given Restaurant");
                        System.out.println("7)Costliest Food Item(s) on the Menu in a given Restaurant");
                        System.out.println("8)List of Restaurants and Total Food Item on the Menu");
                        System.out.println("9)Back to Main Menu");
                        System.out.print("Enter your choice: ");

                        choiceFoodMenu = scanner.nextInt();
                        scanner.nextLine();

                        switch (choiceFoodMenu) {
                            case 1:
                                System.out.println("Enter food item name: ");
                                String foodName = scanner.nextLine();
                                List<Food> foundFoodName = restaurantDatabase.searchFoodByName(foodName);
                                if (foundFoodName.isEmpty()) {
                                    System.out.println("No such food items with this name.");

                                } else {
                                    System.out.println("Food Item Details");
                                    for (Food food : foundFoodName) {

                                        restaurantDatabase.displayFoodDetails(food);
                                    }
                                }
                                break;
                            case 2:
                                System.out.print("Enter food item name: ");
                                String foodNameInRestaurant = scanner.nextLine();
                                System.out.print("Enter restaurant name: ");
                                String restaurantNameForFood = scanner.nextLine();
                                List<Food> matchingFoodName = restaurantDatabase.searchFoodByNameInRestaurant(foodNameInRestaurant, restaurantNameForFood);
                                if (matchingFoodName.isEmpty()) {
                                    System.out.println("No such food item with this name on the menu of this restaurant");
                                } else {
                                    for (Food food : matchingFoodName) {
                                        restaurantDatabase.displayFoodDetails(food);
                                    }
                                }
                                break;
                            case 3:
                                System.out.print("Enter food item category: ");
                                String foodCategory = scanner.nextLine();
                                List<Food> matchingFoodCat = restaurantDatabase.searchFoodByCategory(foodCategory);
                                if (matchingFoodCat.isEmpty()) {
                                    System.out.println("No such food item with this category");
                                } else {
                                    System.out.println("Matching food item(s) in category");
                                    for (Food food : matchingFoodCat) {
                                        restaurantDatabase.displayFoodDetails(food);
                                    }
                                }
                                break;
                            case 4:
                                System.out.print("Enter food item category: ");
                                String foodCategoryInRestaurant = scanner.nextLine();
                                System.out.print("Enter restaurant name: ");
                                String restaurantNameForCategory = scanner.nextLine();

                                List<Food> matchingFoodCatRes = restaurantDatabase.searchFoodByCategoryInRestaurant(foodCategoryInRestaurant, restaurantNameForCategory);
                                if (matchingFoodCatRes.isEmpty()) {
                                    System.out.println("No such food item with this category on the menu of this restaurant");
                                } else {
                                    System.out.println("Matching food item(s) in restaurant:");
                                    for (Food food : matchingFoodCatRes) {
                                        restaurantDatabase.displayFoodDetails(food);
                                    }
                                }
                                break;
                            case 5:
                                System.out.print("Enter minimum price: ");
                                double minPrice = scanner.nextDouble();
                                System.out.print("Enter maximum price: ");
                                double maxPrice = scanner.nextDouble();
                                List<Food> matchingFoodPrice = restaurantDatabase.searchFoodByPriceRange(minPrice, maxPrice);
                                if (matchingFoodPrice.isEmpty()) {
                                    System.out.println("No such food item with this price range");
                                } else {
                                    for (Food food : matchingFoodPrice) {
                                        restaurantDatabase.displayFoodDetails(food);
                                    }
                                }
                                break;
                            case 6:
                                System.out.print("Enter minimum price: ");
                                double minPriceInRestaurant = scanner.nextDouble();
                                scanner.nextLine();
                                System.out.print("Enter maximum price: ");
                                double maxPriceInRestaurant = scanner.nextDouble();
                                scanner.nextLine();
                                System.out.print("Enter restaurant name: ");
                                String restaurantNameForPriceRange = scanner.nextLine();
                                List<Food> matchingFoodPriceRes = restaurantDatabase.searchFoodByPriceRangeInRestaurant(minPriceInRestaurant, maxPriceInRestaurant, restaurantNameForPriceRange);
                                if (matchingFoodPriceRes.isEmpty()) {
                                    System.out.println("No such food item with this price range on the menu of this restaurant");
                                } else {
                                    for (Food food : matchingFoodPriceRes) {
                                        restaurantDatabase.displayFoodDetails(food);
                                    }
                                }
                                break;

                            case 7:
                                System.out.print("Enter restaurant name: ");
                                String restaurantNameForCostliest = scanner.nextLine();
                                List<Food> costliestFoodItems = restaurantDatabase.displayCostliestFoodItems(restaurantNameForCostliest);

                                if (!costliestFoodItems.isEmpty()) {
                                    System.out.println("Costliest food item(s) in restaurant " + restaurantNameForCostliest + ":");
                                    for (Food food : costliestFoodItems) {
                                        restaurantDatabase.displayFoodDetails(food);
                                    }
                                } else {
                                    System.out.println("No costliest food item found for the specified restaurant.");
                                }
                                break;

                            case 8:
                                List<String> restaurantAndTotalFoodInfo = restaurantDatabase.displayRestaurantAndTotalFoodItems();

                                if (!restaurantAndTotalFoodInfo.isEmpty()) {
                                    System.out.println("List of Restaurants and Total Food Items:");
                                    for (String info : restaurantAndTotalFoodInfo) {
                                        System.out.println(info);
                                    }
                                } else {
                                    System.out.println("No restaurants with food items found.");
                                }
                                break;

                            case 9:
                                break;
                            default:
                                System.out.println("Invalid choice.Please enter a valid option .");
                        }
                    }while (choiceFoodMenu!=9);


                    break;

                case 3:

                        System.out.print("Enter restaurant Id: ");
                        int id = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Enter restaurant name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter score: ");
                        double score = scanner.nextDouble();
                        scanner.nextLine();

                        System.out.print("Enter price: ");
                        String price = scanner.nextLine();

                        System.out.print("Enter zip code: ");
                        String zipCode = scanner.nextLine();

                        List<String> categories = new ArrayList<>();
                        for (int i = 1; i <= 3; i++) {
                            System.out.print("Enter category " + i + " (or leave blank to skip): ");
                            String category = scanner.nextLine();
                            if (!category.isEmpty()) {
                                categories.add(category);
                            }
                        }
                        Restaurant newRestaurant = new Restaurant(id, name, score, price, zipCode, categories);
                        restaurantDatabase.addRestaurant(id, name, score, price, zipCode, categories);

                restaurantDatabase.addRestaurantToFile(newRestaurant);


                    break;
                case 4:

                        System.out.print("Enter restaurant name: ");
                        String restaurantName = scanner.nextLine();

                        Restaurant restaurant = restaurantDatabase.getRestaurantByName(restaurantName);
                        if (restaurant == null) {
                            System.out.println("Restaurant not found. Please enter a valid restaurant name.");
                            break;
                        }

                        System.out.print("Enter food item name: ");
                        String foodName = scanner.nextLine();

                        System.out.print("Enter food item category: ");
                        String category = scanner.nextLine();

                        System.out.print("Enter food item price: ");
                        double foodPrice = scanner.nextDouble();
                        scanner.nextLine();

                        Food foodItem = new Food(restaurant.getId(), foodName, category, foodPrice);
                        restaurantDatabase.addFood(restaurant.getId(), foodName, category, foodPrice);
                        restaurantDatabase.addFoodToFile(foodItem);

                        System.out.println("Food item added to the menu of " + restaurantName + " successfully!");

                    break;
                case 5:
                   // restaurantDatabase.saveDataToFile();
                    System.out.println("Exiting the system.");
                    return;
                default:
                    System.out.println("Invalid option. Please choose a valid option.");
            }

        } while (option != 5);


    }
}


