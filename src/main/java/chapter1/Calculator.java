/*******************************************************************************
 * Copyright 2013, Barbon. All Rights Reserved. 
 * No part of this content may be used without Barbon's express consent.
 ******************************************************************************/
package chapter1;

/**
 * The Class Calculator.
 */
public class Calculator implements Runnable {

    /** The number. */
    private int number;

    /**
     * Instantiates a new calculator.
     * 
     * @param number the number
     */
    public Calculator(final int number) {
        this.setNumber(number);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Runnable#run()
     */
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.printf("%s : %d * %d = %d\n", Thread.currentThread().getName(), number, i, i * number);
        }
    }

    /**
     * Gets the number.
     * 
     * @return the number
     */
    public int getNumber() {
        return number;
    }

    /**
     * Sets the number.
     * 
     * @param number the new number
     */
    public void setNumber(final int number) {
        this.number = number;
    }

    /**
     * The main method.
     * 
     * @param args the arguments
     */
    public static void main(final String args[]) {
        for (int j = 0; j < 10; j++) {
            final Calculator calculator = new Calculator(j);
            final Thread thread = new Thread(calculator);
            thread.start();
        }
    }
}
