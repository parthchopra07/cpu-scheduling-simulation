import java.util.Comparator;

//To simply compare two jobs and comes up with smaller job
public class JobLengthComparator implements Comparator<Job> {
    @Override
    public int compare(Job job1, Job job2) {
        return Integer.compare(job1.getLength(), job2.getLength());
    }
}
