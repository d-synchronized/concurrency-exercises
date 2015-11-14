package chapter3;

public class TestingThreadSafety {

    public static void main(final String args[]) {
        final ThreadSafeClass threadSafeClass = new ThreadSafeClass();
        final Runnable runnable = new Runnable() {

            public void run() {
                try {
                    Thread.sleep(200);
                    threadSafeClass.waitUntilTrue();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        final Thread threadA = new Thread(runnable, "Thread-A");
        threadA.start();

        final Runnable runnable2 = new Runnable() {

            public void run() {
                try {
                    threadSafeClass.waitUntilTrue();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        final Thread threadB = new Thread(runnable2, "Thread-B");
        threadB.start();

        final Runnable runnable3 = new Runnable() {

            public void run() {
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                threadSafeClass.releaseNotificatios();
            }
        };
        final Thread threadC = new Thread(runnable3, "Thread-C");
        threadC.start();
    }

}
