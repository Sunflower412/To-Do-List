package todo;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class MainController {
    @FXML private TextField taskIdField;
    @FXML private TextField taskTitleField;
    @FXML private TextField taskDescriptionField;
    @FXML private ChoiceBox<String> priorityChoice;
    private TaskManager taskManager = new TaskManager();

    @FXML
    public void addTask() {
        String title = taskTitleField.getText();
        String description = taskDescriptionField.getText();
        Priority priority = Priority.valueOf(priorityChoice.getValue());

        taskTitleField.setText("");
        taskDescriptionField.setText("");
        priorityChoice.setValue(null);
    }

    @FXML
    public void showAllTasks() {

    }

    @FXML
    public void updateTask() {
        int id = Integer.parseInt(taskIdField.getText());
        String title = taskTitleField.getText();
        String description = taskDescriptionField.getText();
        Priority priority = Priority.valueOf(priorityChoice.getValue());
        if (taskManager.updateTask(id, title, description, priority)) {
            System.out.println("Задача обновлена!");
        }
        taskIdField.setText("");
        taskTitleField.setText("");
        taskDescriptionField.setText("");
        priorityChoice.setValue(null);
    }

    @FXML
    public void exit() {
        System.exit(0);
    }
}