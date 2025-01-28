import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main2 {
    static int c;
    public static void main(String[] args)  {
        JFrame window = new JFrame();
        window.setTitle("Hello World " + c++);
        window.setSize(800, 600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton button = new JButton("Click Me");
        window.add(button, BorderLayout.SOUTH);
        button.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                button.setBackground(Color.RED);
            }
        });


        window.setVisible(true);

//        window.addWindowListener(new WindowAdapter() {
//            public void windowIconified(WindowEvent e) {
//                System.exit(0);
//            }
//            public void windowClosing(WindowEvent e) {
//                for (int i=0;i<10;i++)
//                    main(null);
////                try {
////                    Thread.sleep(3000);
////                } catch (InterruptedException ex) {
////                    // anti-pattern
////                }
//                //System.exit(0);
//
//            }
//        });
    }
}
