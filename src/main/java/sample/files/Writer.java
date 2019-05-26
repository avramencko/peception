package sample.files;

import sample.models.Order;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.time.Duration;
import java.time.format.DateTimeFormatter;

/**
 * class for writing the HTML file information about the order
 */
public class Writer {
    /**Оrder field*/
    private Order order;
    private final String HEAD = "<head>\n" +
            "<meta charset=\"utf-8\">\n" +
            "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
            "<title>Catalog</title>\n" +
            "</head>\n";

    private final String HTML = "<html>\n";
    private final String HTML_END = "</html>\n";
    private final String BODY = "<body style=\"font: 12pt sans-serif\">\n";
    private final String BODY_END = "</body>\n";

    private final String TABLE = "<table border=\"0\" cellpadding=\"5\">";
    private final String TABLE_END = "</table>\n";
    private final String CAPTION = "<caption>\n";
    private final String CAPTION_END = "</caption>\n";
    private final String TBODY = "<tbody>\n";
    private final String TBODY_END = "</tbody>\n";
    private final String TR = "<tr>\n";
    private final String TR_END = "</tr>\n";
    private final String TD = "<td>\n";
    private final String TD_END = "</td>\n";

    /**Сonstructor*/
    public Writer() {}

    /**Set the value of the order property*/
    public void setOrder(Order order) {
        this.order = order;
    }

    /**
     * Save the file with the order details
     * @param path - path where want to write the file
     * @param filename - file name
     */
    public void save(String path, String filename){
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(path + "/" + filename + ".html", "UTF-8");
            String strHtml = HTML + HEAD + BODY + TABLE + CAPTION + "Данные о казазе" +CAPTION_END + TBODY;
            strHtml+= TR + TD + "Номер комнаты" + TD_END + TD + String.valueOf(order.getRoom().getNumber()) + TD_END + TR_END;
            strHtml+= TR + TD + "Имя гостя" + TD_END + TD + order.getGuest().getName()+""+order.getGuest().getSurname() + TD_END + TR_END;
            strHtml+= TR + TD + "Номер телефона" + TD_END + TD + order.getGuest().getPhone() + TD_END + TR_END;
            strHtml+= TR + TD + "Оформил(а)" + TD_END + TD + order.getEmployee().getName()+ " "+order.getEmployee().getSurname() + TD_END + TR_END;
            strHtml+= TR + TD + "Дата заезда" + TD_END + TD + order.getArrival().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) + TD_END + TR_END;
            strHtml+= TR + TD + "Дата выселения" + TD_END + TD + order.getEviction().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) + TD_END + TR_END;
            int days = (int) Duration.between(order.getArrival().atStartOfDay(), order.getEviction().atStartOfDay()).toDays();
            strHtml+= TR + TD + "Стоимость" + TD_END + TD + String.valueOf(order.getRoom().getPrice()*days) + TD_END + TR_END;

            strHtml += TBODY_END + TABLE_END + BODY_END + HTML_END;
            writer.println(strHtml);
            writer.close();
            System.out.println(strHtml);
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

}
