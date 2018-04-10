
package diary.domain;


public class Exercise {
    
    private int id;
    private SportType sport;
    private double km;
    private double duration;
    private int date;
    
    public Exercise(int id, SportType sport, double km, double duration, int date){
        this.id = id;
        this.sport = sport;
        this.km = km;
        this.duration = duration;
        this.date = date;
    }
    
}
