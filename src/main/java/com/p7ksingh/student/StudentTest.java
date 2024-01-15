package com.p7ksingh.student;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StudentTest {
    public static void main(String[] args) {
        List<Student> list = Arrays.asList(
                new Student(1, "Rohit", "Mall", 30, "Male", "Mechanical Engineering", 2015, "Mumbai", 122),
                new Student(2, "Pulkit", "Singh", 56, "Male", "Computer Engineering", 2018, "Delhi", 67),
                new Student(3, "Ankit", "Patil", 25, "Female", "Mechanical Engineering", 2019, "Kerala", 164),
                new Student(4, "Satish Ray", "Malaghan", 30, "Male", "Mechanical Engineering", 2014, "Kerala", 26),
                new Student(5, "Roshan", "Mukd", 23, "Male", "Biotech Engineering", 2022, "Mumbai", 12),
                new Student(6, "Chetan", "Star", 24, "Male", "Mechanical Engineering", 2023, "Karnataka", 90),
                new Student(7, "Arun", "Vittal", 26, "Male", "Electronics Engineering", 2014, "Karnataka", 324),
                new Student(8, "Nam", "Dev", 31, "Male", "Computer Engineering", 2014, "Karnataka", 433),
                new Student(9, "Sonu", "Shankar", 27, "Female", "Computer Engineering", 2018, "Karnataka", 7),
                new Student(10, "Shubham", "Pandey", 26, "Male", "Instrumentation Engineering", 2017, "Mumbai", 98),
                new Student(11, "niraj", "Singh", 66, "Male", "Marine Engineering", 2018, "Delhi", 7));
        // 1- Find list of students whose first name starts with alphabet A
        method1(list);
        // 2- Group The Student By Department Names
        method2(list);
        // 3- Find the total count of student using stream
        method3(list);
        // Q4- Find the max age of student
        method4(list);
        // Q5- Find all departments names
        method5(list);
        // Q6- Find the count of student in each department
        method6(list);
        // Q7-Find the list of students whose age is less than 30
        method7(list);
        // Q8- Find the list of students whose rank is in between 50 and 100
        method8(list);
        // Q9- Find the average age of male and female students
        method9(list);
        // Q10- Find the department who is having maximum number of students
        method10(list);
        // Q11- Find the Students who stays in Delhi and sort them by their names
        method11(list);
        // Q12- Find the average rank in all departments
        method12(list);
        // Q13- Find the highest rank in each department
        method13(list);
        // Q14- Find the list of students and sort them by their rank
        method14(list);
        // Q15- Find the student who has second rank
        method15(list);
    }

    private static void method15(List<Student> list) {
        Student student = list.stream().sorted(Comparator.comparing(Student::getRank)).skip(1).findFirst().get();
        System.out.println("Second highest rank student  : " + student);
    }

    private static void method14(List<Student> list) {
        List<Student> stuRankSorted = list.stream().sorted(Comparator.comparing(Student::getRank))
                .collect(Collectors.toList());
        System.out.println("List of students sorted by their rank  : " + stuRankSorted);
    }

    private static void method13(List<Student> list) {
        Map<String, Optional<Student>> collect = list.stream().collect(Collectors.groupingBy(Student::getDepartmantName,
                Collectors.minBy(Comparator.comparing(Student::getRank))));
        System.out.println(collect);

    }
    // private static void method13(List<Student> list) {
    // Optional<Student> max =
    // list.stream().max(Comparator.comparing(Student::getRank));

    // System.out.println(max);
    // }

    private static void method12(List<Student> list) {
        Map<String, Double> collect = list.stream()
                .collect(Collectors.groupingBy(Student::getDepartmantName, Collectors.averagingInt(Student::getRank)));

        System.out.println(collect);
    }

    // private static void method12(List<Student> list) {
    // Double collect =
    // list.stream().collect(Collectors.averagingDouble(Student::getRank));
    // System.out.println(collect);
    // }

    private static void method11(List<Student> list) {
        List<String> collect = list.stream().filter(dt -> dt.getCity().equals("Delhi")).map(Student::getFirstName)
                .sorted()
                .collect(Collectors.toList());
        System.out.println(collect);
    }
    // private static void method11(List<Student> list) {
    // List<Student> collect = list.stream().filter(dt ->
    // dt.getCity().equals("Delhi")).sorted(Comparator.comparing(Student::getFirstName))
    // .collect(Collectors.toList());

    // }

    private static void method10(List<Student> list) {
        Entry<String, Long> entry = list.stream().collect(Collectors.groupingBy(Student::getDepartmantName,
                Collectors.counting())).entrySet().stream().max(Map.Entry.comparingByValue()).get();
        System.out.println(entry);
    }

    private static void method9(List<Student> list) {
        Map<String, Double> collect = list.stream()
                .collect(Collectors.groupingBy(Student::getGender, Collectors.averagingDouble(Student::getAge)));
        System.out.println(collect);
    }

    private static void method8(List<Student> list) {
        list.stream().filter(dt -> dt.getRank() > 50 && dt.getRank() < 100)
                .collect(Collectors.toList()).forEach(System.out::println);

    }

    private static void method7(List<Student> list) {
        list.stream().filter(dt -> dt.getAge() < 30).collect(Collectors.toList()).forEach(System.out::println);

    }

    private static void method6(List<Student> list) {
        Map<String, Long> collect = list.stream()
                .collect(Collectors.groupingBy(Student::getDepartmantName, Collectors.counting()));
        System.out.println(collect);
    }

    private static void method5(List<Student> list) {
        List<String> listDept = list.stream().map(dt -> dt.getDepartmantName()).distinct().collect(Collectors.toList());
        System.out.println(listDept);
    }
    // private static void method5(List<Student> list) {
    // list.stream().map(dt ->
    // dt.getDepartmantName()).distinct().forEach(System.out::println);

    // }

    private static void method4(List<Student> list) {
        System.out.println("4- Find the max age of student ?");
        Student maxAgeStudent = list.stream().max((s1, s2) -> Integer.compare(s1.getAge(), s2.getAge())).get();
        System.out.println(maxAgeStudent);
    }
    // private static void method4(List<Student> list) {
    // System.out.println("4- Find the max age of student ?");
    // OptionalInt max = list.stream().mapToInt(dt -> dt.getAge()).max();
    // System.out.println(max.getAsInt());
    // }

    private static void method3(List<Student> list) {
        System.out.println("=".repeat(40));
        System.out.println("Q3- Find the total count of student using stream");
        long count = list.stream().count();
        System.out.println(count);
    }

    // private static void method2(List<Student> list) {
    // System.out.println("=".repeat(40));
    // System.out.println("Q2- Group The Student By Department Names");
    // Map<String, List<Student>> group = list.stream()
    // .collect(Collectors.groupingBy(Student::getDepartmantName,
    // Collectors.toList()));
    // System.out.println(group);
    // System.out.println("=".repeat(40));
    // }
    private static void method2(List<Student> list) {
        System.out.println("=".repeat(40));
        System.out.println("Q2- Group The Student By Department Names");
        Map<String, List<Student>> group = list.stream()
                .collect(Collectors.groupingBy(Student::getDepartmantName));
        System.out.println(group);
        System.out.println("=".repeat(40));
    }

    private static void method1(List<Student> list) {
        System.out.println("=".repeat(40));
        System.out.println("Q1- Find list of students whose first name starts with alphabet A");
        List<Student> listOfStu = list.stream().filter(dt -> dt.getFirstName().startsWith("A"))
                .collect(Collectors.toList());
        System.out.println(listOfStu);
        System.out.println("=".repeat(40));
    }
}
