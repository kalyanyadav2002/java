
package service.impl;

import service.*;
import model.*;
import java.util.*;

public class ReportServiceImpl implements ReportService {

    public String generateLessonReport(List<Lesson> lessons){
        StringBuilder sb = new StringBuilder();
        for(Lesson l: lessons){
            sb.append(l.getDay()).append(" ")
              .append(l.getTimeSlot()).append(" ")
              .append(l.getType())
              .append(" Members:").append(l.getBookingCount())
              .append(" Avg:").append(l.getAverageRating())
              .append("\n");
        }
        return sb.toString();
    }

    public ExerciseType topIncome(List<Lesson> lessons){
        Map<ExerciseType, Double> map = new HashMap<>();
        for(Lesson l: lessons){
            double inc = l.getBookingCount() * l.getType().getPrice();
            map.put(l.getType(), map.getOrDefault(l.getType(),0.0)+inc);
        }
        return Collections.max(map.entrySet(), Map.Entry.comparingByValue()).getKey();
    }
}
