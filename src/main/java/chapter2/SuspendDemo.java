package chapter2;

public class SuspendDemo implements Runnable{
    
    public void run(){
        int counter = 0;
        do{
            counter++;
            System.out.println("Demo prnting "+ counter + "times");
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }while(counter < 200);
    }
    
    public static void main(final String args[]){
        final SuspendDemo suspendDemo = new SuspendDemo();
        final Thread thread = new Thread(suspendDemo);
        thread.start();
        
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            System.out.println("Main thread sleeping");
        }
        thread.suspend();
        System.out.println("Suspending thread...");
        
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println("Main thread sleeping again");
        }
        thread.resume();
        System.out.println("Back in main again");
    }

}
