import java.util.concurrent.atomic.AtomicBoolean;

public class RewardsMain {
    public static void main(String[] args) {
        // creating list of users
        UserService usersList = new UserService();

        AtomicBoolean threadRunning = new AtomicBoolean(true);

        // adding a new thread to add points in user list
        TransactionThread thread = new TransactionThread(threadRunning);
        Thread t1 = new Thread(thread);
        t1.start();

        // login user
        usersList.userLogin();

        // stopping the thread when task is done
        threadRunning.set(false);

        try{
            // join the thread in main thread
            t1.join();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
