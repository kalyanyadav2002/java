
package pattern.strategy;

import model.*;
import java.util.*;

public class LessonReportStrategy implements ReportStrategy {
    public String generate(List<Lesson> lessons){
        StringBuilder sb=new StringBuilder();
        for(Lesson l:lessons){
            sb.append(l.getType()).append(" ").append(l.getBookingCount()).append("\n");
        }
        return sb.toString();
    }
}
