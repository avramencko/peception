package sample.drawing;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Сreates rectangles that correspond to orders on the time line
 */
public class OrderRectangle {
    private final int DAY_OF_WEEK = 7;
    private final int DAY_OF_ROW = 14;
    private final double D = 2d;
    private final Color orange = Color.rgb(255, 165, 0, 0.7);


    private Rectangle getRect(int begin, int days, double width, double height, Color color, double x, double y) {
        Rectangle rect = new Rectangle(0, 0, width * days, height);
        rect.setArcHeight(10);
        rect.setArcWidth(10);
        rect.setFill(color);
        rect.setLayoutX(width * begin+x);
        rect.setLayoutY(y);
        return rect;
    }

    private Rectangle getRect2(int begin, int days, double width, double height, Color color, double x, double y) {
        Rectangle rect = new Rectangle(0, 0, width * days - 2 * D, height - 2 * D);
        rect.setArcHeight(10);
        rect.setArcWidth(10);
        rect.setFill(color);
        rect.setLayoutX(width * begin + D);
        rect.setLayoutY(y + D);
        return rect;
    }

    /**
     * create rectangles that correspond to orders on the time line
     * @param begin - the position of the first day of the order relative to the start of the time line
     * @param days - number of days of stay
     * @param w - the width of the desired rectangle
     * @param h - the height of the desired rectangle
     * @return the rectangle with the specified parameters
     */
    public Rectangle getRectangleForTimeline(int begin, int days, double w, double h) {
        int invisibleDays = 0;
        if (begin < 0) {
            invisibleDays = Math.abs(begin);
            begin = 0;
        }
        if ((begin + days - invisibleDays) > DAY_OF_ROW) {
            days = DAY_OF_ROW - begin;
            invisibleDays = 0;
        }
        if (days > 0)
//        return getRect(begin, days - invisibleDays, w, h, orange, (16d + w / 2), 0);
            return getRect(begin, days - invisibleDays, w, h, orange, 0, 4);
        else return new Rectangle();

    }

    /**
     * Create rectangles that correspond to orders on the calendar
     * @param begin - position of the first day of the order relative to the beginning of the week in the calendar
     * @param days - number of days of stay
     * @param w - the width of the desired rectangle
     * @param h - the height of the desired rectangle
     * @param j - the week number in the calendar
     * @return the rectangle with the specified parameters
     * @see OrderRectangle#getRectangleForCalendar(int, int, double, double, int, Color)
     */
    public Rectangle getRectangleForCalendar(int begin, int days, double w, double h, int j) {
        return getRectangleForCalendar(begin, days, w, h, j, orange);
    }

    /**
     * Create rectangles that correspond to orders on the calendar
     * @param begin position of the first day of the order relative to the beginning of the week in the calendar
     * @param days number of days of stay
     * @param w the width of the desired rectangle
     * @param h the height of the desired rectangle
     * @param j the week number in the calendar
     * @param color the color of the rectangle
     * @return the rectangle with the specified parameters
     * @see OrderRectangle#getRectangleForCalendar(int, int, double, double, int)
     */
    public Rectangle getRectangleForCalendar(int begin, int days, double w, double h, int j, Color color) {
        int invisibleDays = 0;
        if (begin < 0) {
            invisibleDays = Math.abs(begin);
            begin = 0;
        }
        if ((begin + days - invisibleDays) > DAY_OF_WEEK) {
            days = DAY_OF_WEEK - begin;
            invisibleDays = 0;
        }

        return getRect2(begin, days - invisibleDays, w, h, color, 0, 10d + j * h);
    }
}
