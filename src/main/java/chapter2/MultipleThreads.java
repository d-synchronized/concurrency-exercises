package chapter2;

public class MultipleThreads {

    public synchronized void workerThread(final int value) {
        final Thread thread = Thread.currentThread();
        int number = value * 2;
        try {
            System.out.println(thread.getName() + "about to go to sleep");
            Thread.sleep(1000);
        } catch (InterruptedException interruptedException) {
            System.out.println("Thread interrupted prematurely");
        }
        System.out.println("Leaving the worker thread for thread " + thread.getName() + " , " + number);
    }

    public static void main(final String args[]) {
        final MultipleThreads multipleThreads = new MultipleThreads();
        final Runnable runnable = new Runnable() {

            @Override
            public void run() {
                multipleThreads.workerThread(4);
            }
        };
        
        final Thread threadA = new Thread(runnable);
        threadA.start();

        final Runnable runnable2 = new Runnable() {

            @Override
            public void run() {
                multipleThreads.workerThread(2);
            }
        };
        final Thread threadB = new Thread(runnable2);
        threadB.start();
    }

}
