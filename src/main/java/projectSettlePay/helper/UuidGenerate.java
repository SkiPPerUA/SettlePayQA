package projectSettlePay.helper;

import java.util.UUID;

public interface UuidGenerate {

    static String generateUUID(){
        return UUID.randomUUID().toString();
    }
}
