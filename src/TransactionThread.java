import java.util.concurrent.atomic.AtomicBoolean;

public class TransactionThread implements Runnable{
    private final AtomicBoolean threadRunnning;

    //Constructor to flag the status of thread
    public TransactionThread(AtomicBoolean threadRunning) {
        this.threadRunnning = threadRunning;
    }

    @Override
    public void run(){
        UserService users = new UserService();
        User[] userList = users.getUsers();
        RewardsService rewards = new RewardsService();
        try{
            while(threadRunnning.get()){
                rewards.createRewards(userList);
                Thread.sleep(10000);
            }
        } catch (InterruptedException e){
            System.out.println(e.getMessage());
        }
    }
}
