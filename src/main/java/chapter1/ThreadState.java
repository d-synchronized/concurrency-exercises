/*******************************************************************************
 * Copyright 2013, Barbon. All Rights Reserved. 
 * No part of this content may be used without Barbon's express consent.
 ******************************************************************************/
package chapter1;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.Thread.State;

/**
 * The Class ThreadState.
 */
public class ThreadState implements Runnable {

    /** The number. */
    private final int number;

    /**
     * Instantiates a new thread state.
     * 
     * @param number the number
     */
    public ThreadState(final int number) {
        this.number = number;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.printf("%s : %d*%d = %d\n", Thread.currentThread().getName(), i, number, i * number);
        }
    }

    /**
     * The main method.
     * 
     * @param args the arguments
     */
    public static void main(final String args[]) {

        final Thread[] threads = new Thread[10];
        final Thread.State[] state = new Thread.State[10];

        for (int i = 0; i < threads.length; i++) {
            final ThreadState calculator = new ThreadState(i);
            threads[i] = new Thread(calculator);
            if (i % 2 == 0) {
                threads[i].setPriority(Thread.MAX_PRIORITY);
            } else {
                threads[i].setPriority(Thread.MIN_PRIORITY);
            }
            threads[i].setName("Thread-" + i);
        }

        // Initialize the print writer object for writing to the file.
        try (FileWriter fileWriter = new FileWriter("C:/Users/Ajay/Desktop/thread-info.txt"); PrintWriter printWriter = new PrintWriter(fileWriter)) {
            for (int i = 0; i < threads.length; i++) {
                printWriter.write("Current state of thread 'Thread-" + i + "' is " + threads[i].getState() + "\n");
                state[i] = threads[i].getState();
            }

            for (int i = 0; i < threads.length; i++) {
                threads[i].start();
            }

            boolean finish = false;
            while (!finish) {
                for (int i = 0; i < threads.length; i++) {
                    if (threads[i].getState() != state[i]) {
                        // write to the file..
                        writeThreadInfoToFile(printWriter, threads[i], state[i]);
                        state[i] = threads[i].getState();
                    }
                }
                finish = true;
                for (int i = 0; i < threads.length; i++) {
                    finish = finish && threads[i].getState() == State.TERMINATED;
                }
            }
        } catch (final IOException ioException) {
            System.out.println("Error occurred while creating print writer for the file.., Error -" + ioException);
        }

    }

    /**
     * Write thread info to file.
     * 
     * @param printWriter the print writer
     * @param thread the thread
     * @param threadState the thread state
     */
    private static void writeThreadInfoToFile(final PrintWriter printWriter, final Thread thread, final State threadState) {
        printWriter.write("****************Thread INFO**********************\n");
        printWriter.write("Thread Name - " + thread.getName());
        printWriter.write("\nThread Id - " + thread.getId());
        printWriter.write("\nThread Priority - " + thread.getPriority());
        printWriter.write("\nThread Old State - " + threadState);
        printWriter.write("\nThread New State - " + thread.getState());
        printWriter.write("\n**************Thread Info ENDS*********************\n");
    }

}
