
import model.*;
import service.impl.*;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

public class ReportServiceTest {

    @Test
    public void testLessonReportGeneration() {
        Lesson l = new Lesson("Sat", "Morning",
                ExerciseType.YOGA, new Trainer("T", "Yoga"));

        Member m = new Member(1, "A", "123");
        l.addMember(m);

        List<Lesson> lessons = new ArrayList<>();
        lessons.add(l);

        ReportServiceImpl r = new ReportServiceImpl();

        String report = r.generateLessonReport(lessons);

        assertTrue(report.contains("YOGA"));
    }

    @Test
    public void testTopIncome() {
        Lesson l1 = new Lesson("Sat", "Morning",
                ExerciseType.YOGA, new Trainer("T", "Yoga"));

        Lesson l2 = new Lesson("Sat", "Evening",
                ExerciseType.ZUMBA, new Trainer("T", "Zumba"));

        Member m = new Member(1, "A", "123");

        l2.addMember(m); // Zumba earns more

        List<Lesson> lessons = Arrays.asList(l1, l2);

        ReportServiceImpl r = new ReportServiceImpl();

        ExerciseType top = r.topIncome(lessons);

        assertEquals(ExerciseType.ZUMBA, top);
    }
}