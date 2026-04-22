
package service.impl;

import service.*;
import model.*;

public class BookingServiceImpl implements BookingService {

    private boolean isConflict(Member m, Lesson l){
        return m.hasConflict(l);
    }

    public boolean book(Member m, Lesson l){
        if(isConflict(m,l) || l.isFull()) return false;
        m.addBooking(l);
        l.addMember(m);
        return true;
    }

    public boolean change(Member m, Lesson oldL, Lesson newL){
        if(!book(m,newL)) return false;
        oldL.removeMember(m);
        m.removeBooking(oldL);
        return true;
    }

    public boolean cancel(Member m, Lesson l){
        l.removeMember(m);
        m.removeBooking(l);
        return true;
    }
}
