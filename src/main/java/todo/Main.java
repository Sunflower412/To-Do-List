package todo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/main.fxml"));
        primaryStage.setTitle("To-Do List");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}



//import java.util.Scanner;
//public class Main {
//    private static TaskManager taskManager = new TaskManager();
//    private static Scanner scanner = new Scanner(System.in);
//    public static void main(String[] args) {
////        Task task = new Task(1, Priority.Low, "String title", "String discription", true);
////        System.out.println(task.getTaskInfo());
//        while (true) {
//            String choice = getUserChoice();
//            if(choice == null) {
//                taskManager.saveTasks();
//                System.out.println("Программа завершена.");
//                return;
//            }
//            switch (choice){
//                case "1": addTask(); break;
//                case "2": showAllTasks(); break;
//                case "3": getTasksByStatus(); break;
//                case "4": markTaskAsCompleted(); break;
//                case "5": updateTask(); break;
//                case "6": deleteTask(); break;
//                default: System.out.println("Невеный ввод. Попробуйте снова.");
//            }
//        }
//    }
//
//    private static String getUserChoice(){
//        printMenu();
//        String choice = scanner.nextLine().trim();
//        if (choice.isEmpty()){
//            return getUserChoice();
//        }
//        if (choice.equals("0")){
//            return null;
//        }
//        return choice;
//    }
//
//    private static void printMenu(){
//        System.out.println("-------Список дел-------");
//        System.out.println("1. Добавить задачу");
//        System.out.println("2. Показать все задачи");
//        System.out.println("3. Показать задачи по статусу");
//        System.out.println("4. Пометить задачу, как выполненную");
//        System.out.println("5. Редактировать задачу");
//        System.out.println("6. Удалить задачу");
//        System.out.println("0. Завершить работу программы");
//    }
//    private static void addTask(){
//        System.out.println("Введите название: ");
//        String title = scanner.nextLine();
//        System.out.println("Введите описание: ");
//        String decription = scanner.nextLine();
//        System.out.println("Введите приоритет(Low, Medium, High): ");
//        Priority priority = Priority.valueOf(scanner.nextLine());
//        System.out.println("Введите Статус выполнения(В работе - false, Выполнено - true): ");
//        boolean isCompleted = scanner.nextBoolean();
//        scanner.nextLine();
//        taskManager.addTask(priority, title, decription, isCompleted);
//    }
//    private static void showAllTasks(){
//        taskManager.getAllTasks();
//    }
//    private static void getTasksByStatus(){
//        System.out.println("Показать только выполненные задачи - " +
//                "true");
//        System.out.println("Показать только невыполненные задачи - " +
//                "false");
//        boolean completed = scanner.nextBoolean();
//        scanner.nextLine();
//        if (completed == true) {
//        for (Task task : taskManager.getTasksByStatus(true)) {
//            System.out.println(task.getTaskInfo());
//            }
//        }
//        else {
//            for (Task task : taskManager.getTasksByStatus(false)) {
//                System.out.println(task.getTaskInfo());
//            }
//        }
//    }
//
//    private static void markTaskAsCompleted(){
//        System.out.println("Введите Id задачи: ");
//        int id = scanner.nextInt();
//        scanner.nextLine();
//        if(taskManager.markTaskAsCompleted(id)){
//            taskManager.saveTasks();
//            System.out.println("Задача помечена как выполненная.");
//            System.out.println();
//        }
//        else{
//            System.out.println("Задача не найдена");
//        }
//    }
//
//    private static void updateTask() {
//        try {
//            System.out.println("Введите id задачи для редактирования: ");
//            int id = scanner.nextInt();
//            scanner.nextLine();
//
//            Task task = taskManager.findTaskById(id);
//            if (task == null) {
//                System.out.println("Ошибка: задача с ID " + id + " не найдена!");
//                return;
//            }
//
//            System.out.println("Текущие данные задачи:");
//            System.out.println(task.getTaskInfo());
//
//            System.out.println("Введите новое название (или нажмите Enter чтобы оставить текущее): ");
//            String newTitle = scanner.nextLine().trim();
//            if (newTitle.isEmpty()) newTitle = task.getTitle();
//
//            System.out.println("Введите новое описание (или нажмите Enter чтобы оставить текущее): ");
//            String newDescription = scanner.nextLine().trim();
//            if (newDescription.isEmpty()) newDescription = task.getDescription();
//
//            System.out.println("Введите новый приоритет (Low, Medium, High) (или нажмите Enter чтобы оставить текущий): ");
//            String priorityInput = scanner.nextLine().trim();
//            Priority newPriority = task.getPriority();
//            if (!priorityInput.isEmpty()) {
//                try {
//                    newPriority = Priority.valueOf(priorityInput);
//                } catch (IllegalArgumentException e) {
//                    System.out.println("Ошибка: некорректный приоритет! Приоритет не изменён.");
//                }
//            }
//
//            System.out.println("Передаваемые данные: title=" + newTitle + ", description=" + newDescription + ", priority=" + newPriority); // Отладка
//            if (taskManager.updateTask(id, newTitle, newDescription, newPriority)) {
//
//                System.out.println("Задача успешно обновлена и сохранена!");
//            }
//                 else {
//                    System.out.println("Ошибка при сохранении изменений!");
//                }
//
//        } catch (Exception e) {
//            System.out.println("Ошибка при редактировании задачи: " + e.getMessage());
//            scanner.nextLine();
//        }
//    }
//    private static void deleteTask() {
//        System.out.println("Введите Id задачи: ");
//        int id = scanner.nextInt();
//        scanner.nextLine();
//        taskManager.removeTask(id);
//
//        }
//    }

