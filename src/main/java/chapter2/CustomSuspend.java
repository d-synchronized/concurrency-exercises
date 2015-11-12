package chapter2;

public class CustomSuspend implements Runnable {

    volatile boolean suspend = false;

    public void run() {
        System.out.println("Entered the thread run method");
        int counter = 0;
        boolean exit = false;
        do {
            if (suspend) {
                System.out.println("Thread suspended");
                exit = true;
            }
            System.out.println("Printing demo " + counter + "times");
            counter++;
        } while (!exit);
        System.out.println("Leaving the thread run method");
    }

    public static void main(final String args[]) {
        final CustomSuspend customSuspend = new CustomSuspend();
        final Thread thread = new Thread(customSuspend);
        thread.start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted in it's sleep");
        }
        customSuspend.suspend = true;
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted in its sleep");
        }
        System.out.println("After some time , lets cancel the suspension");
        customSuspend.suspend = false;
    }

}
