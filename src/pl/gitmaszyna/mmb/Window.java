/**
 * 17 maj 2015
 * Author Grzegorz Różycki <grozycki@op.pl>
 */
package pl.gitmaszyna.mmb;

import java.awt.AWTException;
import java.awt.EventQueue;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.Timer;


/**
 * @author grzechu
 *
 */
public class Window {

    private final class UserTimeout implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // set lastPosition to the current position and quit,
            // this way at next timer invocation if the user didn't move
            // the mouse the bot can continue his work
            lastPosition = MouseInfo.getPointerInfo().getLocation();
            userTimeout.stop();
            userTimeout = null;
        }

    }

    private final class MovementTimer implements ActionListener {

        private MovementStrategy movement;

        public MovementTimer(MovementStrategy movement) {
            this.movement = movement;
        }

        @Override
        public void actionPerformed(ActionEvent event) {
            final Point currentPosition = MouseInfo.getPointerInfo().getLocation();

            if (lastPosition.equals(currentPosition)) {
                // movement can be performed
                if (!movement.makeMove()) {
                    // no next moves...
                    toggleRunningState();
                } else {
                    final Point point = movement.getLocation();
                    lastPosition = point;

                    try {
                        new Robot().mouseMove((int) point.getX(), (int) point.getY());
                    } catch (AWTException e) {
                        e.printStackTrace();
                    }
                }
            } else if (userTimeout == null) {
                userTimeout = new Timer(USER_TIMEOUT, new UserTimeout());
                userTimeout.setRepeats(false);
                userTimeout.start();
            }
        }
    }

    private Point lastPosition = null;
    private Timer movementLoop = null;
    private Timer userTimeout = null;
    private JFrame frame = null;
    private JComboBox movementTypeComboBox = null;
    private JButton startStopButton = null;
    private MovementStrategy[] movements = {
            new StubStrategy(),
            new CircleStrategy(),
    };

    public static final int MOVE_OFFSET = 1000 / 30;    // 30 Hz
    public static final int USER_TIMEOUT = 3000;        // 3 seconds


    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                try {
                    Window window = new Window();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public Window() {
        initialize();
    }

    @SuppressWarnings("deprecation")
    private void toggleRunningState() {

        if (movementLoop != null) {
            movementLoop.stop();
            movementLoop = null;
            movementTypeComboBox.enable(true);
            startStopButton.setText("Start");
        } else {
            final int selectedIndex = movementTypeComboBox.getSelectedIndex();
            lastPosition = MouseInfo.getPointerInfo().getLocation();
            movementLoop = new Timer(Window.MOVE_OFFSET,
                    new MovementTimer(movements[selectedIndex]));
            movementLoop.start();
            movementTypeComboBox.enable(false);
            startStopButton.setText("Stop");
        }
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        final String[] movementNames = new String[movements.length];

        for (int i = 0; i < movements.length; i += 1) {
            movementNames[i] = movements[i].getName();
        }

        frame = new JFrame();

        frame.setResizable(false);
        frame.setBounds(100, 100, 368, 87);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        startStopButton = new JButton("Start");
        startStopButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                toggleRunningState();
            }
        });
        startStopButton.setBounds(224, 12, 130, 24);
        frame.getContentPane().add(startStopButton);

        movementTypeComboBox = new JComboBox(movementNames);
        movementTypeComboBox.setBounds(12, 12, 200, 24);
        frame.getContentPane().add(movementTypeComboBox);
    }

}
