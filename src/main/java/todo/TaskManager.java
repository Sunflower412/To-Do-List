package todo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TaskManager {

    private List<Task> tasks;

    public TaskManager() {
        tasks = new FileManager().loadTasks();
        if(tasks == null){
            tasks = new ArrayList<>();
        }
    }

    public void addTask(Priority priority, String title, String discription, boolean isCompleted){
        int id = tasks.size() + 1;
        tasks.add(new Task(id, priority, title, discription, isCompleted));
    }

    public void getAllTasks(){
        System.out.println("Список всех задач: ");
        for (Task task : tasks){
            System.out.println();
            System.out.println(task.getTaskInfo());
        }
    }
    public List<Task> getTasksByStatus(boolean completed){
        return tasks.stream().filter(task -> task.isCompleted() == completed).collect(Collectors.toList());
    }

    public Task findTaskById(int id){
        return tasks.stream().filter(task -> task.getId() == id).findFirst().orElse(null);
    }

    public boolean markTaskAsCompleted(int id){
        Task task = findTaskById(id);
        if (task != null){
            task.setCompleted(true);
            return true;
        }
        else {
            System.out.println("Задачи с таким id не существует.");
            return false;
        }
    }

    public boolean updateTask(int id, String title, String description, Priority priority){
        Task task = findTaskById(id);
        if (task != null) {
            task.setTitle(title);
            task.setDiscription(description);
            task.setPriority(priority);
            System.out.println("Задача отредактирована.");
            return true;
        }
        else {
            System.out.println("Задачи с таким id не существует.");
            return false;
        }
    }

    public void removeTask(int id){
        Task task = findTaskById(id);
        if (task != null) {
            tasks.remove(task);
            System.out.println("Задача удалена из списка.");
        }
        else {
            System.out.println("Задачи с таким id не существует.");
        }
    }
    public void saveTasks(){
        new FileManager().saveTasks(tasks);
    }

}
