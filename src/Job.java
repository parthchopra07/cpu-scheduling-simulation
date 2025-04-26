// Job class to keep track of all the details regarding the job
public class Job implements Comparable<Job> {
    private final int jobNumber;
    private final int length;
    private final long enqueueTime; // Timestamp when the job was added to the queue
    private long waitingTime;
    private long startTime;//When it actually reaches the CPU
    private long endTime;

    //
    public Job(int jobNumber, int length) {
        this.jobNumber = jobNumber;
        this.length = length;
        this.enqueueTime = System.currentTimeMillis();
}
    public void startJob() {
        this.startTime = System.currentTimeMillis();
        this.waitingTime = this.startTime - this.enqueueTime; // Calculate wait time
    }

    public void endJob() {
        this.endTime = System.currentTimeMillis();
    }
    // Getters
    public int getJobNumber() {
        return jobNumber; }

    public int getLength() {
        return length; }

    public long getEnqueueTime() {
        return enqueueTime;
    }
    public long getStartTime(){
        return startTime;
    }
    public long getEndTime(){
        return endTime;
    }
    public long getWaitingTime() {
        return waitingTime;
    }
    // To compare enqueue times for FCFS
    @Override
    public int compareTo(Job other) {
        return Long.compare(this.enqueueTime, other.enqueueTime);
    }
}
