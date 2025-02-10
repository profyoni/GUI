import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class T3_GUI extends JFrame {
    public T3_GUI() {
        T3 model = new T3();
        setTitle("Tic Tac Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLayout(new GridLayout(3,3,5,5));
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                JButton b = new JButton();
                add(b);
                int finalI = i;
                int finalj = i;
                b.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        model.makeMove(finalI,finalj);
                        b.setText(model.currentPlayer().toString());
                    }
                });
            }
        }
        setVisible(true);
    }
}
