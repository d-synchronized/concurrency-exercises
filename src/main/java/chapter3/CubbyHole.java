package chapter3;

public class CubbyHole {

    private Object slot;

    public synchronized void putIn(final Object object) throws InterruptedException {
        final Thread thread = Thread.currentThread();
        if (slot != null) {
            System.out.println(thread.getName() + " thread entering wait");
            wait();
            System.out.println(thread.getName() + "thread successfully moved out from wait");
        }
        slot = object;
    }

    public synchronized void takeOut() throws InterruptedException {
        final Thread thread = Thread.currentThread();
        if (slot != null) {
            System.out.println("about to notifying other threads to proceed");
            notifyAll();
            slot = null;
            System.out.println("Notification proceeded successfully");
        }else{
            System.out.println(thread.getName() + "thread going to wait while taking the object out");
            wait();
            System.out.println(thread.getName() + "thread going to wait while taking the object out");
        }
        System.out.println("moving out of the take out method");
    }

    public static void main(final String args[]) throws InterruptedException {
        final Object object = new Object();
        final CubbyHole cubbyHole = new CubbyHole();

        final Runnable runnable = new Runnable() {
            public void run() {
                try {
                    cubbyHole.putIn(object);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        final Thread threadA = new Thread(runnable, "Thread-A");
        threadA.start();
        
        Thread.sleep(1000);

        final Object object2 = new Object();
        final Runnable runnable1 = new Runnable() {
            public void run() {
                try {
                    cubbyHole.putIn(object2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        final Thread threadB = new Thread(runnable1, "Thread-B");
        threadB.start();
        
        Thread.sleep(1000);

        final Object object3 = new Object();
        final Runnable runnable3 = new Runnable() {
            public void run() {
                try {
                    cubbyHole.putIn(object3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        final Thread threadD = new Thread(runnable3, "Thread-D");
        threadD.start();
        
        Thread.sleep(1000);

        final Runnable runnable2 = new Runnable() {
            public void run() {
                try {
                    cubbyHole.takeOut();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        final Thread threadC = new Thread(runnable2, "Thread-C");
        threadC.start();
    }

}
