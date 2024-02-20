package projectSettlePay.helper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public interface Data {

    static String getCurrentTime(){
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")).toString();
    }

    static String getChangeDays(int days){
        if (days < 0) {
            return LocalDateTime.now().minusDays(-1 * days).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")).toString();
        }else {
            return LocalDateTime.now().plusDays(days).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")).toString();
        }
    }

    static String getChangeHours(int hours){
        if (hours < 0) {
            return LocalDateTime.now().minusHours(-1 * hours).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")).toString();
        }else {
            return LocalDateTime.now().plusHours(hours).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")).toString();
        }
    }

    static String getChangeMinutes(int minutes){
        if (minutes < 0) {
            return LocalDateTime.now().minusMinutes(-1 * minutes).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")).toString();
        }else {
            return LocalDateTime.now().plusMinutes(minutes).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")).toString();
        }
    }
}
