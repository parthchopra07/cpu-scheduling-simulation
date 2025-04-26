import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Report {
    public static void writeReport(String filename, List<Job> jobs) {
        try (FileWriter writer = new FileWriter(filename)) {
            System.out.println("I am being called");
            // Header for report
            writer.write("Job Report\n");
            writer.write("Job #, Burst Time (ms), Waiting Time (ms), Start Time, End Time\n");

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            long totalWaitingTime = 0;

            for (Job job : jobs) {
                long waitingTime = job.getWaitingTime();
                totalWaitingTime += waitingTime;

                String formattedStartTime = sdf.format(new Date(job.getStartTime()));
                String formattedEndTime = sdf.format(new Date(job.getEndTime()));

                String line = String.format("Job #%d, Burst Time: %dms, Waiting Time: %dms, Start Time: %s, End Time: %s\n",
                        job.getJobNumber(),
                        job.getLength(),
                        waitingTime,
                        formattedStartTime,
                        formattedEndTime);
                writer.write(line);
            }

            // Writing summary statistics at the end of the report
            long averageWaitingTime = jobs.isEmpty() ? 0 : totalWaitingTime / jobs.size();
            writer.write("\nSummary Statistics:\n");
            writer.write("Total Jobs: " + jobs.size() + "\n");
            writer.write("Total Waiting Time: " + totalWaitingTime + "ms\n");
            writer.write("Average Waiting Time: " + averageWaitingTime + "ms\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
