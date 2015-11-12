package chapter2;

public class ThreadPriority implements Runnable {

    public void run() {
        for (int i = 0; i < 5; i++) {
            final Thread thread = Thread.currentThread();
            System.out.println("Entered the run method of thread   " + thread.getName() + " and yielding " + "counter " + i);
            Thread.yield();
            System.out.println("Leaving the run method of thread - " + thread.getName() + " counter "+ i);
        }
    }

    public static void main(final String args[]) {
        final ThreadGroup threadGroup = new ThreadGroup("DEMO_GROUP");
        threadGroup.setMaxPriority(8);
        
        final ThreadPriority priorityA = new ThreadPriority();
//        final ThreadPriority priorityB = new ThreadPriority();
        final ThreadPriority priorityC = new ThreadPriority();

        final Thread threadA = new Thread(threadGroup,priorityA);
        threadA.setName("A");
        threadA.setPriority(Thread.MAX_PRIORITY);
        threadA.start();

//        final Thread threadB = new Thread(priorityB);
//        threadB.setName("B");
//        threadA.setPriority(6);
//        threadB.start();

        final Thread threadC = new Thread(threadGroup, priorityC);
        threadC.setName("C");
        threadA.setPriority(3);
        threadC.start();
    }

}
