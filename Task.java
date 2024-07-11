import java.util.concurrent.Semaphore;

class Task {
    private int id;
    private double priority;
    private long arrivalTime;
    private int duration;
    private double deadline;
    private int requiredCache;
    private int requiredMemory;
    private int requiredFrequency;
    private long startTime;
    private long endTime;
    private boolean isDone = false;

    Semaphore mutex = new Semaphore(1);

    public Task(int id, int priority, long arrivalTime, int duration, long deadline, int requiredCache,
                int requiredMemory, int requiredFrequency) {
        this.id = id;
        this.priority = priority;
        this.arrivalTime = arrivalTime;
        this.duration = duration;
        this.deadline = deadline;
        this.requiredCache = requiredCache;
        this.requiredMemory = requiredMemory;
        this.requiredFrequency = requiredFrequency;
    }

    public Semaphore getMutex() {
        return mutex;
    }

    public void setMutex(Semaphore mutex) {
        this.mutex = mutex;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPriority() {
        return priority;
    }

    public void setPriority(double priority) {
        this.priority = priority;
    }

    public long getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(long arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public double getDeadline() {
        return deadline;
    }

    public void setDeadline(double deadline) {
        this.deadline = deadline;
    }

    public int getRequiredCache() {
        return requiredCache;
    }

    public void setRequiredCache(int requiredCache) {
        this.requiredCache = requiredCache;
    }

    public int getRequiredMemory() {
        return requiredMemory;
    }

    public void setRequiredMemory(int requiredMemory) {
        this.requiredMemory = requiredMemory;
    }

    public int getRequiredFrequency() {
        return requiredFrequency;
    }

    public void setRequiredFrequency(int requiredFrequency) {
        this.requiredFrequency = requiredFrequency;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }
    public double getBackPackPriority(){

        return this.getPriority() / getDeadline();
    }

    public static int compareTo(Task t1, Task t2){
        return Double.compare(t1.getBackPackPriority(),t2.getBackPackPriority()) ;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }
}