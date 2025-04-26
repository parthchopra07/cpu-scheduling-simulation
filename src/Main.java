import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
public class Main {
    public static void main(String[] args) {
        // Set up for FCFS scheduling
        ReadyJobs readyJobsFCFS = new ReadyJobs(); // Uses default constructor, suitable for FCFS
        Scheduler1 schedulerFCFS = new Scheduler1(readyJobsFCFS);
        Simulation simulationFCFS = new Simulation(readyJobsFCFS);

        // Set up for SJF scheduling
        ReadyJobs readyJobsSJF = new ReadyJobs(new JobLengthComparator()); // Uses Comparator for SJF
        Scheduler2 schedulerSJF = new Scheduler2(readyJobsSJF);
        Simulation simulationSJF = new Simulation(readyJobsSJF);

        // Start the simulations
        simulationFCFS.start();
        simulationSJF.start();

        // Start the schedulers
        schedulerFCFS.start();
        schedulerSJF.start();

        // Wait for the threads to finish if necessary
        // You may want to join on the simulation and scheduler threads to ensure they complete
        try {
            simulationFCFS.join();
            simulationSJF.join();
            schedulerFCFS.join();
            schedulerSJF.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<Job> completedJobsFCFS = schedulerFCFS.getCompletedJobs();
        List<Job> completedJobsSJF = schedulerSJF.getCompletedJobs();

// Assuming Report.writeReport takes a filename and a list of jobs

        Report.writeReport("FCFS_Report.txt", completedJobsFCFS);
        Report.writeReport("SJF_Report.txt", completedJobsSJF);

        // After all threads have completed, you can collect and print statistical data
        // This part is up to you to implement based on how you wish to report the statistics
    }
}
