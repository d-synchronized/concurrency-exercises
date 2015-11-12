package chapter2;

public class PendingInterrupts implements Runnable{
    
    public static void main(final String args[]){
        final Thread thread = Thread.currentThread();
        thread.interrupt();
        final PendingInterrupts pendingInterrupts = new PendingInterrupts();
        final Thread pendingInterruptThread = new Thread(pendingInterrupts);
        pendingInterruptThread.start();
        
        if(thread.isInterrupted()){
            System.out.println("Main thread was interrrupted before sleep");
        }
        
        if(thread.isInterrupted()){
            System.out.println("checking thread interruption 2nd time.");
        }
        
        if(Thread.interrupted()){
            System.out.println("checking thread interruption 3rd time.");
        }
        
        System.out.println("Main thread going to sleep");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted");
        }
        
        if(thread.isInterrupted()){
            System.out.println("Main thread was interrrupted during it's normal operations");
        }
        
        if(pendingInterruptThread.isInterrupted()){
            System.out.println("Main thread was interrrupted during it's normal operations");
        }
        
    }

    @Override
    public void run() {
        System.out.println("running for the first time");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("Thread interuppted ....");
        }
        System.out.println("leaving the run method");
    }

}
