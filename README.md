# Student Grade Management System — Enhanced (Java)

A resume-ready Java console application to manage students, calculate averages, assign grades, search, update, delete, and export grade reports.

## ✨ Features
- Add students with variable number of subjects
- Automatic **average** and **grade** calculation
- List all students (tabular view)
- **Search** by roll or name
- **Update** marks and **Delete** student
- **Persist** data to CSV (`students.csv`), auto-save on exit
- **Export** grade report to CSV (roll, name, average, grade)

## 🧰 Tech Stack
- Java 17+ (works with 8+ if you remove the arrow `->` switch)
- OOP, Collections
- Simple CSV file I/O

## 🚀 Run
```bash
javac src/*.java
java -cp src Main
```

> On Windows PowerShell, use: `java -cp src Main`

## 📁 Project Structure
```
StudentGradeSystem-Enhanced/
├── src/
│   ├── Main.java
│   ├── Student.java
│   ├── StudentManager.java
│   ├── GradeCalculator.java
│   ├── FileHandler.java
│   └── ReportExporter.java
├── students.csv        # created after first save/exit
├── README.md
└── .gitignore
```

## 📝 Notes
- Data file format: `rollNo,name,mark1;mark2;...`
- If you compile with Java 8, replace the modern `switch` arrow cases with classic `case:` blocks.

## 📄 License
MIT
