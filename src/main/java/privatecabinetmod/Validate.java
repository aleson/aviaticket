package privatecabinetmod;

import usermod.User;

//class for check operation in inner logic
public class Validate {

    //this method return true if user may payed flight
    public static boolean isPay(User user, Flight flight){
       return user.getMoney()>=flight.getCost() ? true: false;
    }

    private Validate(){}//that we always used one or few of them methods
}
