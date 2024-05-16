import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

public class UserService {
    User[] usersList = new User[10];
    User user;
    AtomicBoolean isUser = new AtomicBoolean(false);
    boolean isExit = false;

    // constructor to create 10 users
    public UserService() {
        usersList[1] = new User(1, 5000, "user", "user@gmail.com", "password");
        usersList[0] = new User(2, 5000, "Sumant", "sumant@gmail.com", "sumant");
        usersList[2] = new User(3, 5000, "Rahul", "Rahul@gmail.com", "rahul");
        usersList[3] = new User(4, 5000, "Ryan", "Ryan@gmail.com", "ryan");
        usersList[4] = new User(5, 5000, "Robin", "Robin@gmail.com", "robin");
        usersList[5] = new User(6, 5000, "Sameeksha", "Sameeksha@gmail.com", "sameeksha");
        usersList[6] = new User(7, 5000, "Diya", "Diya@gmail.com", "diya");
        usersList[7] = new User(8, 5000, "Monu", "Monu@gmail.com", "monu");
        usersList[8] = new User(9, 5000, "Sonu", "Sonu@gmail.com", "sonu");
        usersList[9] = new User(10, 5000, "Sanju", "Sanju@gmail.com", "sanju");
    }

    // method to get user list
    public User[] getUsers(){
        return usersList;
    }

    // method to login user
    public void userLogin(){

        System.out.println("Welcome to Rewards Application");
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter you credentials:");
        System.out.println("Email: ");
        String email = scan.nextLine();
        System.out.println("Password: ");
        String password = scan.nextLine();

        // to check user exists or not
        for(User user: usersList){
            if(user.getEmail().equalsIgnoreCase(email) && user.getPassword().equals(password)){
                isUser.set(true);
                System.out.println("Welcome " + user.getName());
                while (!isExit) {
                    System.out.println("You have " + user.getPoints() + " reward points in you wallet.");
                    this.user = user;
                    RewardsService rewardsService = new RewardsService();
                    String category = rewardsService.rewardsCategories();
                    String redeemedProduct;
                    System.out.println(category);

                    // Check the category user selected
                    if ("1".equals(category) || "Electronics".equalsIgnoreCase(category)) {
                        redeemedProduct = rewardsService.redeemElectronics();

                        // redemption based on user selection
                        switch (redeemedProduct) {
                            case "1", "Headphone" -> rewardsService.redeemPoints(user, 2000, "Headphone");
                            case "2", "Laptop" -> rewardsService.redeemPoints(user, 20000, "Laptop");
                            case "3", "Portable speaker" -> rewardsService.redeemPoints(user, 3000, "Portable speaker");
                            case "4", "Apple watch" -> rewardsService.redeemPoints(user, 10000, "Apple watch");
                            default -> System.out.println("Invalid choice. Please try again.");
                        }
                    } else if ("2".equals(category) || "Travel".equalsIgnoreCase(category)) {
                        redeemedProduct = rewardsService.redeemTravel();

                        switch (redeemedProduct) {
                            case "1", "Goa" -> rewardsService.redeemPoints(user, 20000, "Goa");
                            case "2", "Manali" -> rewardsService.redeemPoints(user, 10000, "Manali");
                            case "3", "Nainital" -> rewardsService.redeemPoints(user, 15000, "Nainital");
                            case "4", "Musoorie" -> rewardsService.redeemPoints(user, 25000, "Musoorie");
                            default -> System.out.println("Invalid choice. Please try again.");
                        }
                    } else if ("3".equals(category) || "Customized Products".equalsIgnoreCase(category)) {
                        redeemedProduct = rewardsService.redeemCustomizedProduct();

                        switch (redeemedProduct) {
                            case "1", "T-Shirt" -> rewardsService.redeemPoints(user, 2000, "T-Shirt");
                            case "2", "Cap" -> rewardsService.redeemPoints(user, 1000, "Cap");
                            case "3", "Smart Water Bottle" -> rewardsService.redeemPoints(user, 1500, "Smart Water Bottle");
                            case "4", "Personalized Diary" -> rewardsService.redeemPoints(user, 200, "Personalized Diary");
                            default -> System.out.println("Invalid choice. Please try again.");
                        }
                    } else {
                        System.out.println("Invalid Category!! Try Again");
                    }
                    System.out.println("You have following coupons in your wallet: ");

                    // print user coupons
                    user.getGifts();

                    System.out.println("Would you like to continue redeem your points? y/n");
                    String rerun = scan.nextLine();
                    if(Objects.equals(rerun, "n")){
                        isExit=true;
                    }
                }
            }
        }

        if(!isUser.get()){
            System.out.println("Invalid Credentials! Please try Again");
        }
    }

}
