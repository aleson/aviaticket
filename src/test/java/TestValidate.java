import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import usermod.User;

public class TestValidate {

    private  User user;

    @Before
    public void init(){
        user=new User();
    }

    @Test
    public void RegEx(){
        //user.setFio("1asaa");//Exception
        user.setFio("asas");//true
        user.setFio("AAAS");//true
        //user.setFio("MVC");//Exception - <4
        user.setFio("AraRZSAsrAr");//true
        //user.setFio("ArarararararA");//Exception - >12
        System.out.println(user.getFio());
        //user.setLogin("");//Exception - null
        //user.setLogin("g12 5678");//Exception - " "
        user.setLogin("Aerdghs_98");//true
        System.out.println(user.getLogin());
    }

    @After
    public void flush(){
        user=null;
    }
}
