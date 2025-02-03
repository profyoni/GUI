import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class LinesApp extends JFrame {
    private JButton clearCanvasButton = new JButton("Clear");
    private JButton drawButton = new JButton("Draw Random Circle");
    private JLabel statusBar = new JLabel("status bar");
    private static int  counter;
    LinesApp(){
        this.setTitle("Drawcula" + counter++);
        this.setSize(800, 600);
        // Borderlayout is default
        add(statusBar, BorderLayout.SOUTH);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(clearCanvasButton);
        buttonPanel.add(drawButton);
        add(buttonPanel, BorderLayout.NORTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel canvas = new JPanel();
        canvas.setBackground(Color.BLACK);
        add(canvas, BorderLayout.CENTER);

        drawButton.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Graphics g = canvas.getGraphics();

                g.setColor(Color.WHITE);
                g.drawOval(100, 20, 400, 200);

            }
        });

        clearCanvasButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Graphics g = canvas.getGraphics();
                g.setColor(Color.BLACK);
                g.drawOval(100, 20, 400, 200);
            }
        });

        this.addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent e) {
                new LinesApp();
            }

        });

        setVisible(true);
    }
}

public class Main {

     static class  MyEventListener implements ActionListener {
        // called when button pressed
        public void actionPerformed(ActionEvent e) {
            System.out.println("It works!");
          //  window.getContentPane().setBackground( randomColor() );
         //   pressMeButton.setBackground(randomColor());
        }
    }
    private static Color randomColor(){
        return new Color((float)Math.random(), (float)Math.random(), (float)Math.random());
    }


    public static void main(String[] args) {
        new LinesApp();
    }

//
//        JFrame window = new JFrame();
//        window.setTitle("Hello World" + counter++);
//        window.setSize(800, 600);
//        window.setLayout(new FlowLayout());
//        JButton pressMeButton = new JButton("Click Me");
//        window.add(pressMeButton);
//        window.add(new JTextField(20));
//        window.add(new JLabel("I'm a Laibel for Laibel"));
//        window.add(new JTextArea(10,40));
//        JPanel drawPanel = new JPanel();
//        drawPanel.setLayout(new GridLayout(6,2));
//        for (int i=0;i<6;i++) {
//            drawPanel.add(new JButton("" + i));
//            JTextField textField = new JTextField(10);
//            drawPanel.add(textField);
//            textField.addActionListener(new ActionListener() {
//                public void actionPerformed(ActionEvent e) {
//                    drawPanel.setBackground(randomColor());
//                }
//            });
//        }
//        drawPanel.add(new JLabel("take up space"));
//        window.add(drawPanel);
//
//        MyEventListener eventListener = new MyEventListener();//. instantiating event handler
//        pressMeButton.addActionListener( eventListener); // register the event handler
//
//        window.setVisible(true);
//        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
////
////        window.addWindowListener(new WindowAdapter() {
////            public void windowIconified(WindowEvent e) {
////                System.exit(0);
////            }
////
////            public void windowClosing(WindowEvent e) {
////                for (int i=0;i<10;i++)
////                    main(null);
////            }
////        });
//
//    }
}