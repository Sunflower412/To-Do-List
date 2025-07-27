package todo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TaskManager {
    private List<Task> tasks;
    private final FileManager fileManager;

    public TaskManager() {
        fileManager = new FileManager();
        tasks = fileManager.loadTasks();
        if (tasks == null) tasks = new ArrayList<>();
    }


    public void addTask(Priority priority, String title, String description, boolean isCompleted){
        int id = tasks.size() + 1;
        tasks.add(new Task(id, priority, title, description, isCompleted));
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

    public Task findTaskById(int id) {
        return tasks.stream()
                .filter(t -> t.getId() == id)
                .findFirst()
                .orElse(null);
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

    public boolean updateTask(int id, String title, String description, Priority priority) {
        Task task = findTaskById(id);
        if (task != null) {
            // Используем текущее значение, если новое пустое
            if (title == null || title.trim().isEmpty()) title = task.getTitle();
            if (description == null || description.trim().isEmpty()) description = task.getDescription();
            if (priority == null) priority = task.getPriority();

            task.setTitle(title);
            task.setDescription(description);
            task.setPriority(priority);
            System.out.println("Обновлённая задача: " + task.getTaskInfo()); // Отладка
            return saveTasks();
        } else {
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
    public boolean saveTasks() {
        System.out.println("Список перед сохранением: " + tasks); // Отладка
        return fileManager.saveTasks(tasks);
    }
}
