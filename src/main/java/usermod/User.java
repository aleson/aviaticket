package usermod;

public class User implements Userable {

    private int id;//User's index
    private String fio;//User's name
    private String login; //login
    private String pass;//password
    private float money;//user's money
    private int role; //if 1 - client, else 2 - admin

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role)
    {

        if(role<1) throw new IllegalArgumentException("Illegal argument in user role");
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        if(fio.isEmpty() || fio.length()<4)
            throw new IllegalArgumentException("Name is incorrect");
        this.fio = fio;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        if(login.isEmpty() || login.length()<4)
            throw new IllegalArgumentException("Login incorrected");
        //меньше трех для таких логинов как Oleg,Egor и.т.д.
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        if(pass.isEmpty() || (pass.length()<4 && pass.length()>12))
            throw new IllegalArgumentException("Pass incorrected");
        this.pass = pass;
    }

    public float getMoney() {
        return new Float(money);
    }

    public void setMoney(float money) {
        Float m=money;
        if(m.isNaN()) throw new IllegalArgumentException("No money");
        this.money = money;
    }
    
    public void info() {
        System.out.println(getId()+" "+getFio() +" "+ getLogin()+" "+ getPass()+" "+ getMoney()+" "+getRole());
    }
}
