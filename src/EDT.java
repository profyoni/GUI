import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EDT extends JFrame {
    public static void main(String[] args) {
        new EDT();
    }
    JButton button = new JButton ("Press Me");
    JLabel label = new JLabel ("nothin yet!");
    public EDT() {
        super("EDT");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);

        add( button , BorderLayout.NORTH );
        button.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e ) {
                label.setText("Started");
                new Thread(){
                    public void run(){
                        slowTask();
                        SwingUtilities.invokeLater(() -> label.setText("Finished"));
                    }
                }.start();

            }
        });

        add( label , BorderLayout.SOUTH );



        setVisible(true);

    }

    private void slowTask(){
        System.out.println("Started slowTask");
        try {
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            // ANTI anti pattern
        }
        System.out.println("Finished slowTask");
    }
}
