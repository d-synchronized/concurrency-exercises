package chapter2;

public class CounterClock {

    public void runClock() throws InterruptedException {
        int second = 0;
        int hour = 0;
        int minute = 0;

        while (true) {
            Thread.sleep(1000);
            second++;
            if (second > 59) {
                minute++;
                second = 0;
            }
            if (minute > 59) {
                hour++;
                minute = 0;
            }

            if (hour > 12) {
                hour = 1;
            }
            System.out.println("hour " + hour + ": minutes - " + minute + " second - " + second);
        }
    }

    public static void main(final String args[]) {
        final CounterClock counterClock = new CounterClock();
        try {
            counterClock.runClock();
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }

}
