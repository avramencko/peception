package sample.drawing;

import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.util.Callback;

import java.time.LocalDate;

public class CalendarFactory {
    public Callback<DatePicker, DateCell> getDayCellFactory(LocalDate beforeDate) {
        return getDayCellFactory(beforeDate, beforeDate.plusMonths(3));
    }
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

