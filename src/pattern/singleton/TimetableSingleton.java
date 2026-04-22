
package pattern.singleton;

import model.*;
import java.util.*;

public class TimetableSingleton {
    private static TimetableSingleton instance;
    private List<Lesson> lessons = new ArrayList<>();

    private TimetableSingleton(){}

    public static TimetableSingleton getInstance(){
        if(instance==null) instance=new TimetableSingleton();
        return instance;
    }

    public void generate(){
        Trainer t1=new Trainer("John","Yoga");
        Trainer t2=new Trainer("Emma","Zumba");

        String[] days={"Saturday","Sunday"};
        String[] times={"Morning","Afternoon","Evening"};
        ExerciseType[] types=ExerciseType.values();

        for(int w=1;w<=8;w++){
            for(String d:days){
                for(int i=0;i<3;i++){
                    lessons.add(new Lesson(d,times[i],types[i%types.length],(i%2==0?t1:t2)));
                }
            }
        }
    }

    public List<Lesson> getLessons(){ return lessons; }
}
