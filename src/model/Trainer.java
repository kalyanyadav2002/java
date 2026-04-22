
package model;

public class Trainer {
    private String name;
    private String specialization;

    public Trainer(String name, String specialization){
        this.name = name;
        this.specialization = specialization;
    }

    public String getName(){ return name; }
    public String getSpecialization(){ return specialization; }
}
