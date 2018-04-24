package diary.domain;

/*yksittäinen harjoituskerta*/
public class Exercise {

    private int id;
    private SportType sport;
    private double km;
    private double duration;
    private int date;

    public Exercise( SportType sport, double km, double duration, int date) {
        this.sport = sport;
        this.km = km;
        this.duration = duration;
        this.date = date;
    }


    public SportType getType() {
        return this.sport;
    }

    public double getKm() {
        return this.km;
    }

    public double getDuration() {
        return this.duration;
    }

    public int getDate() {
        return this.date;
    }
}
