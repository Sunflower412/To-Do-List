public class Task {

    private int id;
    private Priority priority;
    private String title;
    private String discription;
    private boolean isCompleted;


    public Task(int id, Priority priority, String title, String discription, boolean isCompleted) {
        this.id = id;
        this.priority = priority;
        this.title = title;
        this.discription = discription;
        this.isCompleted = isCompleted;
    }


    public int getId() {
        return id;
    }

    public int setId(int id) {
        if (id >= 0){
            return id;
        }
        else{
            throw new IllegalArgumentException("Недопустимое значение для id. Возможно Вы ввели пустую строку или введенное id содержит нечисленное значение.");
        }
    }

    public Priority getPriority() {
        return priority;
    }

    public Priority setPriority(Priority priority) {
        if (priority != null){
            return priority;
        }
        else {
            throw new IllegalArgumentException("Введена пустая строка.");
        }
    }

    public String getDiscription() {
        return discription;
    }

    public String setDiscription(String discription) {
        return discription;
    }

    public String getTitle() {
        return title;
    }

    public String setTitle(String title) {
        return title;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public boolean setCompleted(boolean completed) {
        return completed;
    }

    public String getTaskInfo(){
        return "TASK INFO: " + "\n" + "Id: " + id + "\n" + "Приоритет: " + priority + "\n"
                + "Название: " + title + "\n" + "Описание: " + discription +
                "\n" + "Статус: " + isCompleted;
    }
}
