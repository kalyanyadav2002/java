import model.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class LessonTest {

    @Test
    public void testAddMember() {
        Lesson l = new Lesson("Sat", "Morning",
                ExerciseType.YOGA, new Trainer("T", "Yoga"));

        Member m = new Member(1, "A", "123");

        assertTrue(l.addMember(m));
        assertEquals(1, l.getBookingCount());
    }

    @Test
    public void testMaxCapacity() {
        Lesson l = new Lesson("Sat", "Morning",
                ExerciseType.YOGA, new Trainer("T", "Yoga"));

        for (int i = 0; i < 4; i++) {
            l.addMember(new Member(i, "M"+i, "123"));
        }

        assertFalse(l.addMember(new Member(99, "X", "123")));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidRating() {
        Lesson l = new Lesson("Sat", "Morning",
                ExerciseType.YOGA, new Trainer("T", "Yoga"));

        l.addRating(6); // invalid
    }
}