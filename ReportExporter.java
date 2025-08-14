import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ReportExporter {
    public static void exportGradesCsv(String path, List<Student> students) throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(path))) {
            pw.println("rollNo,name,average,grade");
            for (Student s : students) {
                double avg = s.getAverage();
                String grade = GradeCalculator.calculateGrade(avg);
                pw.printf("%d,%s,%.2f,%s%n", s.getRollNo(), s.getName(), avg, grade);
            }
        }
    }
}
