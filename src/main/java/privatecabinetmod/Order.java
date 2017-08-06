package privatecabinetmod;

//class have data about user's who payed flight's
public class Order {

    private int id;
    private int idflight;
    private int iduser;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if(id<1) throw new IllegalArgumentException("id is incorrect");
        this.id = id;
    }

    public int getIdflight() {
        return idflight;
    }

    public void setIdflight(int idflight) {
        if(idflight<1) throw new IllegalArgumentException("id is incorrect");
        this.idflight = idflight;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        if(iduser<1) throw new IllegalArgumentException("id is incorrect");
        this.iduser = iduser;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", idflight=" + idflight +
                ", iduser=" + iduser +
                '}';
    }
}
