package chapter2;

public class BestReplacement {
    
    
    public static void main(final String args[]){
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
            }
        };
        
        final Thread thread = new Thread(runnable);
        thread.start();
        
    }

}
