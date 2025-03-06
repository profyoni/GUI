import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

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
        panel.addMouseWheelListener(new MouseWheelListener() {
            public void mouseWheelMoved(MouseWheelEvent e) {
                paddle.translate(0, -e.getWheelRotation() * 10);
            }
        });
        setVisible(true);
        ball = new Point(250,250);
        Timer timer = new Timer(50,
                new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ball.translate(delta.x, delta.y);
                if (ball.x > 600 || ball.x < 0)
                    delta.x = -delta.x;
                if (ball.y > 500 || ball.y < 0)
                    delta.y = -delta.y;
                if (ball.x < paddle.x + 20 && ball.y > paddle.y && ball.x < paddle.x + 20 && ball.y < paddle.y + 120){
                    delta.x = -delta.x;
                }
                repaint();
            }
                });
        timer.start();
        JButton button = new JButton("Pong");
        add(button, BorderLayout.NORTH);

        add(statusBar, BorderLayout.SOUTH);
    }

    private JLabel statusBar = new JLabel("I work") ;
    private Point ball, paddle = new Point(20,250);
    private Point delta = new Point(5,5);

    public void paint(Graphics g) {
        super.paint(g);
        g = panel.getGraphics();
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.WHITE);
        g2d.fillOval(ball.x, ball.y, 40, 40);

        g2d.fillRect(paddle.x, paddle.y, 10, 120);
    }

    private void verySlowMethod()  {
        try {
            System.out.println("started");
            Thread.sleep(10_000);
            System.out.println("finished");
        } catch (InterruptedException e) {
            // ANTI-anti-pattern
        }
    }
}

