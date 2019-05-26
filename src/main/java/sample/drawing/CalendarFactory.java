package sample.drawing;

import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.util.Callback;

import java.time.LocalDate;
/**the factory that creates the calendar*/
public class CalendarFactory {
    /**
     * create a calendar in which you cannot select days before this date
     * @param beforeDate - days before this date cannot be selected
     * @return - calendar
     * @see CalendarFactory#getDayCellFactory(LocalDate)
     */
    public Callback<DatePicker, DateCell> getDayCellFactory(LocalDate beforeDate) {
        return getDayCellFactory(beforeDate, beforeDate.plusMonths(3));
    }

    /**
     * Create a calendar where you cannot select days before the first date and days after the second date
     * @param beforeDate - days before this date cannot be selected
     * @param afterDate - days after this date cannot be selected
     * @return - calendar
     * @see CalendarFactory#getDayCellFactory(LocalDate)
     */
    public Callback<DatePicker, DateCell> getDayCellFactory(LocalDate beforeDate, LocalDate afterDate) {
        return new Callback<DatePicker, DateCell>() {
            @Override
            public DateCell call(final DatePicker datePicker) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item.isBefore(beforeDate)||item.isAfter(afterDate)) {
                            setStyle("-fx-background-color: #8099ff;");
                            setDisable(true);
                        }
                    }
                };
            }
        };
    }
}

