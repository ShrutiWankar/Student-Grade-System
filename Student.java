import java.util.ArrayList;
import java.util.List;

public class Student {
    private String name;
    private int rollNo;
    private List<Double> marks;

    public Student(String name, int rollNo, List<Double> marks) {
        this.name = name;
        this.rollNo = rollNo;
        this.marks = new ArrayList<>(marks);
    }

    public String getName() {
        return name;
    }

    public int getRollNo() {
        return rollNo;
    }

    public List<Double> getMarks() {
        return new ArrayList<>(marks);
    }

    public void setMarks(List<Double> newMarks) {
        this.marks = new ArrayList<>(newMarks);
    }

    public double getAverage() {
        if (marks.isEmpty()) return 0.0;
        double sum = 0.0;
        for (double m : marks) sum += m;
        return sum / marks.size();
    }
}
