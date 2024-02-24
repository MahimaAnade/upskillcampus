import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ExpenseTracker {
    private static Scanner scanner = new Scanner(System.in);
    private static Map<String, List<Expense>> expensesByCategory = new HashMap<>();

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            System.out.println("Expense Tracker Menu:");
            System.out.println("1. Record Expense");
            System.out.println("2. View Expenses by Category");
            System.out.println("3. View Total Expenses");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    recordExpense();
                    break;
                case 2:
                    viewExpensesByCategory();
                    break;
                case 3:
                    viewTotalExpenses();
                    break;
                case 4:
                    running = false;
                    System.out.println("Exiting... Thank you for using Expense Tracker!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void recordExpense() {
        System.out.println("Enter expense details:");
        System.out.print("Date (YYYY-MM-DD): ");
        String date = scanner.nextLine();
        System.out.print("Amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume newline
        System.out.print("Category: ");
        String category = scanner.nextLine();
        System.out.print("Description: ");
        String description = scanner.nextLine();

        Expense expense = new Expense(date, amount, category, description);
        expensesByCategory.computeIfAbsent(category, k -> new ArrayList<>()).add(expense);

        System.out.println("Expense recorded successfully!");
    }

    private static void viewExpensesByCategory() {
        System.out.println("Enter category to view expenses:");
        String category = scanner.nextLine();
        List<Expense> expenses = expensesByCategory.getOrDefault(category, new ArrayList<>());
        if (expenses.isEmpty()) {
            System.out.println("No expenses found for category: " + category);
        } else {
            System.out.println("Expenses for category: " + category);
            for (Expense expense : expenses) {
                System.out.println(expense);
            }
        }
    }

    private static void viewTotalExpenses() {
        double totalExpenses = 0;
        for (List<Expense> expenses : expensesByCategory.values()) {
            for (Expense expense : expenses) {
                totalExpenses += expense.getAmount();
            }
        }
        System.out.println("Total expenses: $" + totalExpenses);
    }

    static class Expense {
        private String date;
        private double amount;
        private String category;
        private String description;

        public Expense(String date, double amount, String category, String description) {
            this.date = date;
            this.amount = amount;
            this.category = category;
            this.description = description;
        }

        public String getDate() {
            return date;
        }

        public double getAmount() {
            return amount;
        }

        public String getCategory() {
            return category;
        }

        public String getDescription() {
            return description;
        }

        @Override
        public String toString() {
            return "Date: " + date +
                    ", Amount: $" + amount +
                    ", Category: " + category +
                    ", Description: " + description;
        }
    }
}
