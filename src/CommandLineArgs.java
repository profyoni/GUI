import javax.swing.*;

public class CommandLineArgs {
    public static int max(int ... array){
        int rm = array[0];
        for (int i : array)
            if (i > rm)
                rm = i;
        return rm;
    }

    public static void main(String[] args) {
        System.out.println("CLA");

        String name = "Bob";
        int age = 98;
        System.out.printf("%s is #d years old",name,age);
        System.out.print(max(3,4,5,6,7));
        String s = args[0];
        Integer size = Integer.parseInt(s);
        JFrame ap = new JFrame();
        ap.setSize(size, size);
        ap.setVisible(true);
        ap.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        for(String arg : args) {
//            System.out.println(arg);
//        }
    }
}
