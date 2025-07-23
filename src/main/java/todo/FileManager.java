package todo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    private static final String FILE_PATH = "tasks.json";
    private final Gson gson;

    public FileManager() {
        gson = new GsonBuilder().create(); // Завершаем создание Gson
    }

    // Сохранить задачи в файл
    public void saveTasks(List<Task> tasks) {
        try (Writer writer = new FileWriter(FILE_PATH)) {
            gson.toJson(tasks, writer);
        } catch (IOException e) {
            System.err.println("Ошибка при сохранении: " + e.getMessage());
        }
    }

    // Загрузить задачи из файла
    public List<Task> loadTasks() {
        try (Reader reader = new FileReader(FILE_PATH)) {
            return gson.fromJson(reader, new TypeToken<List<Task>>(){}.getType());
        } catch (IOException e) {
            System.err.println("Ошибка при загрузке: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}