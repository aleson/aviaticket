package privatecabinetmod;

import java.util.Date;

//this class have all information about one flight
public class Flight {
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    private int id;
    private int idcitybeg,idcityend;
    private String name;
    private float cost;
    private Date date;
    private int hour,minut,time;

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinut() {
        return minut;
    }

    public void setMinut(int minut) {
        this.minut = minut;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIdcityend() {
        return idcityend;
    }

    public void setIdcityend(int idcityend) {
        this.idcityend = idcityend;
    }

    public int getIdcitybeg() {
        return idcitybeg;
    }

    public void setIdcitybeg(int idcitybeg) {
        this.idcitybeg = idcitybeg;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", idcitybeg=" + idcitybeg +
                ", idcityend=" + idcityend +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                ", date=" + date +
                ", hour=" + hour +
                ", minut=" + minut +
                ", time=" + time +
                '}';
    }
}
