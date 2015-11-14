package chapter3;

public class MissedNotification {
    
    private volatile boolean okToProceed;
    
    public MissedNotification(){
        okToProceed = false;
    }

    public synchronized void waitUntilTrue() throws InterruptedException {
        final Thread thread = Thread.currentThread();
        System.out.println(thread.getName() + "going to wait...now");
        if(!okToProceed){
            wait();
        }
        System.out.println(thread.getName() + "coming out of wait");
    }

    public synchronized void releaseNotificatios() {
        final Thread thread = Thread.currentThread();
        System.out.println(thread.getName() + " about to notify others to wakeup");
        notify();
        okToProceed = true;
        System.out.println("Delivered successful notification to threads");
    }

    public static void main(final String args[]) {
        final MissedNotification missedNotification = new MissedNotification();
        final Runnable runnable = new Runnable() {
            public void run() {
                try {
                    Thread.sleep(200);
                    missedNotification.waitUntilTrue();
                } catch (InterruptedException e) {
                    System.out.println("Thread interreupted during it's normal course of operation");
                }
            }
        };
        final Thread threadA = new Thread(runnable, "Thread-A");
        threadA.start();

        final Runnable runnable2 = new Runnable() {
            public void run() {
                missedNotification.releaseNotificatios();
            }
        };
        final Thread threadB = new Thread(runnable2, "Thread-B");
        threadB.start();

    }

}
