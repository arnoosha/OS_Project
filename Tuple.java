public class Tuple {
    private Task task;
    private Processor processor;
    private int time;

    public Tuple(Task task, Processor processor,int time) {
        this.task = task;
        this.processor = processor;
        this.time = time;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Processor getProcessor() {
        return processor;
    }

    public void setProcessor(Processor processor) {
        this.processor = processor;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
