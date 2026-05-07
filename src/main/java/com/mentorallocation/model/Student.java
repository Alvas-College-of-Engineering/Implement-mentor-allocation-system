package com.mentorallocation.model;

/**
 * Student Model Class
 * Represents a student in the system with their details and specialization requirements
 */
public class Student {
    private int studentId;
    private String name;
    private String department;
    private String requiredSpecialization;

    // Constructors
    /**
     * Default Constructor
     */
    public Student() {
    }

    /**
     * Constructor with all fields
     */
    public Student(int studentId, String name, String department, String requiredSpecialization) {
        this.studentId = studentId;
        this.name = name;
        this.department = department;
        this.requiredSpecialization = requiredSpecialization;
    }

    /**
     * Constructor without studentId (for insert operations)
     */
    public Student(String name, String department, String requiredSpecialization) {
        this.name = name;
        this.department = department;
        this.requiredSpecialization = requiredSpecialization;
    }

    // Getters
    public int getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public String getRequiredSpecialization() {
        return requiredSpecialization;
    }

    // Setters
    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setRequiredSpecialization(String requiredSpecialization) {
        this.requiredSpecialization = requiredSpecialization;
    }

    // toString method
    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", name='" + name + '\'' +
                ", department='" + department + '\'' +
                ", requiredSpecialization='" + requiredSpecialization + '\'' +
                '}';
    }
}
