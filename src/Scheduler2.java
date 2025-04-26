//SJF
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class Scheduler2 extends Thread {
    private ReadyJobs readyJobs;
    private boolean running = true;
    private List<Job> completedJobs = Collections.synchronizedList(new ArrayList<>());


    public Scheduler2(ReadyJobs readyJobs) {
        this.readyJobs = readyJobs;
    }
    //If we need to terminate a process
    public void terminate() {
        running = false;
        this.interrupt(); //So the thread exits the waiting state
    }

    @Override
    public void run() {
        while (!Thread.interrupted() && running) {
            Job job = readyJobs.getJob(); // Placeholder method to find the shortest job
            if (job != null) {
                job.startJob(); // Mark the start time and calculate waiting time.
                try {
                    System.out.println("Processing Job #" + job.getJobNumber() + " (Length: " + job.getLength() + ")");
                    Thread.sleep(job.getLength()); // Simulate  processing
                    job.endJob(); // Note the end time.
                    System.out.println("Completed Job #" + job.getJobNumber());
                    completedJobs.add(job); // Add to completed jobs after processing.
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt(); // Properly handle  interruption
                    running = false;
                    System.out.println("Scheduler interrupted during job processing.");
                }
            }
        }
    }
    public List<Job> getCompletedJobs() {
        return new ArrayList<>(completedJobs); // Return a copy to avoid modification issues.
    }

}
