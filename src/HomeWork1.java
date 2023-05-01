import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.function.Function;
import java.util.Arrays;

public class HomeWork1 {

    public static class Student {
        private String name;
        private Integer grade;

        public Student(String name, Integer grade) {
            this.name = name;
            this.grade = grade;
        }

        public String getName() {
            return name;
        }

        public Integer getGrade() {
            return grade;
        }

        public String announce() {
            return String.format("%s учится в %d классе", name, grade);
        }
    }

    public static class Teacher {
        private String name;
        private Student[] students;

        public Teacher(String name) {
            this.name = name;
            students = new Student[30];
        }

        public String getName() {
            return name;
        }

        public Student[] getStudents() {
            return students;
        }

        public void addStudent(Student student) {
            for (int i = 0; i < students.length; i++) {
                if (students[i] == null) {
                    students[i] = student;
                    return;
                }
            }
        }

        public String[] rollCall() {
            String[] result = new String[students.length];
            for (int i = 0; i < students.length; i++) {
                if (students[i] != null) {
                    result[i] = students[i].announce();
                }
            }
            return Arrays.stream(result)
                    .filter(Objects::nonNull)
                    .toArray(String[]::new);
        }
    }

    public static void main(String[] args) {

        var student = new Student(STUDENT_NAME, STUDENT_GRADE);
        print("Student: Студент создался", true);
        print("Student: Геттер имени", Objects.equals(student.getName(), STUDENT_NAME));
        print("Student: Геттер класса", Objects.equals(student.getGrade(), STUDENT_GRADE));
        print("Student: announce содержит имя", student.announce().contains(STUDENT_NAME));
        print("Student: announce содержит класс", student.announce().contains(STUDENT_GRADE.toString()));

        var teacher = new Teacher(TEACHER_NAME);
        print("Teacher: Учитель создался", true);
        print("Teacher: Геттер имени", Objects.equals(teacher.getName(), TEACHER_NAME));
        print("Teacher: Геттер студентов", Objects.nonNull(teacher.getStudents()));
        print("Teacher: Массив учеников должен быть размером 30", teacher.getStudents().length == 30);

        teacher.addStudent(student);
        print("Teacher: Студент сохранился в массив", teacher.getStudents()[0] == student);
        String[] calls = teacher.rollCall();
        print("Teacher: Массив rollCall состоит из одного элемента", calls.length == 1);
        print("Teacher: В строке содержится имя студента", calls[0].contains(STUDENT_NAME));
    }

    /* Техническая секция - сюда писать ничего не надо */

    private static void print(String condition, boolean act) {
        String prefix = act ? "✅" : "❌";
        System.out.printf("%s %s%n", prefix, condition);
    }

    private final static String STUDENT_NAME = "NameStudent";
    private final static Integer STUDENT_GRADE = 7;
    private final static String TEACHER_NAME = "NameTeacher";
}