package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Activity {
    private Subject subject;
    private final String group;
    private final int number;
    private final String day;
    private final int start;
    private final int duration;
    private final String room;
    private final int capacity;
    private IntegerProperty enrolled = new SimpleIntegerProperty();

    public Activity(Subject subject, String group, int number, String day, int start, int duration, String room, int capacity) {
        this.subject = subject;
        this.group = group;
        this.number = number;
        this.day = day;
        this.start = start;
        this.duration = duration;
        this.room = room;
        this.capacity = capacity;
        this.enrolled.set(0);
    }

    public Subject getSubject() { return subject; }
    public int getSubjectNumber() { return subject.getNumber(); }
    public final String getGroup() { return group; }
    public final int getNumber() { return number; }
    public final String getDay() { return day; }
    public final int getStart() { return start; }
    public final int getDuration() { return duration; }
    public final String getRoom() { return room; }
    public final int getCapacity() { return capacity; }
    public final int getEnrolled() { return enrolled.get(); }
    private final void setEnrolled(int enrolled) {this.enrolled.set(enrolled); }
    public ReadOnlyIntegerProperty enrolledProperty() { return enrolled; }

    public boolean canEnrol() {
        return getEnrolled() < capacity;
    }

    public void enrol() {
        setEnrolled(getEnrolled() + 1);
    }

    public void withdraw() {
        setEnrolled(getEnrolled() - 1);
    }

    public boolean matches(int subjectNumber, String group) {
        return subject.matches(subjectNumber) && group.equals(this.group);
    }

    @Override
    public String toString() {
        return String.format("%d %s %d %s %s %02d:00 %dhrs %d/%d", subject.getNumber(), group, number, day, room, start, duration, enrolled, capacity);
    }
}
