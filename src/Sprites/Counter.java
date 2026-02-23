// 322824806 Omer Ahronson
package Sprites;
/**
 * A class for counter.
 */
public class Counter {
    private int counter;

    /**
     * Constructor method.
     * @param initialNumber the initial starting value of the counter.
     */
    public Counter(int initialNumber) {
        this.counter = initialNumber;
    }

    /**
     * add number to current count.
     * @param number the number.
     */
    public void increase(int number) {
        this.counter += number;
    }

    /**
     * subtract a number from current count.
     * @param number the number.
     */
    public void decrease(int number) {
        this.counter -= number;
    }

    /**
     * get current count.
     * @return current counter's value.
     */
    public int getValue() {
        return this.counter;
    }
}