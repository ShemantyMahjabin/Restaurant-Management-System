import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.List;
import java.util.ArrayList;

public class RestaurantDatabase {
    private List<Restaurant> restaurants=new ArrayList<>();




    public RestaurantDatabase(){
        restaurants=new ArrayList<>();

    }




   /* public void addRestaurantToFile(Restaurant restaurant) {
        try {

            List<String> existingContent = Files.readAllLines(Paths.get("C:\\Users\\shema\\IdeaProjects\\Restaurent Database System\\src\\restaurant.txt"));


            StringBuilder newRestaurantData = new StringBuilder();
            newRestaurantData.append(restaurant.getId()).append(",")
                    .append(restaurant.getName()).append(",")
                    .append(restaurant.getScore()).append(",")
                    .append(restaurant.getPrice()).append(",")
                    .append(restaurant.getZipCode()).append(",");
            List<String> categories = restaurant.getCategories();
            for (int i = 0; i < 3; i++) {
                if (i < categories.size()) {
                    newRestaurantData.append(categories.get(i));
                }
                if (i < 2) {
                    newRestaurantData.append(",");
                }
            }


            existingContent.add(newRestaurantData.toString());


            Files.write(Paths.get("C:\\Users\\shema\\IdeaProjects\\Restaurent Database System\\src\\restaurant.txt"), existingContent, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addFoodToFile(Food food) {
        try {

            List<String> existingContent = Files.readAllLines(Paths.get("C:\\Users\\shema\\IdeaProjects\\Restaurent Database System\\src\\Menu.txt"));


            StringBuilder newFoodData = new StringBuilder();
            newFoodData.append(food.getRestaurantId()).append(",")
                    .append(food.getCategory()).append(",")
                    .append(food.getName()).append(",")
                    .append(food.getPrice());


            existingContent.add(newFoodData.toString());


            Files.write(Paths.get("C:\\Users\\shema\\IdeaProjects\\Restaurent Database System\\src\\Menu.txt"), existingContent,StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
*/
   public void addRestaurantToFile(Restaurant restaurant) {
       try {
           BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\shema\\IdeaProjects\\Restaurent Database System\\src\\restaurant.txt", true));
           // Notice the 'true' parameter for append mode
           writer.write(restaurant.getId() + "," +
                   restaurant.getName() + "," +
                   restaurant.getScore() + "," +
                   restaurant.getPrice() + "," +
                   restaurant.getZipCode() + ",");
           List<String> categories = restaurant.getCategories();
           for (int i = 0; i < 3; i++) {
               if (i < categories.size()) {
                   writer.write(categories.get(i));
               }
               if (i < 2) {
                   writer.write(",");
               }
           }
           writer.write(System.lineSeparator());
           writer.close();
       } catch (IOException e) {
           e.printStackTrace();
       }
   }

    public void addFoodToFile(Food food) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\shema\\IdeaProjects\\Restaurent Database System\\src\\Menu.txt", true));
            // Notice the 'true' parameter for append mode
            writer.write(food.getRestaurantId() + "," +
                    food.getCategory() + "," +
                    food.getName() + "," +
                    food.getPrice() + System.lineSeparator());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void saveDataToFile() {
        saveRestaurantsToFile();
        saveFoodToFile();
    }

    public void saveRestaurantsToFile(){
        for(Restaurant restaurant:restaurants){
            addRestaurantToFile(restaurant);
        }
    }

    public void saveFoodToFile(){
        for(Restaurant restaurant:restaurants)
        {
            List<Food>menu=restaurant.getMenu();
            for(Food food:menu){
                addFoodToFile(food);
            }
        }

    }

    public  void addRestaurant(int id, String name, double score, String price, String zipCode, List<String> categories) {
        Restaurant restaurant = new Restaurant(id, name, score, price, zipCode, categories);
        restaurants.add(restaurant);

    }


    public void addFood(int restaurantId,String category,String name,double price){
        Food food=new Food(restaurantId,category,name,price);
        getRestaurantById(restaurantId).getMenu().add(food);

    }





    public List<Restaurant> searchRestaurantsByName(String name) {

        List<Restaurant>foundRestaurants=new ArrayList<>();

        for(Restaurant restaurant:restaurants){
            if(restaurant.getName().toLowerCase().contains(name.toLowerCase())){
                foundRestaurants.add(restaurant);
            }
        }

    return foundRestaurants;
        }




