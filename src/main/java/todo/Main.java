package todo;

import todo.TaskManager;

import java.util.Scanner;
public class Main {
    private static TaskManager taskManager = new TaskManager();
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
//        Task task = new Task(1, Priority.Low, "String title", "String discription", true);
//        System.out.println(task.getTaskInfo());
        while (true) {
            String choice = getUserChoice();
            if(choice == null) {
                taskManager.saveTasks();
                System.out.println("Программа завершена.");
                return;
            }
            switch (choice){
                case "1": addTask(); break;
                case "2": showAllTasks(); break;
                case "3": getTasksByStatus(); break;
                case "4": markTaskAsCompleted(); break;
                case "5": updateTask(); break;
                case "6": deleteTask(); break;
                default: System.out.println("Невеный ввод. Попробуйте снова.");
            }
        }
    }

    private static String getUserChoice(){
        printMenu();
        String choice = scanner.nextLine().trim();
        if (choice.isEmpty()){
            return getUserChoice();
        }
        if (choice.equals("0")){
            return null;
        }
        return choice;
    }

    private static void printMenu(){
        System.out.println("-------Список дел-------");
        System.out.println("1. Добавить задачу");
        System.out.println("2. Показать все задачи");
        System.out.println("3. Показать задачи по статусу");
        System.out.println("4. Пометить задачу, как выполненную");
        System.out.println("5. Редактировать задачу");
        System.out.println("6. Удалить задачу");
        System.out.println("0. Завершить работу программы");
    }
    private static void addTask(){
        System.out.println("Введите название: ");
        String title = scanner.nextLine();
        System.out.println("Введите описание: ");
        String decription = scanner.nextLine();
        System.out.println("Введите приоритет(Low, Medium, High): ");
        Priority priority = Priority.valueOf(scanner.nextLine());
        System.out.println("Введите Статус выполнения(В работе - false, Выполнено - true): ");
        boolean isCompleted = scanner.nextBoolean();
        scanner.nextLine();
        taskManager.addTask(priority, title, decription, isCompleted);
    }
    private static void showAllTasks(){
        taskManager.getAllTasks();
    }
    private static void getTasksByStatus(){
        System.out.println("Показать только выполненные задачи - " +
                "true");
        System.out.println("Показать только невыполненные задачи - " +
                "false");
        boolean completed = scanner.nextBoolean();
        scanner.nextLine();
        if (completed == true) {
        for (Task task : taskManager.getTasksByStatus(true)) {
            System.out.println(task.getTaskInfo());
            }
        }
        else {
            for (Task task : taskManager.getTasksByStatus(false)) {
                System.out.println(task.getTaskInfo());
            }
        }
    }

    private static void markTaskAsCompleted(){
        System.out.println("Введите Id задачи: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        if(taskManager.markTaskAsCompleted(id)){
            taskManager.saveTasks();
            System.out.println("Задача помечена как выполненная.");
            System.out.println();
        }
        else{
            System.out.println("Задача не найдена");
        }
    }

    private static void updateTask(){
        System.out.println("Введите id задачи: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Введите новое название: ");
        String title = scanner.nextLine();
        System.out.println("Введите новое описание: ");
        String description = scanner.nextLine();
        System.out.println("Введите новый приоритет(Low, Medium, High): ");
        Priority priority = Priority.valueOf(scanner.nextLine());

        if (taskManager.updateTask(id, title, description, priority)){
            taskManager.saveTasks();
            System.out.println("Задача обновлена.");
        }
        else{
            System.out.println("Задачи с указанным id не найдена.");
        }
    }

    private static void deleteTask() {
        System.out.println("Введите Id задачи: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        taskManager.removeTask(id);

        }
    }

