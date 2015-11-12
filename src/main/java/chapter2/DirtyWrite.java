package chapter2;

public class DirtyWrite {
    
    private String firstName;
    
    private String lastName;
    
    public synchronized void setNames(final String firstName , final String lastName){
        final Thread thread = Thread.currentThread();
        this.firstName = firstName;
        try {
            System.out.println("Thread "+ thread.getName() + " going to sleep");
            Thread.sleep(1000);
            System.out.println("Thread "+ thread.getName() + " waking up");
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted while it's sleep");
        }
        this.lastName = lastName;
        System.out.println("First name "+ this.firstName + ", last name "+ this.lastName);
    }
    
    public static void main(final String args[]) throws InterruptedException{
        final DirtyWrite dirtyWrite = new DirtyWrite();
        final Runnable runnable = new Runnable() {
            
            @Override
            public void run() {
                dirtyWrite.setNames("Dishant", "Anand");
            }
        };
        final Thread threadA = new Thread(runnable);
        threadA.start();
        
        Thread.sleep(200);
        
        final Runnable runnable2 = new Runnable() {
            
            @Override
            public void run() {
                dirtyWrite.setNames("Ajay", "Deshwal");
            }
        };
        final Thread threadB = new Thread(runnable2);
        threadB.start();
    }

}
