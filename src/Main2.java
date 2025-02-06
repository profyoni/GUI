import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

class DrawApp extends JFrame
{
    private JLabel statusBar;
    private class CanvasPanel extends JPanel{

        private int paintCounter;
        @Override
        public void paint(Graphics g)
        {
            super.paint(g); // sit on the shoulders of JPanel

            g.setColor(Color.PINK);
            for ( Point p : points) {
                g.drawOval((int) p.getX(), (int) p.getY(), 500, 200);
            }
            statusBar.setText(paintCounter++ + "");
        }
    }
    ArrayList<Point> points = new ArrayList<Point>();
    public DrawApp(){
        this.setTitle("Draw App");
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        statusBar = new JLabel("status bar");
        add(statusBar, BorderLayout.SOUTH);

        JPanel buttonPanel = new JPanel();
        JButton drawButton = new JButton("Draw");
        JButton clearButton = new JButton("Clear");
        buttonPanel.add(drawButton);
        buttonPanel.add(clearButton);
        add(buttonPanel, BorderLayout.NORTH);

        JPanel canvasPanel = new CanvasPanel();
        canvasPanel.setBackground(Color.BLACK);
        add( canvasPanel, BorderLayout.CENTER);

        canvasPanel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                statusBar.setText(e.getPoint().toString());

                Graphics g = canvasPanel.getGraphics();
                g.setColor(Color.PINK);
                g.drawOval(e.getX(), e.getY(), 500, 200);
                points.add(e.getPoint());
            }

            private Point pressedPoint, releasedPoint;
            @Override
            public void mousePressed(MouseEvent e) {
                pressedPoint = e.getPoint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                releasedPoint = e.getPoint();
                int w = releasedPoint.x - pressedPoint.x;
                int h = releasedPoint.y - pressedPoint.y;

                Graphics g = canvasPanel.getGraphics();
                g.setColor(Color.PINK);
                g.drawOval(pressedPoint.x, pressedPoint.y, w, h);
                points.add(pressedPoint);

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        this.setVisible(true);
    }
}

public class Main2 {
    static int c;
    public static void main(String[] args) {
        Object o = new DrawApp();


//        window.setLayout(new GridLayout(3,4));
////        window.add(new JTextField(20));
////        window.add(new JLabel("I'm a Label for Laibel"));
////        window.add(new JTextArea(10,40));
//        for (int i=0;i<12;i++) {
//            JButton button = new JButton("" + i);
//            window.add(button);
//
//            button.addActionListener(new MyActionListener(button));
//            button.addActionListener( new ActionListener() {
//                        // gets called when button is pressed
//                        public void actionPerformed(ActionEvent e) {
//                            float grey = Integer.parseInt(e.getActionCommand()) / 11.0f;
//                            button.setBackground(new Color(grey,grey,1));
//                        }
//                    }
//            );
//        }
//
//        window.setVisible(true);

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

//    private static class MyActionListener implements ActionListener {
//        private JButton button;
//        MyActionListener(JButton button) {
//                this.button = button;
//             }
//              // gets called when button is pressed
//            public void actionPerformed(ActionEvent e) {
//                float grey = Integer.parseInt(e.getActionCommand()) / 11.0f;
//                button.setBackground(new Color(grey,grey,1));
//            }
//        }
//    }

