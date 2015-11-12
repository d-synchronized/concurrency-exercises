package chapter2;

public class DaemonThread {
    
    public void doSomeWork(){
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                for(int i=0 ; i< 10 ; i++){
                    System.out.println("Priting demo " +  i + " times");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        System.out.println("Thread woke up improperly");
                    }
                }
                System.out.println("Leaving the run method");
            }
        };
        
        
        final Thread thread = new Thread(runnable);
        thread.setDaemon(true);
        thread.start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Skipping main");
        
    }
    
    public static void main(final String args[]){
        final DaemonThread daemonThread = new DaemonThread();
        daemonThread.doSomeWork();
    }

}
