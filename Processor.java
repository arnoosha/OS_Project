import java.util.*;

class Processor implements Runnable {
    private int id;
    private boolean isBusy;
    private long availableAt;
    private int cache;
    private int memory;
    private int frequency;
    ArrayList<Task> taskQueue;
    Thread processorThread;


    public Processor(int id, int cache, int memory, int frequency) {
        this.id = id;
        this.isBusy = false;
        this.availableAt = 0;
        this.cache = cache;
        this.memory = memory;
        this.frequency = frequency;
        this.taskQueue = new ArrayList<>();

    }

    public void run() {
        int lastTime = TaskManager.getInstance().getCurrentTime();
        while (true) {
            int currentTime = TaskManager.getInstance().getCurrentTime();
            if (lastTime < currentTime){
                this.setBusy(false);
                lastTime = currentTime;
            }
            requestTask();
            if (!taskQueue.isEmpty() && !isBusy) {
                processTask();
            }
            else {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isBusy() {
        return isBusy;
    }

    public void setBusy(boolean busy) {
        isBusy = busy;
    }

    public long getAvailableAt() {
        return availableAt;
    }

    public void setAvailableAt(long availableAt) {
        this.availableAt = availableAt;
    }

    public int getCache() {
        return cache;
    }

    public void setCache(int cache) {
        this.cache = cache;
    }

    public int getMemory() {
        return memory;
    }

    public void setMemory(int memory) {
        this.memory = memory;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public ArrayList<Task> getTaskQueue() {
        return taskQueue;
    }

    public void setTaskQueue(ArrayList<Task> taskQueue) {
        this.taskQueue = taskQueue;
    }

    public Thread getProcessorThread() {
        return processorThread;
    }

    public void setProcessorThread(Thread processorThread) {
        this.processorThread = processorThread;
    }

    public void requestTask() {
        if (this.isBusy()) {
            return;
        }
        ArrayList<Task> readyTasks = TaskManager.getInstance().proposeTasks(this);
        this.setTaskQueue(readyTasks);
        for (Task task : readyTasks
        ) {
            System.out.println("task with id " + task.getId() + " is ready for processor " + this.getId() + " at time "
                    + TaskManager.getInstance().getCurrentTime());
        }
        System.out.println("---------------------------");
    }

    public void processTask() {
        Random random = new Random();
        int rnd = 1;
        if (getTaskQueue().size() <= 3) {
            double probability = 0.8;

            double randomNumber = random.nextDouble();

            if (randomNumber < probability) {
                rnd = 1;
            } else {
                rnd = 0;
            }
        }

        ArrayList<Task> notDoneTasks = new ArrayList<>();
        for (Task task : getTaskQueue()) {
            if (!task.isDone()){
                notDoneTasks.add(task);
            }
        }
        if (notDoneTasks.size() <1){
            return;
        }


         if (rnd == 1){
        Task selectedTask = getTaskQueue().get(0);

        boolean processorHasDoneTask = TaskManager.getInstance().processorDoTask(this, selectedTask);
        if (processorHasDoneTask) {
            System.out.println("Task " + selectedTask.getId() +
                    " assigned to processor " + this.getId() + " at time : " + TaskManager.getInstance().getCurrentTime());

        } else {
            System.out.println("Processor " + this.getId() + " cant complete task "
                    + selectedTask.getId() + " at time : " + TaskManager.getInstance().getCurrentTime());
        }
       }
        else {
            System.out.println("number of ready tasks was not enough to select for processor " + this.getId());
        }
    }
}