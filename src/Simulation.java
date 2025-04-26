import java.util.Random;

public class Simulation extends Thread {
    private ReadyJobs readyJobs;
    private Random random = new Random();

    public Simulation(ReadyJobs readyJobs) {
        this.readyJobs = readyJobs;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            // Generate a time-length between 4 and 15 milliseconds for  jobs burst time
            int length = random.nextInt(12) + 4;
            Job job = new Job(i, length); // new job with the length
            readyJobs.addJob(job);
            try {
                // Sleep before creating a new job
                Thread.sleep(random.nextInt(16) + 5);
                //To handle interruptions
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
