package chapter3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class SynchronizedCollections {
    
    
    public void createArrayOfStrings(){
        final Thread thread = Thread.currentThread();
        List<String> list = Collections.synchronizedList(new ArrayList<String>());
//        List<String> list = new ArrayList<String>();
        list.add("Opportunities" + thread.getName());
        list.add("come" + thread.getName());
        list.add("hunting" + thread.getName());
        list.add("for" + thread.getName());
        list.add("strong" + thread.getName());
        list.add("leaders" + thread.getName());
        System.out.println("About to create an array for "+ thread.getName());
        final String[] stringArray = list.toArray(new String[0]);
        
        for(final String string : stringArray){
            System.out.println(string);
        }
    }
    
    public static void main(final String args[]){
//        final List<String> list = Collections.synchronizedList(new ArrayList<String>());
        final List<String> list = new ArrayList<String>();
        
        final SynchronizedCollections synchronizedCollections = new SynchronizedCollections();
        
        final Runnable runnable = new Runnable() {
            public void run() {
                synchronizedCollections.createArrayOfStrings();
            }
        };
        final Thread threadA = new Thread(runnable);
        threadA.setPriority(4);
        threadA.start();
        
        final Runnable runnable1 = new Runnable() {
            public void run() {
                synchronizedCollections.createArrayOfStrings();
            }
        };
        final Thread threadB = new Thread(runnable1);
        threadB.setPriority(6);
        threadB.start();
        
        try {
            System.out.println("Main thread going to sleep for 2 seconds");
            Thread.sleep(2000);
            System.out.println("Main thread woke up on time");
        } catch (InterruptedException e) {
            System.out.println("Main Thread interrupted during its sleep");
        }
    }

}
