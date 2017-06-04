package defmod;

import usermod.User;

public class Registration {
    private User user=null;
    public Registration(String fio,String login,String pass,float money){
        user.setFio(fio);
        user.setMoney(money);
        user.setLogin(login);
        user.setPass(pass);
        user= new User() {
            @Override
            public void info() {
                System.out.println("Registry finished successfully");
            }
        };
    }

}
