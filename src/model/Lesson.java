
package model;

import java.util.*;

public class Lesson {
    private String day;
    private String timeSlot;
    private ExerciseType type;
    private Trainer trainer;

    private List<Member> members = new ArrayList<>();
    private List<Integer> ratings = new ArrayList<>();
    private final int MAX = 4;

    public Lesson(String day, String timeSlot, ExerciseType type, Trainer trainer){
        this.day=day; this.timeSlot=timeSlot; this.type=type; this.trainer=trainer;
    }

    public boolean isFull(){ return members.size() >= MAX; }

    public boolean addMember(Member m){
        if(isFull() || members.contains(m)) return false;
        members.add(m);
        return true;
    }

    public void removeMember(Member m){ members.remove(m); }

    public void addRating(int r){
        if(r<1||r>5) throw new IllegalArgumentException("Rating 1-5");
        ratings.add(r);
    }

    public double getAverageRating(){
        return ratings.stream().mapToInt(i->i).average().orElse(0);
    }

    public int getBookingCount(){ return members.size(); }
    public String getDay(){ return day; }
    public String getTimeSlot(){ return timeSlot; }
    public ExerciseType getType(){ return type; }
    public Trainer getTrainer(){ return trainer; }
}
