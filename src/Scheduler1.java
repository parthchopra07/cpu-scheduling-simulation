//FCFS
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
public class Scheduler1 extends Thread// Allows it to run concurrently with other threads
{   private ReadyJobs readyJobs;
    private List<Job> completedJobs = Collections.synchronizedList(new ArrayList<>());
    public Scheduler1(ReadyJobs readyJobs) {
        this.readyJobs = readyJobs;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            Job job = readyJobs.getJob(); // Retrieve the next job
            if (job != null) {
                job.startJob(); // Notes the start time and calculates waiting time internally

                System.out.println("Processing Job #" + job.getJobNumber());

                try {
                    // Simulate  processing by sleeping for the job's length in milisecs
                    Thread.sleep(job.getLength());
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt(); // Re-interrupt the thread
                    System.out.println("Scheduler1 is terminating due to an interruption.");
                    return; // Exit the run method
                }

                job.endJob(); // Marks the end time
                System.out.println("Completed Job #" + job.getJobNumber() + " at " + System.currentTimeMillis());
                completedJobs.add(job); // Collect completed job after processing.
            }

        }
    }
    public List<Job> getCompletedJobs() {
        return new ArrayList<>(completedJobs); // Return a copy to avoid modification issues.
    }
}
