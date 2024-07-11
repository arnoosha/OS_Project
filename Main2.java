import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;


public class Main2 {
    public static void main(String[] args) throws InterruptedException {

        TaskManager taskManager = TaskManager.getInstance();
        ArrayList<Processor> allProcessors = new ArrayList<>();
        Semaphore sem = new Semaphore(1);

        String processorFile = "D:/OS/Project/OS_PROJECT/src/processors2.csv";
        try (BufferedReader br = new BufferedReader(new FileReader(processorFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith("processor_id")) continue;
                String[] values = line.split(",");
                int id = Integer.parseInt(values[0]);
                int cache = Integer.parseInt(values[1]);
                int memory = Integer.parseInt(values[2]);
                int frequency = Integer.parseInt(values[3]);
                Processor processor = new Processor(id, cache, memory, frequency);
                allProcessors.add(processor);
//                Thread processorThread = new Thread(processor);
//                processor.setProcessorThread(processorThread);
//                processorThread.start();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String taskFile = "D:/OS/Project/OS_PROJECT/src/tasks2.csv";
        try (BufferedReader br = new BufferedReader(new FileReader(taskFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith("task_id")) continue;
                String[] values = line.split(",");
                int id = Integer.parseInt(values[0]);
                long arrivalTime = Long.parseLong(values[1]);
                long deadline = Long.parseLong(values[2]);
                int priority = Integer.parseInt(values[3]);
                int requiredCache = Integer.parseInt(values[4]);
                int requiredMemory = Integer.parseInt(values[5]);
                int requiredFrequency = Integer.parseInt(values[6]);
                taskManager.addTask(new Task(id, priority, arrivalTime, 1, deadline, requiredCache, requiredMemory, requiredFrequency));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<Thread> threads = new ArrayList<>();

        for (Processor p : allProcessors
        ) {
            Thread thread
                    = new Thread(p);
            thread.start();
            threads.add(thread);
        }


        for (int i = 0; i <1000000000l; i++) {
        }

        int repeat = 6;
        for (int currentTime = 0; currentTime <= repeat; currentTime++) {

            taskManager.setCurrentTime(currentTime);
            System.out.println("**************************************** " + currentTime + " ***************************");
            ArrayList<Task> taskQueueCopy = new ArrayList<>(taskManager.taskQueue);
            for (Task task : taskQueueCopy) {
                System.out.println("task " + task.getId() + " is in taskQueue at time " + currentTime);
            }



            Thread.sleep(3000);


        }


        for (Thread t: threads
        ) {
            t.stop();
        }

        taskManager.getSemaphore().acquire();
        taskManager.printCompletedTasks();
        taskManager.getSemaphore().release();


    }
}