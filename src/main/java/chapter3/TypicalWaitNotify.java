package chapter3;

public class TypicalWaitNotify {

    private volatile boolean value;

    private volatile Object lockableObject;

    public void doSomeWaitingTask() throws InterruptedException {
        final Thread thread = Thread.currentThread();
        synchronized (lockableObject) {//remove this to see IllegalMonitorStateException
            if (!value) {
                System.out.println("Thread " + thread.getName() + " is about to go to wait until it will be notified");
                lockableObject.wait();
                System.out.println("Thread " + thread.getName() + " is coming out of the wait state , since somebody informed him about the changes to the object");
            }
        }
    }

    public void notifyAndReleaseWaitThreads() throws InterruptedException {
        // Thread.sleep(2000);// A brief stoppage here
        synchronized (lockableObject) {
            final Thread thread = Thread.currentThread();
            value = true;
            System.out.println(thread.getName() + "About to notify other threads that i have changed something in the object");
            lockableObject.notify();
            System.out.println(thread.getName() + "Just notified other threads to woke up");
        }
    }

    public static void main(final String args[]) {
        final TypicalWaitNotify typicalWaitNotify = new TypicalWaitNotify();
        typicalWaitNotify.value = false;
        typicalWaitNotify.lockableObject = new Object();

        final Runnable runnable = new Runnable() {
            public void run() {
                try {
                    typicalWaitNotify.doSomeWaitingTask();
                } catch (final InterruptedException e) {
                    System.out.println("Thread interrrupted during it's normal course of function");
                }
            }
        };
        final Thread threadA = new Thread(runnable);
        threadA.setName("Thread-A");
        threadA.start();

        final Runnable runnable2 = new Runnable() {
            public void run() {
                try {
                    typicalWaitNotify.notifyAndReleaseWaitThreads();
                } catch (InterruptedException e) {
                    System.out.println("Thread interrrupted during it's normal course of function");
                }
            }
        };
        final Thread threadB = new Thread(runnable2);
        threadB.setName("Thread-B");
        threadB.start();
        
        try {
            System.out.println("about to make , main thread to go to sleep");
            Thread.sleep(2000);
            System.out.println("main thread coming out of sleep");
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted during it's normal course of action");
        }
        
//        typicalWaitNotify.lockableObject = new Object();
//        synchronized (typicalWaitNotify.lockableObject) {
//            typicalWaitNotify.lockableObject.notifyAll();
//        }
    }

}
