import models.Person;
import models.School;
import models.Student;
import models.Teacher;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Assignment1 {
    public static void main(String[] args) {
        School school = new School();

        try {
            BufferedReader studentReader = new BufferedReader(new FileReader("src/students.txt"));
            String line;
            while ((line = studentReader.readLine()) != null) {
                String[] parts = line.split(" ");
                String name = parts[0];
                String surname = parts[1];
                int age = Integer.parseInt(parts[2]);
                boolean gender = parts[3].equalsIgnoreCase("Male");
                Student student = new Student(name, surname, age, gender);

                if (parts.length > 4) {
                    String[] grades = parts[4].split(",");
                    for (String grade : grades) {
                        student.addGrade(Integer.parseInt(grade));
                    }
                }
                school.addMember(student);
            }
            studentReader.close();

            BufferedReader teacherReader = new BufferedReader(new FileReader("src/teachers.txt"));
            while ((line = teacherReader.readLine()) != null) {
                String[] parts = line.split(" ");
                String name = parts[0];
                String surname = parts[1];
                int age = Integer.parseInt(parts[2]);
                boolean gender = parts[3].equalsIgnoreCase("Male");
                String subject = parts[4];
                int yearsOfExperience = Integer.parseInt(parts[5]);
                int salary = Integer.parseInt(parts[6]);

                Teacher teacher = new Teacher(name, surname, age, gender, subject, yearsOfExperience, salary);
                school.addMember(teacher);
            }
            teacherReader.close();

        } catch (IOException e) {
            System.err.println("Error reading files: " + e.getMessage());
        }

        System.out.println("School Members:");
        System.out.println(school);

        for (Person member : school.members) {
            if (member instanceof Student) {
                Student student = (Student) member;
                System.out.println(student.name + " GPA: " + student.calculateGPA());
            } else if (member instanceof Teacher) {
                Teacher teacher = (Teacher) member;
                if (teacher.yearsOfExperience > 10) {
                    teacher.giveRaise(10);
                    System.out.println(teacher.name + " new salary: " + teacher.getSalary());
                }
            }
        }
    }
}
