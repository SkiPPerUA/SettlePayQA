package projectSettlePay.helper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public interface Data {

    static String getCurrentTime(){
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")).toString();
    }

    static String getPlusDays(int days){
        return LocalDateTime.now().plusDays(days).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")).toString();
    }

    static String getMinusDays(int days){
        return LocalDateTime.now().minusDays(days).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")).toString();
    }
}
