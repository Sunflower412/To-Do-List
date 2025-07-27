package todo;

public class Task {
    private int id;
    private Priority priority;
    private String title;
    private String description;
    private boolean isCompleted;

    public Task() {} // Конструктор по умолчанию для Gson

    public Task(int id, Priority priority, String title, String description, boolean isCompleted) {
        this.id = id;
        this.priority = priority;
        this.title = title;
        this.description = description;
        this.isCompleted = isCompleted;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Priority getPriority() { return priority; }
    public void setPriority(Priority priority) { this.priority = priority; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public boolean isCompleted() { return isCompleted; }
    public void setCompleted(boolean isCompleted) { this.isCompleted = isCompleted; }

    public String getTaskInfo() {
        return "TASK INFO: \nId: " + id + "\nПриоритет: " + priority + "\nНазвание: " + title +
                "\nОписание: " + description + "\nСтатус: " + isCompleted;
    }
}