import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class User {
    private final int id;
    private int points;
    private final String name;
    private final String email;
    private final String password;
    HashMap<String, String> gifts = new HashMap<>();
    private final List<Coupons> giftsList = new ArrayList<>();

    // constructor to set id, name, points, email, password
    public User(int id, int points, String name, String email, String password) {
        this.id = id;
        this.points = points;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    // Method to get reward points of the user
    public int getPoints() {
        return points;
    }

    // Method to set the user points as per user usage
    public void setPoints(int points) {
        this.points = points;
    }

    // Method to add reward points with the help of transaction thread or on purchasing some product
    public void addPoints(int points){
        this.points += points;
    }

    // Method to get name of the user
    public String getName() {
        return name;
    }

    // Method to get email of the user
    public String getEmail() {
        return email;
    }

    // Method to get password of the user
    public String getPassword() {
        return password;
    }

    // Method to print coupons platform and coupon code
    public void getGifts() {
        for (Coupons gift : giftsList) {
            System.out.println(gift);
        }
    }

    // Method to add a gift
    public void setGifts(String platform, String coupon) {
        giftsList.add(new Coupons(platform, coupon));
    }

    // overridden method to get string representation of user data/object;
    @Override
    public String toString(){
        return "{ id=" + id +
                ", name = " + name +
                ", email = " + email +
                ", password = " + password +
                ", points = " + points +
                ", " + gifts +
                " }";
    }
}
