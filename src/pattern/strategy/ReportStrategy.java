
package pattern.strategy;

import model.*;
import java.util.*;

public interface ReportStrategy {
    String generate(List<Lesson> lessons);
}
