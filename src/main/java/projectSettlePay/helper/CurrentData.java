package projectSettlePay.helper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public interface CurrentData {

    static String get(){
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")).toString();
    }
}
