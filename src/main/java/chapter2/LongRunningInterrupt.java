package chapter2;

public class LongRunningInterrupt implements Runnable{
    
    public void run(){
        for(int i=0 ; i< 1000 ; i++){
            System.out.println("Inside the new thread...printing " + i + "times");
            
            if(Thread.interrupted()){
                System.out.println("Leaving the new thread, since it was interrupted");
                return;
            }
            System.out.println("Leaving run");
        }
    }
    
    public static void main(final String args[]){
        final LongRunningInterrupt longRunningInterrupt = new LongRunningInterrupt();
        final Thread thread = new Thread(longRunningInterrupt);
        thread.start();
        
        try {
            System.out.println("Main thread sleeping");
            Thread.sleep(10);
            thread.interrupt();
            System.out.println("Main thread waking up back");
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted");
        }
    }
    
    
}
