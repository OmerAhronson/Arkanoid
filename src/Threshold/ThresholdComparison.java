package Threshold;// 322824806 Omer Ahronson
/**
 * A class to implement threshold comparison.
 */
public class ThresholdComparison {
    private static final double EPSILON = 1e-9;
    /**
     * Comparing two doubles by using a threshold.
     *
     * @param a first double.
     * @param b second double.
     * @return true if equal, false if not.
     */
    public static boolean isEqual(double a, double b) {
        return (Math.abs(a - b) < EPSILON);
    }
    /**
     * Checking if 'a' is larger than 'b' by using Epsilon.
     * @param a a.
     * @param b b.
     * @return true or false.
     */
    public static boolean aMoreThanB(double a, double b) {
        return ((a - b) > EPSILON);
    }
    /**
     * Checking if 'a' is smaller than 'b' by using Epsilon.
     * @param a a.
     * @param b b.
     * @return true or false.
     */
    public static boolean aLessThanB(double a, double b) {
        return ((b - a) > EPSILON);
    }


    /**
     * Comparing two doubles by using a threshold.
     *
     * @param a first double.
     * @param b second double.
     * @return true if  a<=b, false if not.
     */
   public static boolean aLessOrEqualB(double a, double b) {
        return (Math.abs(a - b) < EPSILON || aLessThanB(a, b));
    }

    /**
     * Comparing two doubles by using a threshold.
     *
     * @param a first double.
     * @param b second double.
     * @return true if  a>=b, false if not.
     */
    public static boolean aMoreOrEqualB(double a, double b) {
        return (Math.abs(a - b) < EPSILON || aMoreThanB(a, b));
    }

    /**
     * Checks if double a<=b<=c.
     * @param a a.
     * @param b b.
     * @param c c.
     * @return true or false.
     */
    public static boolean bLessThanAAndSmallerThanC(double a, double b, double c) {
        return (aLessOrEqualB(a, b) && aLessOrEqualB(b, c));
    }
}
