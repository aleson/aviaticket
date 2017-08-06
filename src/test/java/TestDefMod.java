import defmod.Autentification;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import usermod.User;

public class TestDefMod {

    private User user;

    @Before
    public void init(){
        user=new User();
    }

    @Test
    public void findUser(){
        Autentification aut=new Autentification();
        System.out.println(aut.findUser("Venum","1234").getFio());
    }

    @After
    public void flush(){
        user=null;
    }
}
