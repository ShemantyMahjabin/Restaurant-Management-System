import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class RestaurantManager {
    private List<Restaurant> restaurantList;

    public RestaurantManager() {
        restaurantList = new ArrayList<>();
    }


    public Restaurant getRestaurantById(int id){
        for(Restaurant restaurant: restaurantList){
            if(restaurant.getId()==id){
                return restaurant;
            }
        }
        return null;
    }

    public void loadRestaurantsFromFile(String s) {
        String fileName = null;
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] restaurantData = line.split(",");
                int id = Integer.parseInt(restaurantData[0]);
                String name = restaurantData[1];
                double score = Double.parseDouble(restaurantData[2]);
                String price = restaurantData[3];
                String zipCode = restaurantData[4];
                List<String> categories = new ArrayList<>();
                for (int i = 5; i < restaurantData.length; i++) {
                    categories.add(restaurantData[i]);
                }
                Restaurant restaurant = new Restaurant(id, name, score, price, zipCode, categories);
                restaurant.addRestaurant();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void saveRestaurantsToFile() {
        String fileName = null;
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (Restaurant restaurant : restaurantList) {
                bw.write(restaurant.toCSVString());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
