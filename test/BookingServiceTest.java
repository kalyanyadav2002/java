
import model.*;
import service.impl.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class BookingServiceTest {

    @Test
    public void testBookingSuccess() {
        Member m = new Member(1, "A", "123");

        Lesson l = new Lesson("Sat", "Morning",
                ExerciseType.YOGA, new Trainer("T", "Yoga"));

        BookingServiceImpl s = new BookingServiceImpl();

        assertTrue(s.book(m, l));
    }

    @Test
    public void testConflictBookingFails() {
        Member m = new Member(1, "A", "123");

        Lesson l1 = new Lesson("Sat", "Morning",
                ExerciseType.YOGA, new Trainer("T", "Yoga"));

        Lesson l2 = new Lesson("Sat", "Morning",
                ExerciseType.ZUMBA, new Trainer("T", "Zumba"));

        BookingServiceImpl s = new BookingServiceImpl();

        s.book(m, l1);

        assertFalse(s.book(m, l2));
    }

    @Test
    public void testCancelBooking() {
        Member m = new Member(1, "A", "123");

        Lesson l = new Lesson("Sat", "Morning",
                ExerciseType.YOGA, new Trainer("T", "Yoga"));

        BookingServiceImpl s = new BookingServiceImpl();

        s.book(m, l);
        s.cancel(m, l);

        assertEquals(0, m.getBookings().size());
    }
}