package chapter2;

public class InteruptExample implements Runnable {

    @Override
    public void run() {
        final Thread thread = Thread.currentThread();
        System.out.println("Inside the thread " + thread.getName());
        try {
            System.out.println("Putting the thread on to sleep ...");
            Thread.sleep(10000);
            System.out.println("Thread waking up normally");
        } catch (InterruptedException interruptedException) {
            System.out.println("Thread interuptted in between of his sleep");
        }
        System.out.println("Leaving the thread " + thread.getName());
    }

    public static void main(final String args[]) {
        final InteruptExample interuptExample = new InteruptExample();
        final Thread thread = new Thread(interuptExample);
        thread.start();

        
        try {
            System.out.println("Putting the main thread on to sleep");
            Thread.sleep(2000);
        } catch (final InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
        thread.interrupt();
    }

}
