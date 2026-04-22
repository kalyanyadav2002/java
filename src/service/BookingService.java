
package service;

import model.*;

public interface BookingService {
    boolean book(Member m, Lesson l);
    boolean change(Member m, Lesson oldL, Lesson newL);
    boolean cancel(Member m, Lesson l);
}
