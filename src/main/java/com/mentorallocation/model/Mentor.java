package com.mentorallocation.model;

/**
 * Mentor Model Class
 * Represents a mentor in the system with their details and availability status
 */
public class Mentor {
    private int mentorId;
    private String name;
    private String specialization;
    private boolean availability;

    // Constructors
    /**
     * Default Constructor
     */
    public Mentor() {
    }

    /**
     * Constructor with all fields
     */
    public Mentor(int mentorId, String name, String specialization, boolean availability) {
        this.mentorId = mentorId;
        this.name = name;
        this.specialization = specialization;
        this.availability = availability;
    }

    /**
     * Constructor without mentorId (for insert operations)
     */
    public Mentor(String name, String specialization, boolean availability) {
        this.name = name;
        this.specialization = specialization;
        this.availability = availability;
    }

    // Getters
    public int getMentorId() {
        return mentorId;
    }

    public String getName() {
        return name;
    }

    public String getSpecialization() {
        return specialization;
    }

    public boolean isAvailability() {
        return availability;
    }

    // Setters
    public void setMentorId(int mentorId) {
        this.mentorId = mentorId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    // toString method
    @Override
    public String toString() {
        return "Mentor{" +
                "mentorId=" + mentorId +
                ", name='" + name + '\'' +
                ", specialization='" + specialization + '\'' +
                ", availability=" + availability +
                '}';
    }
}