    public Restaurant getRestaurantByIndex(int index) {
        if (index >= 0 && index < restaurants.size()) {
            return restaurants.get(index);
        }
        return null;
    }



    public void displayRestaurantDetails(Restaurant restaurant) {
        System.out.println("ID: " + restaurant.getId());
        System.out.println("Name: " + restaurant.getName());
        System.out.println("Score: " + restaurant.getScore());
        System.out.println("Price: " + restaurant.getPrice());
        System.out.println("Zip Code: " + restaurant.getZipCode());
        System.out.println("Categories: " + restaurant.getCategories());

    }

    public List<Restaurant> searchRestaurantsByScore(double minScore,double maxScore){
        List<Restaurant>foundRestaurants=new ArrayList<>();

        for(Restaurant restaurant:restaurants){
            double score=restaurant.getScore();
            if(score>=minScore && score<=maxScore){
                foundRestaurants.add(restaurant);
            }
        }
        return foundRestaurants;


    }


    public List<Restaurant> searchRestaurantsByCategory(String categorySearch){
        List<Restaurant>foundRestaurants=new ArrayList<>();

        for(Restaurant restaurant:restaurants){
            List<String>categories=restaurant.getCategories();
            for(String category : categories){
                if(category.toLowerCase().contains(categorySearch.toLowerCase())){
                    foundRestaurants.add(restaurant);
                    break;
                }
            }
        }


        return foundRestaurants;
    }


    public List<Restaurant> searchRestaurantsByPrice(String priceSearch){
        List<Restaurant>foundRestaurants=new ArrayList<>();

        for(Restaurant restaurant:restaurants){
            String price=restaurant.getPrice();
            if(price.equalsIgnoreCase(priceSearch)){
                foundRestaurants.add(restaurant);
            }
        }

        return foundRestaurants;
    }

    public List<Restaurant> searchRestaurantsByZipCode(String zipCodeSearch){
        List<Restaurant> foundRestaurants = new ArrayList<>();

        for (Restaurant restaurant : restaurants) {
            String zipCode=restaurant.getZipCode();
            if(zipCode.equalsIgnoreCase(zipCodeSearch)){
                foundRestaurants.add(restaurant);
            }
        }

        return foundRestaurants;
    }

    public String displayCategoryWiseList(){
        Map<String,List<String>>categoryWiseRestaurants=new HashMap<>();

        for(Restaurant restaurant: restaurants){
            for(String category:restaurant.getCategories()){
                categoryWiseRestaurants.computeIfAbsent(category,k->new ArrayList<>()).add(restaurant.getName());

            }
        }
        StringBuilder result = new StringBuilder();
        for(Map.Entry<String,List<String>>entry: categoryWiseRestaurants.entrySet()){
            String category= entry.getKey();
            List<String>restaurantNames=entry.getValue();
            result.append(category).append(": ").append(String.join(", ", restaurantNames)).append("\n");
        }
        return result.toString();
    }


    public Restaurant getRestaurantById(int restaurantId) {
        for (Restaurant restaurant : restaurants) {
            if (restaurant.getId() == restaurantId) {
                return restaurant;
            }
        }
        return null;
    }



    public List<Food> searchFoodByName(String foodName) {
        boolean found = false;
        List<Food> foundFood = new ArrayList<>();


        for(Restaurant restaurant:restaurants) {
            List<Food> menu = restaurant.getMenu();
            for (Food food : menu) {
                if (food.getName().toLowerCase().contains(foodName.toLowerCase())) {
                    found = true;
                    foundFood.add(food);

                }
            }

        }
        return foundFood;
    }
    public void displayFoodDetails(Food food){
        System.out.println("Name: "+food.getName());
        System.out.println("Category: "+food.getCategory());
        System.out.println("Price:"+food.getPrice());
        System.out.println("Restaurant: "+ getRestaurantNameById(food.getRestaurantId()));
    }

    public String getRestaurantNameById(int restaurantId) {
        for(Restaurant restaurant:restaurants){
            if(restaurant.getId()==(restaurantId)){
                return restaurant.getName();
            }
        }
       return "Not found";
    }

