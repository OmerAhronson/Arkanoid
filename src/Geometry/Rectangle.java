// 322824806 Omer Ahronson
package Geometry;
import java.util.ArrayList;
import java.util.List;
/**
 * A class for Geometry.Rectangle.
 */
public class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;
    private Line[] rectanglesLines = new Line[4];
    /**
     * Constructor.
     * @param upperLeft upper left point.
     * @param width width.
     * @param height height.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        double startingX = upperLeft.getX();
        double startingY = upperLeft.getY();
        double endingX = startingX + width;
        double endingY = startingY + height;
        //Upper side.
        this.rectanglesLines[0] = new Line(startingX, startingY, endingX, startingY);
        //Bottom side,
        this.rectanglesLines[1] = new Line(startingX, endingY, endingX, endingY);
        //Left side.
        this.rectanglesLines[2] = new Line(startingX, startingY, startingX, endingY);
        //Right side.
        this.rectanglesLines[3] = new Line(endingX, startingY, endingX, endingY);
    }

    /**
     * Getter for width.
     * @return width.
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Getter for height.
     * @return height.
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * getter for the upper left point.
     * @return upper left point.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }
    /**
     *  Return a (possibly empty) List of intersection points with the specified line.
     * @param line The line.
     * @return List of points.
     */
    public List<Point> intersectionPoints(Line line) {
        List<Point> points = new ArrayList<>(List.of());
        for (int i = 0; i < 4; i++) {
            if (line.isIntersecting(this.rectanglesLines[i])) {
                points.add(line.intersectionWith(this.rectanglesLines[i]));
            }
        }
        return points;
    }

    /**
     * Returns the array of the rectangle's lines.
     * @return the lines.
     */
    public Line[] getRectanglesLines() {
        return this.rectanglesLines;
    }

    /**
     * Returns the intersection point of the trajectory with all possible collidables.
     * @param trajectory the trajectory.
     * @return true or false.
     */
    public Point getIntersection(Line trajectory) {
        Point closestPoint = null; // Start with no intersection
        double x = trajectory.start().getX();
        double y = trajectory.start().getY();
        for (int i = 0; i < 4; i++) {
            if (trajectory.isIntersecting(this.getRectanglesLines()[i])) {
                Point intersectionPoint = trajectory.intersectionWith(this.getRectanglesLines()[i]);
                if (closestPoint == null) {
                    closestPoint = intersectionPoint;
                }
                double intersectionX = intersectionPoint.getX();
                double intersectionY = intersectionPoint.getY();
                double minIntersectionX = closestPoint.getX();
                double minIntersectionY = closestPoint.getY();
                if (Math.abs(minIntersectionX - x) > Math.abs(intersectionX - x) && Math.abs(minIntersectionY - y)
                        > Math.abs(intersectionY - y)) {
                    closestPoint = intersectionPoint;
                }
            }
        }
        return closestPoint;
    }
}