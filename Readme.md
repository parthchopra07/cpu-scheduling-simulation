CPU Scheduling Simulation
=========================

This project simulates two CPU scheduling algorithms: First-Come-First-Served (FCFS) and Shortest-Job-First (SJF). The simulation creates jobs with random burst times and inter-arrival times, processes them using both algorithms, and generates reports comparing their performance.

Table of Contents
-----------------

-   Project Structure

-   Classes Overview

-   How It Works

-   Getting Started

-   Output Reports

-   Performance Metrics

Project Structure
-----------------

CPU-Scheduling-Simulation
├── Job.java                # Job entity class
├── JobLengthComparator.java # Comparator for SJF scheduling
├── Main.java               # Entry point
├── ReadyJobs.java          # Job queue management
├── Report.java             # Report generation
├── Scheduler1.java         # FCFS scheduler
├── Scheduler2.java         # SJF scheduler
└── Simulation.java         # Job generation

Classes Overview
----------------

### Job.java

Represents a job with:

-   Job number (ID)

-   Length (burst time)

-   Timestamps for enqueue, start, and end times

-   Waiting time calculation

### ReadyJobs.java

Manages the job queue using a `PriorityQueue` with:

-   FCFS ordering (default constructor)

-   SJF ordering (with `JobLengthComparator`)

-   Thread-safe operations with `synchronized` methods

### Scheduler1.java (FCFS)

-   Processes jobs in arrival order

-   Tracks completed jobs

-   Simulates processing with `Thread.sleep()`

### Scheduler2.java (SJF)

-   Processes shortest jobs first

-   Includes termination capability

-   Tracks completed jobs

### Simulation.java

-   Generates 100 random jobs

-   Random burst times (4-15ms)

-   Random inter-arrival times (5-20ms)

### Report.java

Generates reports with:

-   Per-job details

-   Summary statistics

-   Formatted timestamps

How It Works
------------

1.  The `Main` class creates:

    -   Two `ReadyJobs` instances (FCFS and SJF)

    -   Two schedulers (FCFS and SJF)

    -   Two simulation threads

2.  Simulations run concurrently:

    -   Generate jobs with random characteristics

    -   Add jobs to both scheduling queues

3.  Schedulers:

    -   Process jobs from their respective queues

    -   Track completion times and metrics

4.  Reports:

    -   Generated after all jobs complete

    -   Saved to `FCFS_Report.txt` and `SJF_Report.txt`

Getting Started
---------------

1.  Clone the repository:

   ' git clone https://github.com/yourusername/CPU-Scheduling-Simulation.git'

2.  Compile and run:

    
    'javac *.java'
    'java Main'
3.  View reports:

    -   `FCFS_Report.txt`

    -   `SJF_Report.txt`

Output Reports
--------------

Sample report format:

Job Report
Job #, Burst Time (ms), Waiting Time (ms), Start Time, End Time
Job #0, Burst Time: 10ms, Waiting Time: 5ms, Start Time: 2023-01-01 10:00:05, End Time: 2023-01-01 10:00:15
...

Summary Statistics:
Total Jobs: 100
Total Waiting Time: 1234ms
Average Waiting Time: 12ms

Performance Metrics
-------------------

The simulation compares:

-   **Average Waiting Time**: Typically lower for SJF

-   **Job Completion Order**: FCFS preserves arrival order

-   **Throughput**: Similar for both with this workload

Key observations:

-   SJF generally provides better average waiting times

-   FCFS is simpler and predictable

-   Actual performance depends on job characteristics
