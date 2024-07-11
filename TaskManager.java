import java.util.*;
import java.util.concurrent.Semaphore;
import java.util.stream.Collectors;
import java.util.stream.Collectors;

public class TaskManager {

    private static TaskManager instance;

    private TaskManager() {
    }

    public static synchronized TaskManager getInstance() {
        if (instance == null) {
            instance = new TaskManager();
        }
        return instance;
    }


    Comparator<Task> taskComparator = Comparator.comparingDouble((Task t) -> t.getBackPackPriority()).reversed();

    ArrayList<Task> taskQueue = new ArrayList<>();

    ArrayList<Tuple> completedTasks = new ArrayList<>();

    Semaphore semaphore = new Semaphore(1);

    private int currentTime = 0;


    public void addTask(Task task) {
        taskQueue.add(task);

    }

    public ArrayList<Task> getArrivedTasks() {
        ArrayList<Task> arrivedTasks = new ArrayList<>();
        for (Task task : taskQueue) {
            if (task.getArrivalTime() <= getCurrentTime() && task.getDeadline() >= getCurrentTime() + 1) {
                arrivedTasks.add(task);
            }
        }
        arrivedTasks.sort(taskComparator);
        return arrivedTasks;
    }

    public ArrayList<Task> proposeTasks(Processor processor) {
        try {
            semaphore.acquire();
            ArrayList<Task> proposedTasks = new ArrayList<>();
            for (Task task : getArrivedTasks()) {
                if (task.getRequiredCache() <= processor.getCache() &&
                        task.getRequiredMemory() <= processor.getMemory() &&
                        task.getRequiredFrequency() <= processor.getFrequency()) {
                    proposedTasks.add(task);
                }
            }
            proposedTasks.sort(Comparator.comparingDouble((Task t) -> t.getBackPackPriority()).reversed());

            return proposedTasks;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            semaphore.release();
        }
        return null;
    }

    public boolean processorDoTask(Processor processor, Task task) {
        Semaphore mutex = task.getMutex();
        try {
            mutex.acquire(); // this mutex is for each task that is to be done
            if (!task.isDone() ) {
                processor.setBusy(true);
                semaphore.acquire(); //semaphore for list of all tasks
                taskQueue.remove(task);
                task.setDone(true);
                processor.getTaskQueue().remove(task);
                completedTasks.add(new Tuple(task, processor,currentTime));
                return true;
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            semaphore.release();
            mutex.release();
        }
        return false;
    }

    public void printCompletedTasks() {
        System.out.println("Completed Tasks:");
        int sum = 0;
        for (Tuple tuple : completedTasks) {
            Task task = tuple.getTask();
            Processor processor = tuple.getProcessor();
            int time = tuple.getTime();
            sum += task.getPriority();
            System.out.println("Task ID: " + task.getId() + "  Value: " + task.getPriority() + "  Processor ID: " + processor.getId() + " at time : " + time);
        }
        System.out.println("Total value : " + sum);

    }


    public int getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(int currentTime) {
        this.currentTime = currentTime;
    }

    public Semaphore getSemaphore() {
        return semaphore;
    }

    public void setSemaphore(Semaphore semaphore) {
        this.semaphore = semaphore;
    }
}

