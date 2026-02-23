// 322824806 Omer Ahronson
package Geometry;
import Threshold.ThresholdComparison;

/**
 * A class that defines a Geometry.Line.
 */

public class Line {
    private final Point start, end;
    private boolean isVertical;
    /**
     * Constructor for Geometry.Line by 2 Points, will calculate also slope and intercept.
     *
     * @param start Starting point.
     * @param end   Ending point.
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
        this.isVertical = ThresholdComparison.isEqual(start.getX(), end.getX());
    }

    /**
     * A method that checks if the line is vertical or not.
     * @return true or false.
     */
    public boolean isVertical() {
        return this.isVertical;
    }
    /**
     * Constructor for Geometry.Line by given coordinates, will calculate also slope and intercept.
     *
     * @param x1 x coordinates of the first point
     * @param y1 y coordinates of the first point
     * @param x2 x coordinates of the second point
     * @param y2 y coordinates of the second point
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
        this.isVertical = ThresholdComparison.isEqual(start.getX(), end.getX());

    }

    /**
     * Returns the length of the line, by using the starting points method of length calculation from the other point.
     *
     * @return The length.
     */
    public double length() {
        return start.distance(end);
    }

    /**
     * Returns the middle point of the line.
     *
     * @return The middle point.
     */
    public Point middle() {
        return new Point(((this.start.getX() + this.end.getX()) / 2),
                ((this.start.getY() + this.end.getY()) / 2));
    }

    /**
     * Returns the start point of the line.
     *
     * @return the starting point.
     */
    public Point start() {
        return this.start;
    }

    /**
     * Returns the end point of the line.
     *
     * @return the ending point.
     */
    public Point end() {
        return this.end;
    }

    /**
     * returns true if the lines are equal, false otherwise.
     *
     * @param other The other line.
     * @return true or false.
     */
    public boolean equals(Line other) {
        return ((this.start().equals(other.start())) && (this.end().equals(other.end()))
                || (this.start().equals(other.end())) && (this.end().equals(other.start())));
    }

    /**
     * Returns the intersection of 2 vertical lines, if there are infinite intersections, returns null.
     *
     * @param other The other line.
     * @return true or false.
     */
    public Point bothVerticalsIntersection(Line other) {
        if (!ThresholdComparison.isEqual(this.start().getX(), other.start().getX())) {
            return null;
        }
        //If they have the same x, the only way there will be no infinite amount of intersections is if one line
        //starts exactly where the other line ends.
        if (ThresholdComparison.isEqual(this.start().getY(), other.end().getY())) {
            return new Point(this.start().getX(), this.start().getY());
        }

        if (ThresholdComparison.isEqual(this.end().getY(), other.start().getY())) {
            return new Point(this.start().getX(), this.end().getY());
        }
        return null;
    }

    /**
     * Returns the intersection of a non-vertical line with a vertical line.
     *
     * @param verticalLine The vertical line.
     * @return true or false.
     */
    public Point oneVerticalIntersection(Line verticalLine) {
        // Makeing sure the line being checked is vertical
        if (!ThresholdComparison.isEqual(verticalLine.start().getX(), verticalLine.end().getX())) {
            return null;
        }

        double slope = (this.end().getY() - this.start().getY()) / (this.end().getX() - this.start().getX());
        double intercept = this.start().getY() - (slope * this.start().getX());

        double vx = verticalLine.start().getX();
        double yAtX = (slope * vx) + intercept;

        // Check if the vertical line's X-coordinate is within the non-vertical line's X range
        double xMin = Math.min(this.start().getX(), this.end().getX());
        double xMax = Math.max(this.start().getX(), this.end().getX());
        if (vx < xMin || vx > xMax) {
            return null;
        }

        double smallestV = Math.min(verticalLine.start().getY(), verticalLine.end().getY());
        double biggestV = Math.max(verticalLine.start().getY(), verticalLine.end().getY());
        if (yAtX < smallestV || yAtX > biggestV) {
            return null;
        }
        return new Point(vx, yAtX);
    }


    /**
     * Returns the intersection of 2 vertical lines.
     *
     * @param other The other vertical line.
     * @return The point of intersection if exists, if not returns false
     */
    public Point twoNonVerticals(Line other) {
        double slope1 = ((this.end().getY() - this.start().getY()) / (this.end().getX() - this.start().getX()));
        double intercept1 = this.start().getY() - slope1 * this.start().getX();
        double slope2 = ((other.end().getY() - other.start().getY()) / (other.end().getX() - other.start().getX()));
        double intercept2 = other.start().getY() - slope2 * other.start().getX();
        //Case for parallel lines.
        if (ThresholdComparison.isEqual(slope1, slope2)) {
            return null;
        }
        double intersectionX = (intercept2 - intercept1) / (slope1 - slope2);
        //The method will check if the intersection between the 2 lines is within both lines limits.
        double biggerSmallerX = Math.max(Math.min(this.start().getX(), this.end().getX()),
                Math.min(other.start().getX(), other.end().getX()));
        double smallerLargerX = Math.min(Math.max(this.start().getX(), this.end().getX()),
                Math.max(other.start().getX(), other.end().getX()));
        if (ThresholdComparison.aMoreOrEqualB(intersectionX, biggerSmallerX)
                && ThresholdComparison.aLessOrEqualB(intersectionX, smallerLargerX)) {
            return new Point(intersectionX, ((slope1 * intersectionX) + intercept1));
        }
        return null;
    }

    /**
     * Returns the intersection point if the lines intersect,and null otherwise.
     * @param other The other line.
     * @return true or false.
     */
    public Point intersectionWith(Line other) {
        if (this.equals(other)) {
            return null;
        }
        if (this.isVertical && other.isVertical()) {
            return this.bothVerticalsIntersection(other);
        }
        if (!this.isVertical && other.isVertical()) {
            return this.oneVerticalIntersection(other);
        }
        if (this.isVertical) {
            return other.oneVerticalIntersection(this);
        }
        return this.twoNonVerticals(other);
    }
    /**
     * Returns true if the lines intersect, false otherwise.
     *
     * @param other The other line.
     * @return true or false.
     */
    public boolean isIntersecting(Line other) {
        return (this.intersectionWith(other) != null);
    }

    /**
     * Returns true if this 2 lines intersect with this line, false otherwise.
     * @param other1 The first other line.
     * @param other2 The second other line.
     * @return true or false.
     */
    public boolean isIntersecting(Line other1, Line other2) {
        return this.isIntersecting(other1) && this.isIntersecting(other2) && other1.isIntersecting(other2);
    }
    /**
     * Checking if the point is a dot on the line.
     * @param p point.
     * @return true or false.
     */
    public boolean isIntersectingWithPoint(Point p) {
        double pX = p.getX();
        double pY = p.getY();
        double minX = Math.min(this.start.getX(), this.end.getX());
        double maxX = Math.max(this.start.getX(), this.end.getX());
        double minY = Math.min(this.start.getY(), this.end.getY());
        double maxY = Math.max(this.start.getY(), this.end.getY());
        return (ThresholdComparison.bLessThanAAndSmallerThanC(minX, pX, maxX)
                && ThresholdComparison.bLessThanAAndSmallerThanC(minY, pY, maxY));
    }

    /**
     * A method that forces the line to consider itself vertical.
     */
    public void forceVertical() {
        this.isVertical = true;
    }
    /**
     * A method that forces the line to consider itself non - vertical.
     */
    public void forceNonVertical() {
        this.isVertical = false;
    }
}