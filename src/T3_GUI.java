import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class T3_GUI extends JFrame {

    public T3_GUI() {
        T3_Model model = new T3_Model();
        setTitle("Tic Tac Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 600);

        JPanel t3Panel = new JPanel();
        JPanel northPanel = new JPanel();

        add(northPanel, BorderLayout.NORTH);
        add(t3Panel, BorderLayout.CENTER);
        add(new JLabel("Welcome to Tic Tac Toe"), BorderLayout.SOUTH);

        t3Panel.setLayout(new GridLayout(3,3,5,5));
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                JButton b = new JButton();
                t3Panel.add(b);
                int finalI = i;
                int finalj = i;
                b.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        model.makeMove(finalI,finalj);
                        b.setFont(new Font("Arial", Font.PLAIN, 150));
                        b.setText(model.currentPlayer().toString());
                        b.setEnabled(false);

                        if (model.getWinner() != T3_Model.Player.__ || model.isFull()){
                            // update status
                            // new Game button change color
                            // disable all buttons
                        }
                    }
                });
            }
        }
        setVisible(true);
    }
}
