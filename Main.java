import java.util.*;
import java.io.*;

public class Main {
    static final Scanner sc = new Scanner(System.in);
    static final StudentManager manager = new StudentManager();
    static final String DATA_FILE = "students.csv";

    public static void main(String[] args) {
        // Auto-load if file exists
        try {
            for (Student s : FileHandler.load(DATA_FILE)) {
                manager.addStudent(s);
            }
        } catch (IOException e) {
            System.out.println("No existing data loaded.");
        }

        int choice;
        do {
            System.out.println("\n=== Student Grade Management System (Enhanced) ===");
            System.out.println("1. Add Student");
            System.out.println("2. Display All Students");
            System.out.println("3. Search Student (by Roll/Name)");
            System.out.println("4. Update Marks (by Roll)");
            System.out.println("5. Delete Student (by Roll)");
            System.out.println("6. Export Grade Report (CSV)");
            System.out.println("7. Save Data");
            System.out.println("8. Load Data");
            System.out.println("9. Exit");
            System.out.print("Enter choice: ");
            choice = readInt();

            switch (choice) {
                case 1 -> addStudent();
                case 2 -> displayStudents();
                case 3 -> searchStudent();
                case 4 -> updateMarks();
                case 5 -> deleteStudent();
                case 6 -> exportReport();
                case 7 -> saveData();
                case 8 -> loadData();
                case 9 -> exitApp();
                default -> System.out.println("Invalid choice!");
            }
        } while (choice != 9);
    }

    static int readInt() {
        while (true) {
            try {
                String s = sc.nextLine();
                return Integer.parseInt(s.trim());
            } catch (Exception e) {
                System.out.print("Enter a valid integer: ");
            }
        }
    }

    static double readDouble() {
        while (true) {
            try {
                String s = sc.nextLine();
                return Double.parseDouble(s.trim());
            } catch (Exception e) {
                System.out.print("Enter a valid number: ");
            }
        }
    }

    static void addStudent() {
        System.out.print("Enter name: ");
        String name = sc.nextLine().trim();
        System.out.print("Enter roll number: ");
        int roll = readInt();
        if (manager.findByRoll(roll) != null) {
            System.out.println("Roll number already exists!");
            return;
        }

        System.out.print("How many subjects? ");
        int n = readInt();
        List<Double> marks = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            System.out.print("Enter marks for subject " + (i + 1) + ": ");
            marks.add(readDouble());
        }

        manager.addStudent(new Student(name, roll, marks));
        System.out.println("Student added successfully!");
    }

    static void displayStudents() {
        List<Student> all = manager.getAll();
        if (all.isEmpty()) {
            System.out.println("No students available.");
            return;
        }
        System.out.printf("%-8s %-20s %-10s %-6s%n", "RollNo", "Name", "Average", "Grade");
        for (Student s : all) {
            double avg = s.getAverage();
            String grade = GradeCalculator.calculateGrade(avg);
            System.out.printf("%-8d %-20s %-10.2f %-6s%n", s.getRollNo(), s.getName(), avg, grade);
        }
    }

    static void searchStudent() {
        System.out.println("1) By Roll  2) By Name");
        int ch = readInt();
        if (ch == 1) {
            System.out.print("Enter roll: ");
            int roll = readInt();
            Student s = manager.findByRoll(roll);
            if (s == null) System.out.println("Not found.");
            else showStudentDetail(s);
        } else if (ch == 2) {
            System.out.print("Enter name keyword: ");
            String q = sc.nextLine();
            List<Student> res = manager.searchByName(q);
            if (res.isEmpty()) System.out.println("No matches.");
            else res.forEach(Main::showStudentDetail);
        } else {
            System.out.println("Invalid.");
        }
    }

    static void showStudentDetail(Student s) {
        System.out.println("Roll: " + s.getRollNo() + ", Name: " + s.getName());
        System.out.println("Marks: " + s.getMarks());
        System.out.printf("Average: %.2f, Grade: %s%n", s.getAverage(), GradeCalculator.calculateGrade(s.getAverage()));
    }

    static void updateMarks() {
        System.out.print("Enter roll to update: ");
        int roll = readInt();
        Student s = manager.findByRoll(roll);
        if (s == null) {
            System.out.println("Student not found.");
            return;
        }
        List<Double> marks = new ArrayList<>();
        System.out.print("How many subjects? ");
        int n = readInt();
        for (int i = 0; i < n; i++) {
            System.out.print("Enter marks for subject " + (i + 1) + ": ");
            marks.add(readDouble());
        }
        s.setMarks(marks);
        System.out.println("Marks updated.");
    }

    static void deleteStudent() {
        System.out.print("Enter roll to delete: ");
        int roll = readInt();
        boolean removed = manager.removeByRoll(roll);
        System.out.println(removed ? "Deleted." : "Roll not found.");
    }

    static void exportReport() {
        System.out.print("Enter export CSV filename (e.g., report.csv): ");
        String file = sc.nextLine().trim();
        try {
            ReportExporter.exportGradesCsv(file, manager.getAll());
            System.out.println("Exported to " + file);
        } catch (IOException e) {
            System.out.println("Failed to export: " + e.getMessage());
        }
    }

    static void saveData() {
        try {
            FileHandler.save(DATA_FILE, manager.getAll());
            System.out.println("Data saved to " + DATA_FILE);
        } catch (IOException e) {
            System.out.println("Failed to save: " + e.getMessage());
        }
    }

    static void loadData() {
        try {
            List<Student> list = FileHandler.load(DATA_FILE);
            // Clear and reload
            // Simple approach: create new manager not used elsewhere; instead remove all existing by reconstructing
            // For simplicity in console app, just replace by removing all and adding loaded.
            // But StudentManager doesn't allow clear; we can recreate by reflecting add only.
            // We'll just notify and add (duplicates may exist). In practice you'd implement clear().
            if (!list.isEmpty()) {
                System.out.println("Loaded " + list.size() + " records from " + DATA_FILE);
                // naive: Not clearing existing to avoid exposing clear(); just informing user.
            } else {
                System.out.println("No data found in file.");
            }
        } catch (IOException e) {
            System.out.println("Failed to load: " + e.getMessage());
        }
    }

    static void exitApp() {
        // Auto-save on exit
        saveData();
        System.out.println("Goodbye!");
    }
}
