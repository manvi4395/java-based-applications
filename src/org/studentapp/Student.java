package org.studentapp;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Student {
	
	//below are the instance variables
	private String name;
	private int age;
	private String studentId;
	private List<String> courses;

	public Student(String name, int age, String studentId) { //thses are local variables or should say are arguments

		super();
		if (validateAge(age) && validateName(name) && validateStudentId(studentId)){
			this.name = name;
			this.age = age;
			this.studentId = studentId;
			courses = new ArrayList<String>();
		}

	}

	public void enrollCourse(String course) {
		
		if(validateCourseName(course) ) {

		if (! courses.contains(course)) {

			courses.add(course);
			System.out.println("Student is enrolled to: " + course + " successfully");
		}

		else {
			System.err.println("Student is already enrolled to the course");
		}
		}
	}

	public void printStudentInfo() {

		System.out.println("****** Student Information ******");
		System.out.println("Student Name:" + name);
		System.out.println("Student Age:" + age);
		System.out.println("Student id:" + studentId);
		System.out.println("Enrolled for:" + courses);
		System.out.println("Student is enrolled to " + courses + " successfully");

	}

	public boolean validateAge(int age) {
		if (age > 19 && age < 35) {
			return true;
		} else {
			System.err.println("Invalid Age!! Student age should be between 19 and 35.");
			return false;
		}
	}

	public boolean validateName(String name) {

		String nameRegex = "^[a-zA-Z\\s]+$";
		Pattern namePattern = Pattern.compile(nameRegex);
		Matcher nameMatcher = namePattern.matcher(name);

		if (nameMatcher.matches()) {

			return true;
		} else {

			System.err.println("Invalid Name!! PLease enter alphabets only ");
			return false;
		}
	}

	public boolean validateStudentId(String studentId) {

		String studentIdRegex = "S-\\d+$";
		Pattern studentIdPattern = Pattern.compile(studentIdRegex);
		Matcher studentIdMatches = studentIdPattern.matcher(studentId);

		if (studentIdMatches.matches()) {

			return true;
		} else {

			System.err.println("Invalid Studen Id!! Enter valid Id.");
			return false;

		}
	}
	
	public boolean validateCourseName(String course) {
		
		if(course.equalsIgnoreCase("Java") || course.equalsIgnoreCase("Devops") || course.equalsIgnoreCase("Python")) {
			return true;
		} else {
			System.err.println("Invalid Course!! Select course from the list: Java,Devops,Python!!");
			return false;	
			
		}
	}
	
	public String getName() {
		return name;
	}



	public int getAge() {
		return age;
	}



	public String getStudentId() {
		return studentId;
	}



	public List<String> getCourses() {
		return courses;
	}

	@Override
	public String toString() {
		return "Student [name=" + name + ", age=" + age + ", studentId=" + studentId + ", courses=" + courses + "]";
	}
	
	



}
