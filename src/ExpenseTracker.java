import java.io.*;
import java.util.*;

class Expense {
    String date;
    String category;
    double amount;

    Expense(String date, String category, double amount) {
        this.date = date;
        this.category = category;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return date + "," + category + "," + amount;
    }
}

public class ExpenseTracker {
    static final String FILE_NAME = "expenses.csv";
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
    

    do {
        System.out.println("\n=== EXPENSE TRACKER ===");
        System.out.println("1. Add Expense");
        System.out.println("2. View Expenses");
        System.out.println("3. Exit");
        System.out.print("Choose an option: ");
        choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1: addExpense();
            case 2:viewExpenses();
            case 3:System.out.println("Goodbye!");
            default: System.out.println("Invalid option, try again.");
        }
    } while (choice != 3);
}

static void addExpense() {
    try {
        System.out.print("Enter date (YYYY-MM-DD): ");
        String date = scanner.nextLine();

        System.out.print("Enter Category (Food, Transport, etc.): ");
        String category = scanner.nextLine();

        System.out.print("Enter amount ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        Expense expense = new Expense(date, category, amount);

        // Append to CSV
        BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true));
        writer.write(expense.toString());
        writer.newLine();
        writer.close();

        System.out.println("Expense added!");
    } catch (IOException e) {
        System.out.println("Error writing to file.");
        e.printStackTrace();
    }
}

static void viewExpenses() {
    try {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            System.out.println("No expenses recorded yet.");
            return;
        }

        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        System.out.println("\n--- Your Expenses ---");
        System.out.printf("%-12s %-15s %-10s%n", "Date", "Category", "Amount");

        while ((line = reader.readLine()) != null) {
            String[] data = line.split(",");
            System.out.printf("%-12s %-15s %-10s%n", data[0], data[1], data[2]);
        }
        reader.close();
        } catch (IOException e) {
        System.out.println("Error reading the file.");
        e.printStackTrace();
        }
    }
}