   public List<Food> searchFoodByNameInRestaurant(String foodName,String restaurantName){
        //System.out.println("Searching for food Item "+foodName+" in restaurant "+restaurantName+" : ");
        List<Food>matchingFoodItems =new ArrayList<>();
        List<Food>menu=getRestaurantByName(restaurantName).getMenu();
        for(Food food : menu){
            if(food.getName().toLowerCase().contains(foodName.toLowerCase())){
                matchingFoodItems.add(food);
            }
        }

            return matchingFoodItems;
    }
    public List<Food> searchFoodByCategory(String category) {


        List<Food> matchingFoodItems = new ArrayList<>();

        for(Restaurant restaurant:restaurants) {
            List<Food> menu = restaurant.getMenu();
            for (Food food : menu) {
                if (food.getCategory().toLowerCase().contains(category.toLowerCase())) {
                    matchingFoodItems.add(food);
                }
            }

        }

        return matchingFoodItems;
    }

    public List<Food> searchFoodByCategoryInRestaurant(String category,String restaurantName){
        List<Food>matchingFoodItems=new ArrayList<>();
        List<Food>menu=getRestaurantByName(restaurantName).getMenu();
        for(Food food:menu){
            if(food.getCategory().toLowerCase().contains(category.toLowerCase())){
               matchingFoodItems.add(food);
            }
        }

        return matchingFoodItems;
    }


    public List<Food> searchFoodByPriceRange(double minPrice,double maxPrice){
        List<Food>matchingFoodItems=new ArrayList<>();
        for(Restaurant restaurant:restaurants) {
            List<Food> menu = restaurant.getMenu();
            for (Food food : menu) {
                if(food.getPrice()>=minPrice && food.getPrice()<=maxPrice){
                    matchingFoodItems.add(food);
            }
            }
        }

        return matchingFoodItems;
    }
    public List<Food> searchFoodByPriceRangeInRestaurant(double minPrice,double maxPrice,String restaurantName){
        List<Food>matchingFoodItems=new ArrayList<>();
        List<Food>menu=getRestaurantByName(restaurantName).getMenu();
        for(Food food:menu){
            if((food.getPrice()>=minPrice && food.getPrice()<=maxPrice)){
                matchingFoodItems.add(food);
            }
        }
        return matchingFoodItems;

    }



    public List<Food> displayCostliestFoodItems(String restaurantName){
        List<Food> menu = getRestaurantByName(restaurantName).getMenu();
        List<Food> costliestFoodItems = new ArrayList<>();

        if(menu.isEmpty()){
            return costliestFoodItems;
        }

        double maxPrice = -1.0;
        for(Food food: menu){
            if(food.getPrice() > maxPrice){
                maxPrice = food.getPrice();
            }
        }

        for(Food food: menu){
            if(food.getPrice() == maxPrice){
                costliestFoodItems.add(food);
            }
        }

        return costliestFoodItems;
    }

    public List<String> displayRestaurantAndTotalFoodItems() {
        List<String> restaurantAndTotalFoodInfo = new ArrayList<>();

        for (Restaurant restaurant : restaurants) {
            List<Food> menu= restaurant.getMenu();
            int totalFoodItems = menu.size();

            String restaurantInfo = "Restaurant: " + restaurant.getName() + ", Total Food Items: " + totalFoodItems;
            restaurantAndTotalFoodInfo.add(restaurantInfo);
        }

        return restaurantAndTotalFoodInfo;
    }




    public Restaurant getRestaurantByName(String restaurantName) {
        for (Restaurant restaurant : restaurants) {
            if (restaurant.getName().toLowerCase().contains(restaurantName.toLowerCase())){
                return restaurant;
            }
        }
        return null;
    }


    /*public void addRestaurantToFile(Restaurant restaurant){
        try{
            BufferedWriter writer=new BufferedWriter(new FileWriter("C:\\Users\\shema\\IdeaProjects\\Restaurent Database System\\src\\restaurant.txt"));
            writer.write(restaurant.getId()+","+
                    restaurant.getName()+","+
                    restaurant.getScore()+","+
                    restaurant.getPrice()+","+
                    restaurant.getZipCode()+",");
            List<String>categories=restaurant.getCategories();
            for(int i=0;i<3;i++){
                if(i<categories.size()){
                    writer.write(categories.get(i));
                }
                if(i<2){
                    writer.write(",");
                }
            }
            writer.write(System.lineSeparator());
            writer.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }



    public void addFoodToFile(Food food) {
        try{
            BufferedWriter writer=new BufferedWriter(new FileWriter("C:\\Users\\shema\\IdeaProjects\\Restaurent Database System\\src\\Menu.txt"));
            writer.write(food.getRestaurantId()+","+
                    food.getCategory()+","+
                    food.getName()+","+
                    food.getPrice()+System.lineSeparator());
            writer.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
*/



}
