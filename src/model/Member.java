
package model;

import java.util.*;

public class Member {
    private int id;
    private String name;
    private String password;
    private List<Lesson> bookings = new ArrayList<>();

    public Member(int id, String name, String password){
        this.id=id; this.name=name; this.password=password;
    }

    public int getId(){ return id; }
    public String getName(){ return name; }
    public String getPassword(){ return password; }

    public boolean hasConflict(Lesson lesson){
        for(Lesson l: bookings){
            if(l.getDay().equals(lesson.getDay()) &&
               l.getTimeSlot().equals(lesson.getTimeSlot())){
                return true;
            }
        }
        return false;
    }

    public void addBooking(Lesson lesson){ bookings.add(lesson); }
    public void removeBooking(Lesson lesson){ bookings.remove(lesson); }

    public List<Lesson> getBookings(){ return bookings; }
}
