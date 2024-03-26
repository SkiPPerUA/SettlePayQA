package projectSettlePay.helper;

import org.apache.log4j.Logger;
import projectSettlePay.core.DataBase;

import java.sql.ResultSet;
import java.util.UUID;

import static projectSettlePay.core.DataBase.DataBaseName.APIPAY_STAGE_1;

public interface BodyHelper {

    Logger logger = Logger.getLogger(BodyHelper.class);

    static int find_wallet_id(int account_id, String currency){
        int id = 0;
        DataBase dataBase = new DataBase(APIPAY_STAGE_1);
        ResultSet result = dataBase.selectSql(String.format("SELECT id FROM public.account_wallets WHERE account_id = %s AND currency = '%s'",account_id,currency));
        try {
            result.next();
            id = result.getInt(1);
            logger.info("Wallet_id = "+id);
        } catch (Throwable e) {
            logger.error(e);
        }finally {
            dataBase.closeConn();
        }
        return id;
    }

    static String generateUUID(){
        return UUID.randomUUID().toString();
    }
}
