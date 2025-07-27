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
        gson = new GsonBuilder().setPrettyPrinting().create();
    }

    // Сохранить задачи в файл с возвращаемым значением
    public boolean saveTasks(List<Task> tasks) {
        File file = new File(FILE_PATH);
        try (Writer writer = new FileWriter(file)) {
            System.out.println("Сохранение в: " + file.getAbsolutePath()); // Отладка
            gson.toJson(tasks, writer);
            System.out.println("Сохранение успешно.");
            return true;
        } catch (IOException e) {
            System.err.println("Ошибка при сохранении в " + file.getAbsolutePath() + ": " + e.getMessage());
            return false;
        }
    }

    // Загрузить задачи из файла
    public List<Task> loadTasks() {
        File file = new File(FILE_PATH);
        try (Reader reader = new FileReader(file)) {
            return gson.fromJson(reader, new TypeToken<List<Task>>(){}.getType());
        } catch (IOException e) {
            System.err.println("Ошибка при загрузке из " + file.getAbsolutePath() + ": " + e.getMessage());
            return new ArrayList<>();
        }
    }
}