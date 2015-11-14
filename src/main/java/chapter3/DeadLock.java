package chapter3;

public class DeadLock {

    public synchronized void performSomeAction(final DeadLock deadLock) {
        final Thread thread = Thread.currentThread();
        System.out.println("Performing some action for " + thread.getName());

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println(thread.getName() + " waking up from his sleep");
        }

        deadLock.performSecondAction();
    }

    public synchronized void performSecondAction() {
        final Thread thread = Thread.currentThread();
        System.out.println(thread.getName() + "about to perform it' second action");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println(thread.getName() + "interrupted during it's sleep");
        }
        System.out.println("Srecond action performed successfully");
    }

    public static void main(final String args[]) {
        final DeadLock deadLock = new DeadLock();
        final DeadLock deadLock1 = new DeadLock();
        final Runnable runnable = new Runnable() {

            public void run() {
                deadLock.performSomeAction(deadLock1);
            }
        };
        final Thread threadA = new Thread(runnable , "ThreadA");
        threadA.start();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted during it's sleep");
        }
        
        final Runnable runnable2 = new Runnable() {

            public void run() {
                deadLock1.performSomeAction(deadLock);
            }
        };
        final Thread threadB = new Thread(runnable2 , "ThreadB");
        threadB.start();
        
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted during it's sleep");
        }
        
        threadB.interrupt();
    }
}
