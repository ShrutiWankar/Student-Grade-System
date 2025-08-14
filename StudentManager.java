import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class StudentManager {
    private final List<Student> students = new ArrayList<>();

    public void addStudent(Student s) {
        students.add(s);
    }

    public boolean removeByRoll(int roll) {
        return students.removeIf(s -> s.getRollNo() == roll);
    }

    public Student findByRoll(int roll) {
        for (Student s : students) {
            if (s.getRollNo() == roll) return s;
        }
        return null;
    }

    public List<Student> searchByName(String namePart) {
        String q = namePart.toLowerCase(Locale.ROOT);
        return students.stream()
                .filter(s -> s.getName().toLowerCase(Locale.ROOT).contains(q))
                .collect(Collectors.toList());
    }

    public List<Student> getAll() {
        return new ArrayList<>(students);
    }

    public boolean isEmpty() {
        return students.isEmpty();
    }
}
