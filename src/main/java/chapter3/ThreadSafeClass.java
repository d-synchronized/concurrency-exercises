package chapter3;

public class ThreadSafeClass {

    public synchronized void waitUntilTrue() throws InterruptedException {
        final Thread thread = Thread.currentThread();
        System.out.println(thread.getName() + "going to wait...now");
        wait();
        System.out.println(thread.getName() + "coming out of wait");
    }

    public synchronized void releaseNotificatios() {
        final Thread thread = Thread.currentThread();
        System.out.println(thread.getName() + " about to notify others to wakeup");
        notifyAll();
        System.out.println("Delivered successful notification to threads");
    }

}
