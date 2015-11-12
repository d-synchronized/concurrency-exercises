package chapter2;

public class Concurrency implements Runnable {

    private volatile boolean missedIt;

    private int value;
    
    public Concurrency() {
        value = 10;
        missedIt = false;
    }

    @Override
    public void run() {
        System.out.println("Entering threads run method");
        while(value < 20){
            if(missedIt){
                int valueBeforeSynch = value;
                System.out.println("Value before sync is "+ valueBeforeSynch);
                
                final Object object = new Object();
                synchronized(object){
                    //Do Nothing
                }
                
                int valueAfterSynch = value;
                System.out.println("Value after sync is "+ valueAfterSynch);
            }
        }
        System.out.println("Leaving threads run method");
    }
    
    public void runWorkerMethod(){
        System.out.println("successfully entered the worker method");
        System.out.println("Incrementing the threads value to 30");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        value = 30;
        try {
            System.out.println("worker thread about to sleep");
            Thread.sleep(5000);
            System.out.println("worker thread waking up after the small nap");
        } catch (InterruptedException e) {
            System.out.println("thread interrupted");
        }
        System.out.println("leaving the worker thread normally");
        missedIt = true;
    }
    
    public static void main(final String args[]) {
        final Concurrency concurrency = new Concurrency();
        try {
            System.out.println("Main thread going into a small nap");
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted during it's sleep");
        }
        System.out.println("Main thread woke up");
        final Thread thread = new Thread(concurrency);
        thread.start();
        try {
            System.out.println("Main thread going into a small nap");
            Thread.sleep(100);
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted during it's sleep");
        }
        concurrency.runWorkerMethod();
    }

}
