import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Studentgradetracker {
    static class Student {
        String name;
        double grade;
        Student(String name, double grade) {
            this.name = name;
            this.grade = grade;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ArrayList<Student> students = new ArrayList<>();
            int n = 0;
            while (n <= 0) {
                String input = JOptionPane.showInputDialog(null, "Enter number of students:", "Student Grade Tracker", JOptionPane.QUESTION_MESSAGE);
                if (input == null) return;
                try {
                    n = Integer.parseInt(input);
                } catch (NumberFormatException _) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid number.");
                }
            }
            for (int i = 0; i < n; i++) {
                String name = JOptionPane.showInputDialog(null, "Enter student name:", "Student " + (i+1), JOptionPane.QUESTION_MESSAGE);
                if (name == null) return;
                double grade = -1;
                while (grade < 0) {
                    String gradeInput = JOptionPane.showInputDialog(null, "Enter grade for " + name + ":", "Student " + (i+1), JOptionPane.QUESTION_MESSAGE);
                    if (gradeInput == null) return;
                    try {
                        grade = Double.parseDouble(gradeInput);
                        if (grade < 0) JOptionPane.showMessageDialog(null, "Grade must be non-negative.");
                    } catch (NumberFormatException _) {
                        JOptionPane.showMessageDialog(null, "Please enter a valid grade.");
                    }
                }
                students.add(new Student(name, grade));
            }
            if (students.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No students entered.");
                return;
            }
            double total = 0;
            double highest = students.get(0).grade;
            double lowest = students.get(0).grade;
            for (Student s : students) {
                total += s.grade;
                if (s.grade > highest) highest = s.grade;
                if (s.grade < lowest) lowest = s.grade;
            }
            double average = total / students.size();
            StringBuilder report = new StringBuilder();
            report.append("--- Student Grades Summary ---").append(System.lineSeparator());
            for (Student s : students) {
                report.append(String.format("%s: %.2f%n", s.name, s.grade));
            }
            report.append(String.format("Average grade: %.2f%n", average));
            report.append(String.format("Highest grade: %.2f%n", highest));
            report.append(String.format("Lowest grade: %.2f%n", lowest));
            JTextArea textArea = new JTextArea(report.toString());
            textArea.setEditable(false);
            textArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setPreferredSize(new Dimension(350, 200));
            JOptionPane.showMessageDialog(null, scrollPane, "Summary Report", JOptionPane.INFORMATION_MESSAGE);
        });
    }
}
