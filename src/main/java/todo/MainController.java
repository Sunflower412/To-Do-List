package todo;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class MainController {
    @FXML private TextField taskIdField;
    @FXML private TextField taskTitleField;
    @FXML private TextField taskDescriptionField;
    @FXML private ChoiceBox<String> priorityChoice;
    @FXML private ListView<String> taskListView;
    private TaskManager taskManager = new TaskManager();

    @FXML
    public void addTask() {
        String title = taskTitleField.getText();
        String description = taskDescriptionField.getText();
        String priority = priorityChoice.getValue();

        Priority newPriority = Priority.valueOf(priority);
        if (taskManager.addTask(newPriority, title, description, false)){
            showAlert("Успех!", "Задача добавлена.");
            updateTaskList();
        } else {
            showAlert("Ошибка!", "Не удалось добавить задачу.");
        }

        clearFields();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    public void showAllTasks() {
        updateTaskList();
    }

    @FXML
    public void updateTask() {
        if (taskIdField.getText().isEmpty()){
            showAlert("Ошибка!", "Введите Id задачи.");
            return;
        }
        int id;
        try {
            id = Integer.parseInt(taskIdField.getText().trim());
        } catch (NumberFormatException e){
            showAlert("Ошибка!", "Id должен быть числом.");
            return;
        }
        String title = taskTitleField.getText().trim();
        String description = taskDescriptionField.getText().trim();
        String priorityStr = priorityChoice.getValue();
        if (title.isEmpty() || description.isEmpty() || priorityStr == null) {
            showAlert("Ошибка!", "Все поля должны быть заполнены.");
        }
        Priority priority = Priority.valueOf(priorityStr);
        if (taskManager.updateTask(id, title, description, priority)){
            showAlert("Успех!", "Задача обновлена.");
        } else {
            showAlert("Ошибка!", "Задача с Id: " + id + " не найдена!");
        }
        updateTaskList();
        clearFields();
    }

    @FXML
    public void exit() {
        System.exit(0);
    }

    private void updateTaskList() {
        taskListView.getItems().clear();
        for (Task task : taskManager.getAllTasks()) {
            taskListView.getItems().add(task.getTaskInfo());
        }
    }
    private void clearFields() {
        taskIdField.setText("");
        taskTitleField.setText("");
        taskDescriptionField.setText("");
        priorityChoice.setValue(null);
    }


    }

