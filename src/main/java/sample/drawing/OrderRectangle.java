package sample.drawing;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class OrderRectangle {
    private final int DAY_OF_WEEK = 7;
    private final int DAY_OF_ROW = 14;
//    private final double LENGTH_DAY_DOUBLE = 35;
//    private final double WIDTH_DAY_DOUBLE = 60d;
//    private final double HEIGHT_DAY_DOUBLE = 30d;
    private final double D = 2d;
    private final Color orange = Color.rgb(255, 165, 0, 0.7);
//private final Color orange = Color.rgb(74, 101, 114, 0.7);
//    private final Color blue = Color.rgb(100, 100, 255, 0.7);


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

    public Rectangle getRectangleForCalendar(int begin, int days, double w, double h, int j) {
        return getRectangleForCalendar(begin, days, w, h, j, orange);
    }

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
