import java.security.SecureRandom;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class RewardsService {
    Scanner scan = new Scanner(System.in);
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int COUPON_LENGTH = 10;

    // method to create reward points randomly
    public void createRewards(User[] userList) {
        Random random = new Random();
        int randomUser = random.nextInt(userList.length);
        int randomPoints = 100 + random.nextInt(901);
        userList[randomUser].addPoints(randomPoints);
    }

    // method to select category
    public String rewardsCategories(){
        System.out.println("Please select one of the categories you would like to spend your reward points for: ");
        System.out.println("1. Electronics");
        System.out.println("2. Travel");
        System.out.println("3. Customized Products");
        return scan.nextLine();
    }

    // method to select electronic devices
    public String redeemElectronics(){
        System.out.println("Please select a electronic item you would like to redeem");
        System.out.println("1. Headphone - 2000 points");
        System.out.println("2. Laptop - 20000 points");
        System.out.println("3. Portable speaker - 3000 points");
        System.out.println("4. Apple watch - 10000 points");
        return scan.nextLine();
    }

    // method to select travel offers
    public String redeemTravel(){
        System.out.println("Please select a Travel plan you would like to redeem");
        System.out.println("1. Goa - 20000 points");
        System.out.println("2. Manali - 10000 points");
        System.out.println("3. Nainital - 15000 points");
        System.out.println("4. Musoorie - 25000 points");
        return scan.nextLine();
    }

    // method to select customized products
    public String redeemCustomizedProduct(){
        System.out.println("Please select a customized product item you would like to redeem");
        System.out.println("1. T-Shirt - 2000 points");
        System.out.println("2. Cap - 1000 points");
        System.out.println("3. Smart Water Bottle - 1500 points");
        System.out.println("4. Personalized Diary - 200 points");
        return scan.nextLine();
    }

    // method to check reward points are enough or not
    public boolean checkAvailability(int points, int i){
        System.out.println(points + " " + i);
        return points >= i;
    }

    // method to generate random coupon code
    public static String generateCouponCode(int length) {
        SecureRandom random = new SecureRandom();
        StringBuilder coupon = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            coupon.append(CHARACTERS.charAt(randomIndex));
        }
        return coupon.toString();
    }

    // method to redeem points by user
    public void redeemPoints(User user, int i, String product) {
        boolean isAvailable = checkAvailability(user.getPoints(), i);

        if(!isAvailable){
            System.out.println("You don't have enough points. Please try Again!!");
        } else {
            // electronics
            if(Objects.equals(product, "Headphone") || Objects.equals(product, "Laptop") || Objects.equals(product, "Portable speaker") || Objects.equals(product, "Apple watch")){
                Random random = new Random();
                String[] platforms = {"Amazon", "Flipkart", "Meesho", "Myntra", "Best Buy"};

                // select random platform
                int randomIndex = random.nextInt(platforms.length);
                String eCommerceSite = platforms[randomIndex];
                String couponCode = generateCouponCode(COUPON_LENGTH);
                try{
                    user.setGifts(eCommerceSite, couponCode);
                    user.setPoints(user.getPoints()-i);

                    System.out.println("Congratulations! You have redeemed coupon code of " + eCommerceSite + " : " + couponCode + ". Now you can redeem your coupon for " + product + " on " + eCommerceSite + ".");
                    System.out.println("Remaining reward points in your wallet: " + user.getPoints());
                } catch (Exception e){
                    System.out.println("Error while creating coupon");
                }
            }
            // travelling offers
            else if(Objects.equals(product, "Goa") || Objects.equals(product, "Manali") || Objects.equals(product, "Nainital") || Objects.equals(product, "Musoorie")){
                Random random = new Random();
                String[] platforms = {"MakeMyTrip", "IRCTC", "AirBnb", "Yatra", "Goibibo"};
                int randomIndex = random.nextInt(platforms.length);
                String eCommerceSite = platforms[randomIndex];
                String couponCode = generateCouponCode(COUPON_LENGTH);
                try{
                    user.setGifts(eCommerceSite, couponCode);
                    user.setPoints(user.getPoints()-i);

                    System.out.println("Congratulations! You have redeemed coupon code of " + eCommerceSite + " : " + couponCode + ". Now you can redeem your coupon for " + product + " on " + eCommerceSite + ".");
                    System.out.println("Remaining reward points in your wallet: " + user.getPoints());
                } catch (Exception e){
                    System.out.println("Error while creating coupon");
                }
            }
            // customized products
            else if(Objects.equals(product, "T-Shirt") || Objects.equals(product, "Cap") || Objects.equals(product, "Smart Water Bottle") || Objects.equals(product, "Personalized Diary")){
                System.out.println("What would you like to print on your " + product + ": ");
                String title = scan.nextLine();
                try{
                    user.setGifts(product, null);
                    user.setPoints(user.getPoints()-i);

                    System.out.println("Congratulations! You have redeemed coupon code of customized " + product + " with title : " + title + ". Product will be delivered within 15 days at your registered address");
                    System.out.println("Remaining reward points in your wallet: " + user.getPoints());
                } catch (Exception e){
                    System.out.println("Error while creating coupon");
                }
            }
        }
    }
}
