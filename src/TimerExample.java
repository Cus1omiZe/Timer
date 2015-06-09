import java.awt.Rectangle;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class TimerExample extends JFrame {

    class UpdateTextFieldTask extends TimerTask {
        private int seconds = 10;
        @Override public void run() {
            if ( seconds > 0 ) {
                SwingUtilities.invokeLater(new Runnable(){
                    public void run() {
                        // правильно обновл€ем значение пол€
                        jTextField.setText(String.valueOf(seconds--));
                    }
                });
                // 20 секунд отсчитали? ќстанавливаем задачу
            } else cancel();
        }
    }

    private static final long serialVersionUID = 1L;
    private JPanel jContentPane = null;
    private JTextField jTextField = null;
    private JButton jButton = null;
    private Timer timer = new Timer();
    private TimerTask timerTask = null;
    private boolean scheduled = false;

    private JTextField getJTextField() {
        if (jTextField == null) {
            jTextField = new JTextField();
            jTextField.setBounds(new Rectangle(30, 30, 231, 29));
        }
        return jTextField;
    }

    private JButton getJButton() {
        if (jButton == null) {
            jButton = new JButton();
            jButton.setBounds(new Rectangle(93, 90, 107, 38));
            jButton.setText("Start timer");
            jButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    if ( !scheduled ) {
                        scheduled = true;
                        jButton.setText("Stop timer");
                        timerTask = new UpdateTextFieldTask();
                        // «апускаем таймер на выполнение задачи UpdateTextFieldTask каждую секунду
                        timer.schedule(timerTask , new Date(), 1000);
                    } else {
                        jButton.setText("Start timer");
                        timerTask.cancel();
                        scheduled = false;
                        timerTask = null;
                    }
                }
            });
        }
        return jButton;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                TimerExample thisClass = new TimerExample();
                thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                thisClass.setVisible(true);
            }
        });
    }

    public TimerExample() {
        super();
        initialize();
    }

    private void initialize() {
        this.setSize(300, 200);
        this.setResizable(false);
        this.setContentPane(getJContentPane());
        this.setTitle("Test Timer");
    }

    private JPanel getJContentPane() {
        if (jContentPane == null) {
            jContentPane = new JPanel();
            jContentPane.setLayout(null);
            jContentPane.add(getJTextField(), null);
            jContentPane.add(getJButton(), null);
        }
        return jContentPane;
    }
}