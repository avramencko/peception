package sample.drawing;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Class for creating a timeline
 */
public class Timeline {
    /**
     * Creates a timeline
     * @param beginPeriod the beginning of the timeline
     * @param endPeriod the end of the timeline
     * @return Pane with timeline
     */
    public Pane getTimeLine1(LocalDate beginPeriod, LocalDate endPeriod) {
        Pane pane = new Pane();
        pane.setPrefWidth(448);
        double w = 32;
        double h = 8;
        Line line = new Line(0,24,w*14,24);
        line.setStroke(Color.rgb(100,100,100));
        pane.getChildren().add(line);
        ArrayList dates = getListDate(beginPeriod,endPeriod);
        for(int i = 0;i<14;i++){
            Line l = new Line(w/2+i*w,24,w/2+i*w,24+h);
            l.setStroke(Color.rgb(100,100,100));
            pane.getChildren().add(l);
            Label lb = new Label(String.valueOf(dates.get(i)));
            lb.setLayoutY(36);
            lb.setLayoutX(i*w+4);
            lb.setFont(Font.font(10));
            pane.getChildren().add(lb);
        }
        pane.setLayoutY(-10);
        return pane;
    }
    private ArrayList getListDate(LocalDate beginPeriod, LocalDate endPeriod) {
        ArrayList<String> list = new ArrayList<>();
        LocalDate current = beginPeriod;
        while (current.isBefore(endPeriod/*.plusDays(1)*/)) {
            String dayOfMonth = (current.getDayOfMonth() < 10) ? ("0" + current.getDayOfMonth()) : (current.getDayOfMonth() + "");
            String month = (current.getMonthValue() < 10) ? ("0" + current.getMonthValue()) : (current.getMonthValue() + "");
            list.add(dayOfMonth + "." + month);
            current = current.plusDays(1);
        }
        return list;
    }
}
