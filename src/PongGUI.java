import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PongGUI extends JFrame {
    public static void main(String[] args) {
        new PongGUI();
    }
    private JPanel panel = new JPanel();
    public PongGUI() {
        setTitle("Pong");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);

        panel.setBackground(Color.BLACK);
        add(panel);
        setVisible(true);
        ball = new Point(250,250);
        Timer timer = new Timer(50,
                new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ball.translate(delta.x, delta.y);
                if (ball.x > 500 || ball.x < 0)
                    delta.x = -delta.x;
                if (ball.y > 500 || ball.y < 0)
                    delta.y = -delta.y;
                repaint();
            }
                });
        timer.start();
             //   this.getMaximumSize().width/2,
             //   this.getMaximumSize().height/2);
    }

    private Point ball;
    private Point delta = new Point(5,5);

    public void paint(Graphics g) {
        super.paint(g);

        System.out.println("paint");
        g = panel.getGraphics();
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.WHITE);
        g2d.fillOval(ball.x, ball.y, 40, 40);
    }
}

