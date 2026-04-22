
package model;

public enum ExerciseType {
    YOGA(10), ZUMBA(12), AQUACISE(15), BOXFIT(14), BODYBLITZ(13);

    private double price;
    ExerciseType(double price){ this.price = price; }
    public double getPrice(){ return price; }
}
