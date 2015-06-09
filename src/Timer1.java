import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Timer1 {
    static int interval;
    static Timer timer;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Input seconds: ");
        String secs = sc.nextLine();
        int delay = 1000;
        int period = 1000;
        timer = new Timer();
        interval = Integer.parseInt(secs);
        System.out.println(secs);
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                System.out.println(setInterval());
            }
        }, delay, period);
    }

    private static final int setInterval() {
        if (interval == 1)
            timer.cancel();
        return --interval;
    }

    int calculateScore(int timer) {
        int points = 0;

        if (timer == 10)
            points = 10;
        else if (timer <= 8)
            points = 8;
        else if (timer <= 6)
            points = 6;
        else if (timer <= 4)
            points = 4;
        else if (timer <= 2)
            points = 2;
        else
            points = 0;
        return points;
    }
}