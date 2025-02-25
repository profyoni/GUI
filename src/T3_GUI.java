import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class T3_GUI extends JFrame {
    private T3_Model model = new T3_Model();
    public T3_GUI() {

        setTitle("Tic Tac Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 600);

        JPanel t3Panel = new JPanel();
        JPanel northPanel = new JPanel();

        add(northPanel, BorderLayout.NORTH);
        add(t3Panel, BorderLayout.CENTER);
        add(new JLabel("Welcome to Tic Tac Toe"), BorderLayout.SOUTH);

        t3Panel.setLayout(new GridLayout(3,3,5,5));
        ControllerEventHandler ceh = new ControllerEventHandler();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                T3_JButton b = new T3_JButton(i,j);
                t3Panel.add(b);
                b.addActionListener(ceh);
            }
        }
        setVisible(true);
    }
    private static class T3_JButton extends JButton {
        public T3_JButton(int i, int j){ this.i = i; this.j = j;}
        int i, j;
    }
    private class ControllerEventHandler implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                T3_JButton b = (T3_JButton)e.getSource();
                model.makeMove(b.i,b.j);
                b.setFont(new Font("Arial", Font.PLAIN, 150));
                b.setText(model.currentPlayer().toString());
                b.setEnabled(false);

                if (model.getWinner() != T3_Model.Player.__ || model.isFull()){
                    // update status
                    // new Game button change color
                    // disable all buttons
                }
            }

    }
}
