import java.io.*;
import java.util.*;

public class FileHandler {
    // CSV format: rollNo,name,mark1;mark2;mark3;...
    public static void save(String path, List<Student> students) throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(path))) {
            pw.println("rollNo,name,marks");
            for (Student s : students) {
                StringBuilder sb = new StringBuilder();
                sb.append(s.getRollNo()).append(",");
                sb.append(s.getName()).append(",");
                List<Double> ms = s.getMarks();
                for (int i = 0; i < ms.size(); i++) {
                    sb.append(ms.get(i));
                    if (i < ms.size() - 1) sb.append(";");
                }
                pw.println(sb.toString());
            }
        }
    }

    public static List<Student> load(String path) throws IOException {
        List<Student> list = new ArrayList<>();
        File f = new File(path);
        if (!f.exists()) return list;
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line = br.readLine(); // header
            while ((line = br.readLine()) != null) {
                String[] parts = splitCsv(line);
                if (parts.length < 3) continue;
                int roll = Integer.parseInt(parts[0].trim());
                String name = parts[1].trim();
                String[] markStrs = parts[2].split(";");
                List<Double> marks = new ArrayList<>();
                for (String m : markStrs) {
                    if (m.isBlank()) continue;
                    marks.add(Double.parseDouble(m.trim()));
                }
                list.add(new Student(name, roll, marks));
            }
        }
        return list;
    }

    private static String[] splitCsv(String line) {
        // naive split (no quotes used in our simple CSV)
        return line.split(",", -1);
    }
}
