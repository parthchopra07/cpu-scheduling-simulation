import java.util.PriorityQueue;
import java.util.Comparator;

public class ReadyJobs {
    //DS to store the jobs
    private PriorityQueue<Job> queue;

    //To choose between SJF and FCFS if we call the constructor with an argument we get FCFS and
    //SJF without an argument
    // Constructor for SJF, which requires a Comparator for the job length.
    public ReadyJobs(Comparator<Job> comparator) {
        this.queue = new PriorityQueue<>(comparator);
    }

    // Default constructor for FCFS, jobs will be processed based on the order they were added.
    public ReadyJobs() {
        // For FCFS, jobs are processed in the order they were enqueued.
        // This assumes that the Job class has a field 'enqueueTime' which is set when the job is created.
        this.queue = new PriorityQueue<>(Comparator.comparingLong(Job::getEnqueueTime));
    }

    // Functions to add a job to the queue.
    public synchronized void addJob(Job job) {
        queue.add(job);
        notifyAll(); // Notify the waiting threads
    }

    // Method to retrieve and remove the next job from the queue.
    public synchronized Job getJob() {
        while (queue.isEmpty()) {
            try {
                wait(); //Wait for the job to be available
            } catch (InterruptedException e) {  //To handle errors or unexpected behaviours
                Thread.currentThread().interrupt();
                return null;
            }
        }
        return queue.poll(); // Returning the head of the queue
    }


}
