
package service;

import model.*;
import java.util.*;

public interface ReportService {
    String generateLessonReport(List<Lesson> lessons);
    ExerciseType topIncome(List<Lesson> lessons);
}
