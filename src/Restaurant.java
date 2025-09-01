import java.util.List;

public class Restaurant {
    private int id;
    private String name;
    private double score;
    private String price;
    private String zipCode;
    private List<String> categories;

    public Restaurant(int id, String name, double score, String price, String zipCode, List<String> categories) {
        this.id = id;
        this.name = name;
        this.score = score;
        this.price = price;
        this.zipCode = zipCode;
        this.categories = categories;
    }

    public Restaurant() {
        
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

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", name= " + name + '\'' +
                ", score= " + score +
                ", price= " + price + '\'' +
                ", zipCode= " + zipCode + '\'' +
                ", categories= " + categories + '}';
    }

    public String toCSVString() {
        StringBuilder sb = new StringBuilder();
        sb.append(id).append(",");
        sb.append(name).append(",");
        sb.append(score).append(",");
        sb.append(price).append(",");
        sb.append(zipCode).append(",");

        // Join the categories list into a single string
        sb.append(String.join("|", categories));

        return sb.toString();
    }


    public void addRestaurant() {
    }
}

