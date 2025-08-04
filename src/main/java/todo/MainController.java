package todo;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Alert;

public class MainController {
    @FXML
    private Label titleLabel;
    @FXML
    private TextField taskIdField;
    @FXML
    private TextField taskTitleField;
    @FXML
    private TextField taskDescriptionField;
    @FXML
    private ComboBox<String> priorityChoice;
    @FXML
    private ListView<String> taskListView;
    @FXML
    private VBox formContainer;
    @FXML
    private Label idLabel;
    @FXML
    private Label titleLabelInput;
    @FXML
    private Label descLabel;
    @FXML
    private Label priorityLabel;
    @FXML
    private Button submitButton;
    private TaskManager taskManager = new TaskManager();
    private String currentAction = "";

    @FXML
    public void initialize() {
        updateTaskList();
    }

    @FXML
    public void showAddForm() {
        clearFields();
        currentAction = "add";
        idLabel.setVisible(false);
        taskIdField.setVisible(false);
        titleLabelInput.setVisible(true);
        taskTitleField.setVisible(true);
        descLabel.setVisible(true);
        taskDescriptionField.setVisible(true);
        priorityLabel.setVisible(true);
        priorityChoice.setVisible(true);
        submitButton.setText("Добавить задачу");
        submitButton.setVisible(true);
        formContainer.setVisible(true);
    }

    @FXML
    public void showUpdateForm() {
        clearFields();
        currentAction = "update";
        idLabel.setVisible(true);
        taskIdField.setVisible(true);
        titleLabelInput.setVisible(true);
        taskTitleField.setVisible(true);
        descLabel.setVisible(true);
        taskDescriptionField.setVisible(true);
        priorityLabel.setVisible(true);
        priorityChoice.setVisible(true);
        submitButton.setText("Обновить задачу");
        submitButton.setVisible(true);
        formContainer.setVisible(true);
    }

    @FXML
    public void showCompleteForm() {
        clearFields();
        currentAction = "complete";
        idLabel.setVisible(true);
        taskIdField.setVisible(true);
        titleLabelInput.setVisible(false);
        taskTitleField.setVisible(false);
        descLabel.setVisible(false);
        taskDescriptionField.setVisible(false);
        priorityLabel.setVisible(false);
        priorityChoice.setVisible(false);
        submitButton.setText("Пометить как выполненную");
        submitButton.setVisible(true);
        formContainer.setVisible(true);
    }

    @FXML
    public void showDeleteForm() {
        clearFields();
        currentAction = "delete";
        idLabel.setVisible(true);
        taskIdField.setVisible(true);
        titleLabelInput.setVisible(false);
        taskTitleField.setVisible(false);
        descLabel.setVisible(false);
        taskDescriptionField.setVisible(false);
        priorityLabel.setVisible(false);
        priorityChoice.setVisible(false);
        submitButton.setText("Удалить задачу");
        submitButton.setVisible(true);
        formContainer.setVisible(true);
    }

    @FXML
    public void showAllTasks() {
        updateTaskList();
        formContainer.setVisible(false);
    }

    @FXML
    public void submitAction() {
        if (currentAction.equals("add")) {
            String title = taskTitleField.getText().trim();
            String description = taskDescriptionField.getText().trim();
            String priorityStr = priorityChoice.getValue();
            if (title.isEmpty() || description.isEmpty() || priorityStr == null){
                showAlert("Ошибка!", "Все поля должны быть заполнены.");
                return;
            }
            Priority priority = Priority.valueOf(priorityStr);
            if (taskManager.addTask(priority, title, description, false)) {
                showAlert("Успех!", "Задача добавлена.");
                updateTaskList();
            } else {
                showAlert("Ошибка!", "Не удалось добавить задачу.");
            }

        } else if (currentAction.equals("update")) {
            if (taskIdField.getText().isEmpty()) {
                showAlert("Ошибка!", "Введите ID задачи.");
                return;
            }
            int id;
            try {
                id = Integer.parseInt(taskIdField.getText().trim());
            } catch (NumberFormatException e) {
                showAlert("Ошибка!", "ID должен быть числом.");
                return;
            }
            String title = taskTitleField.getText().trim();
            String description = taskDescriptionField.getText().trim();
            String priorityStr = priorityChoice.getValue();
            if (title.isEmpty() || description.isEmpty() || priorityStr == null){
                showAlert("Ошибка!", "Все поля должны быть заполнены.");
                return;
            }
            Priority priority = Priority.valueOf(priorityStr);
            if (taskManager.updateTask(id, title, description, priority)) {
                showAlert("Успех!", "Задача обновлена.");
            } else {
                showAlert("Ошибка!", "Задача с ID " + id + " не найдена!");
            }
            updateTaskList();
        } else if (currentAction.equals("complete")) {
            if (taskIdField.getText().isEmpty()) {
                showAlert("Ошибка!", "Введите ID задачи.");
                return;
            }
            int id;
            try {
                id = Integer.parseInt(taskIdField.getText().trim());
            } catch (NumberFormatException e) {
                showAlert("Ошибка!", "ID должен быть числом.");
                return;
            }
            if (taskManager.markTaskAsCompleted(id)) {
                showAlert("Успех!", "Задача выполнена.");
                updateTaskList();
            } else {
                showAlert("Ошибка!", "Задача с ID " + id + " не найдена!");
            }
        } else if (currentAction.equals("delete")) {
            if (taskIdField.getText().isEmpty()) {
                showAlert("Ошибка!", "Введите ID задачи.");
                return;
            }
            int id;
            try {
                id = Integer.parseInt(taskIdField.getText().trim());
            } catch (NumberFormatException e) {
                showAlert("Ошибка!", "ID должен быть числом.");
                return;
            }
            if (taskManager.removeTask(id)) {
                showAlert("Успех!", "Задача удалена.");
                updateTaskList();
            } else {
                showAlert("Ошибка!", "Задача с ID " + id + " не найдена!");
            }
        }
        formContainer.setVisible(false);
        clearFields();
    }

    @FXML
    public void exit() {
        Stage stage = (Stage) taskIdField.getScene().getWindow();
        stage.close();
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


    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}