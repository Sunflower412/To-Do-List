package todo;

public class Main {
    public static void main(String[] args) {
        Task task = new Task(1, Priority.Low, "String title", "String discription", true);
        System.out.println(task.getTaskInfo());
    }
}