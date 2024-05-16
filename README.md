# Rewards Application
Rewards Application is a console based project in which user can redeem the coupons from the reward points he gets in shopping

## How it Works

### Login 
Project contains 10 users by default. User can log in using the registered email id and password(By default email: user@gmail.com password: password)

### Randomly assigning rewards
Since the project is only console based project, user gets reward points randomly after every 10 seconds. Worked this by creating an extra transaction thread which terminates when user exits.

### Selecting Category
User get three categories to select product from: 
 - Electronics
 - Customized Products
 - Travelling offers

### Selecting Products
User can select products based on different categories and redemption points

On selecting product users reward points will be reduced and user gets a coupon code with platform where user can redeem the coupon.

### Redeem more?
User gets a choice to redeem more coupons. There is no need of re-running the program from starting.