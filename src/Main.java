import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {
    private static int counter;
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setTitle("Hello World" + counter++);
        window.setSize(800, 600);
        JButton pressMeButton = new JButton("Click Me");
        window.add(pressMeButton, BorderLayout.NORTH);
        pressMeButton.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                window.getContentPane().setBackground(Color.GREEN);
                pressMeButton.setBackground(Color.BLUE);
            }
        });

        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        window.addWindowListener(new WindowAdapter() {
//            public void windowIconified(WindowEvent e) {
//                System.exit(0);
//            }
//
//            public void windowClosing(WindowEvent e) {
//                for (int i=0;i<10;i++)
//                    main(null);
//            }
//        });

    }
}