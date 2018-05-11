package diary.domain;

/**
 * Yksittäinen harjoitusolio
 *
 * @author Hilma
 */
public class Exercise {

    private int id;
    private SportType sport;
    private double km;
    private double duration;
    private int date;

    /**
     * Luo exercise olion
     *
     * @param sport harjoituksen laji
     * @param km kuljettu matka
     * @param duration harjoitukseen käytetty aika
     * @param date harjoituksen päivämäärä
     */
    public Exercise(SportType sport, double km, double duration, int date) {
        this.sport = sport;
        this.km = km;
        this.duration = duration;
        this.date = date;
    }

    public Exercise(int id, SportType sport, double km, double duration, int date) {
        this.id = id;
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

    public int getId() {
        return this.id;
    }

    public String getContent() {
        return String.valueOf(this.date) + ", " + this.sport.toString() + ", "
                + String.valueOf(km) + ", " + String.valueOf(this.duration);
    }
}
