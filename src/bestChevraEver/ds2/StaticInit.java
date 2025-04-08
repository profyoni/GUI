package bestChevraEver.ds2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StaticInit {
    private class PC{

    }
    static final double PI = 3.14159265358979323846;
    static final double yesterdaysSP500;// = getSP500();
    ActionListener al = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    };


    static double getSP500() {
        return 0;
    }

    static { // static initializer // if init'ing mutliple var and there is some dependency
        yesterdaysSP500 = PI * 0.01; // cal db, call market
    }

    static int max(int ... list){
        return 0; // STUB TODO
    }

    public static void main(String[] args) {
        StaticInit.max(4,5,1);

        StaticInit w = null;
        int x = w.max(3,4); // NEVER do this
        System.out.println(PI);
    }
}

class PersonImmutable{
    private final String name;
    PersonImmutable(String name){
        this.name = name;
    }

}

/**
 *                  client          derived         package         internal
*  public            X                 X            X                   X
 *
 *  protected                           X           X                   X
 *
 *  [package] is default                            X                   X
 *
 *  private                                                             X
 *
 *
 */