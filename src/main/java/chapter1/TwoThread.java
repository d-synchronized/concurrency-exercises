package chapter1;

public class TwoThread extends Thread {

    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("New Thread");
        }
    }

    public static void main(final String args[]) {
        final TwoThread twoThread = new TwoThread();
        twoThread.start();

        for (int i = 0; i < 10; i++) {
            System.out.println("Main Thread");
        }

    }

}
