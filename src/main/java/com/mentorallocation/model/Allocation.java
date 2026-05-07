package com.mentorallocation.model;

import java.time.LocalDate;

/**
 * Allocation Model Class
 * Represents a mentor-student allocation record
 */
public class Allocation {
    private int allocationId;
    private int mentorId;
    private int studentId;
    private LocalDate allocationDate;

    // Constructors
    /**
     * Default Constructor
     */
    public Allocation() {
    }

    /**
     * Constructor with all fields
     */
    public Allocation(int allocationId, int mentorId, int studentId, LocalDate allocationDate) {
        this.allocationId = allocationId;
        this.mentorId = mentorId;
        this.studentId = studentId;
        this.allocationDate = allocationDate;
    }

    /**
     * Constructor without allocationId (for insert operations)
     */
    public Allocation(int mentorId, int studentId, LocalDate allocationDate) {
        this.mentorId = mentorId;
        this.studentId = studentId;
        this.allocationDate = allocationDate;
    }

    // Getters
    public int getAllocationId() {
        return allocationId;
    }

    public int getMentorId() {
        return mentorId;
    }

    public int getStudentId() {
        return studentId;
    }

    public LocalDate getAllocationDate() {
        return allocationDate;
    }

    // Setters
    public void setAllocationId(int allocationId) {
        this.allocationId = allocationId;
    }

    public void setMentorId(int mentorId) {
        this.mentorId = mentorId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public void setAllocationDate(LocalDate allocationDate) {
        this.allocationDate = allocationDate;
    }

    // toString method
    @Override
    public String toString() {
        return "Allocation{" +
                "allocationId=" + allocationId +
                ", mentorId=" + mentorId +
                ", studentId=" + studentId +
                ", allocationDate=" + allocationDate +
                '}';
    }
}
