package org.studentapp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class MainEnrollment {

	private static List<Student> studentList;
	private static Scanner scanner;

	public static void main(String[] args) {
		System.out.println("**************** WELCOME INTO STUDENT MANAGEMENT SYSTEM ****************");

		studentList = new ArrayList<Student>();
		scanner = new Scanner(System.in);
		while (true) {
			System.out.println("*********** WELCOME TO DASHBOARD ***********");
			System.out.println("Select an option from below...");
			System.out.println("1. Register a student");
			System.out.println("2. Find student with StudentId");
			System.out.println("3. List all student information");
			System.out.println("4. List student information in sorted order");
			System.out.println("5. Exit");

			int option = scanner.nextInt();

			switch (option) {
			case 1:
				enrollStudent(scanner);
				break;

			case 2:
				findStudentById(scanner);
				break;

			case 3:
				printAllStudentData(scanner);
				break;

			case 4:
				sortByName();
				break;

			case 5:
				exit();
				break;

			default:
				System.out.println("Invalid option selected!! Select between 1 to 5");
			}

		}
	}

	private static void exit() {

		System.exit(0);

	}

	private static void printAllStudentData(Scanner scanner2) {

		if (studentList.size() > 0) {
			System.out.println("***************** PRINT ALL STUDENT DATA *****************");

			for (Student student : studentList) {

				student.printStudentInfo();
			}
		} else {
			System.err.println("Student List is empty. No record found.");

		}
	}

	private static void findStudentById(Scanner scanner2) {

		Student studentFound = null;

		System.out.println("Enter the student Id");
		String studentId = scanner2.next();
		try {
			studentFound = studentList.stream().filter(student -> student.getStudentId().equalsIgnoreCase(studentId))
					.findFirst().orElseThrow(() -> new RuntimeException("No data found!!"));
		} catch (RuntimeException e) {

			System.err.println("Student with ID" + studentId + " not found!!"); // custom error message
		}

		studentFound.printStudentInfo();

	}

	private static void enrollStudent(Scanner scanner2) {

		System.out.println("Enter the Student name");
		String studentName = scanner2.next();

		System.out.println("Enter the Student age");
		int studentAge = scanner2.nextInt();

		System.out.println("Enter the Student Id");
		String studentId = scanner2.next();

		Student newStudent = new Student(studentName, studentAge, studentId);
		studentList.add(newStudent);
		while (true) {

			System.out.println("Enter course to be enrolled...Type done to exit");
			String courseName = scanner2.next();
			if (courseName.equalsIgnoreCase("done")) {

				break;// exit from the loop
			}
			newStudent.enrollCourse(courseName);

		}

		newStudent.printStudentInfo();

	}

	public static Student findStudentById(String studentId) {

		Student result = null;

		try {
			result = studentList.stream().filter(x -> x.getStudentId().equalsIgnoreCase(studentId)).findFirst()
					.orElseThrow(() -> new RuntimeException("No data found!!"));
		} catch (RuntimeException e) {

			System.err.println("Student with ID" + studentId + " not found!!"); // custom error message
		}

		return result;

	}

	// Below implementing sorting of students by their `name` using
	// `Collections.sort()` and a custom `Comparator`.
	public static void sortByName() {

		Comparator<Student> studentNameComparator = (o1, o2) -> o1.getName().compareTo(o2.getName()); // using lambda
																										// expression

		/*
		 * OR CAN WRITE AS BELOW: Comparator<Student> studentNameComparator = new
		 * Comparator<Student>() {
		 * 
		 * @Override public int compare(Student o1, Student o2) {
		 * return(o1.getName().compareTo(o2.getName())); } };//custom Comparator
		 * functionality to sort the values on the basis of instance variables
		 */
		Collections.sort(studentList, studentNameComparator);
		System.out.println(studentList);

	}

}
