import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Restaurant {
        private int id;
        private String name;
        private double score;
        private String price;
        private String zipCode;
        private List<String> categories;

        private List<Food>menu;

        private int foodCount;
    //private List<Food> foods=new ArrayList<>();

    public Restaurant(){
        menu=new ArrayList<>();
    }
        public Restaurant(int id, String name, double score, String price, String zipCode, List<String> categories) {
            this.id = id;
            this.name = name;
            this.score = score;
            this.price = price;
            this.zipCode = zipCode;
            this.categories = categories;
            this.menu=new ArrayList<>();
        }




        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public double getScore() {
            return score;
        }

        public String getPrice() {
            return price;
        }

        public String getZipCode() {
            return zipCode;
        }
        public List<Food> getMenu(){return menu;}

        public List<String> getCategories() {
            return categories;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setScore(double score) {
            this.score = score;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public void setZipCode(String zipCode) {
            this.zipCode = zipCode;
        }

        public void setCategories(List<String> categories) {
            this.categories = categories;
        }
        public void addToMenu(Food foodItem){
            menu.add(foodItem);
        }
        public void removeFromMenu(String foodItem){
            menu.remove(foodItem);
        }

    public void displayFoodDetails(Food food){
        System.out.println("Name: "+food.getName());
        System.out.println("Category: "+food.getCategory());
        System.out.println("Price:"+food.getPrice());
        System.out.println("Restaurant: "+ getName());
    }

    public List<Food> searchFoodByNameInRestaurant(String foodName) {
        List<Food> matchingFoodItems = new ArrayList<>();
        for (Food food : menu) {
            if (food.getName().toLowerCase().contains(foodName.toLowerCase())) {
                matchingFoodItems.add(food);
            }
        }
        return matchingFoodItems;
    }


    public List<Food> searchFoodByCategoryInRestaurant(String category) {
        List<Food> matchingFoodItems = new ArrayList<>();
        for (Food food : menu) {
            if (food.getCategory().equalsIgnoreCase(category) ) {
                matchingFoodItems.add(food);
            }
        }
        return matchingFoodItems;
    }


    public List<Food> searchFoodByPriceRangeInRestaurant(double minPrice, double maxPrice, String restaurantName) {
        List<Food> matchingFoodItems = new ArrayList<>();
        for (Food food : menu) {
            if (food.getPrice() >= minPrice && food.getPrice() <= maxPrice) {
                matchingFoodItems.add(food);
            }
        }
        return matchingFoodItems;
    }


    public List<Food> displayCostliestFoodItems() {


        List<Food> costliestFoodItems = new ArrayList<>();

        if (menu.isEmpty()) {
            return costliestFoodItems;
        }

        double maxPrice = Double.MIN_VALUE;
        for (Food food : menu) {
            if (food.getPrice() > maxPrice) {
                maxPrice = food.getPrice();
            }
        }

        for (Food food : menu) {
            if (food.getPrice() == maxPrice) {
                costliestFoodItems.add(food);
            }
        }

        return costliestFoodItems;
    }


    public void addFoodToFile(Food food) {

        menu.add(food);


        try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\shema\\IdeaProjects\\Restaurent Database System\\src\\menu.txt", true))) {
            writer.write(food.getRestaurantId() + "," +
                    food.getCategory() + "," +
                    food.getName() + "," +
                    food.getPrice() + System.lineSeparator());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
