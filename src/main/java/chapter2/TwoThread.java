package chapter2;

public class TwoThread extends Thread {

    private Thread creatorThread;

    public TwoThread() {
        creatorThread = Thread.currentThread();
    }

    public void run() {
        for (int i = 0; i < 10; i++) {
            printMessages();
        }
    }

    private void printMessages() {
        final Thread thread = Thread.currentThread();
        if (thread == creatorThread) {
            System.out.println("Main Thread => " + thread.getName());
        } else if (thread == this) {
            System.out.println("Current Thread => " + thread.getName());
        } else {
            System.out.println("Unknown Thread");
        }
    }

    public static void main(final String args[]) {
        final TwoThread twoThread = new TwoThread();
        twoThread.setName("New Thread");
        twoThread.start();
        for (int i = 0; i < 10; i++) {
            twoThread.printMessages();
        }
    }

}
