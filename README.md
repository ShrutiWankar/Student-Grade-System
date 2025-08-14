# Student Grade Management System — Enhanced (Java)

A Java console application that efficiently manages student records, calculates averages, assigns grades, and provides search, update, delete, and report export functionalities.

## ✨ Features
- Add students with variable number of subjects
- Automatic **average** and **grade** calculation
- View all students in a table
- Search by roll number or name
- Edit marks or remove a student
- Save all data in a CSV file
- Export grade reports to CSV  

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
