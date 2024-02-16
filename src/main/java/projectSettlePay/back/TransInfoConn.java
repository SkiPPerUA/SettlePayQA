package projectSettlePay.back;

import org.apache.log4j.Logger;
import projectSettlePay.core.DataBase;

import java.sql.ResultSet;

import static projectSettlePay.core.DataBase.DataBaseName.CONN_STAGE_1;

public class TransInfoConn {

    private static Logger logger = Logger.getLogger(TransInfoConn.class);
    private String provider_status_code;
    private String currency;
    private String provider_data;
    private String provider_transaction_id;
    private int status;
    private int amount;
    private String transaction_uuid;

    public TransInfoConn(long id){
        DataBase dataBase = new DataBase(CONN_STAGE_1);
        try {
            ResultSet result = dataBase.selectSql("SELECT x.* FROM public.transactions x WHERE external_transaction_id = '"+id+"'");
            result.next();
            provider_status_code = result.getString("provider_status_code");
            currency = result.getString("currency");
            provider_data = result.getString("provider_data");
            provider_transaction_id = result.getString("provider_transaction_id");
            status = result.getInt("status");
            amount = result.getInt("amount");
            transaction_uuid = result.getString("transaction_uuid");
        }catch (Throwable e){
            logger.error(e);
        }finally {
            dataBase.closeConn();
        }
    }

    public String getProvider_status_code() {
        return provider_status_code;
    }

    public String getCurrency() {
        return currency;
    }

    public String getProvider_data() {
        return provider_data;
    }

    public String getProvider_transaction_id() {
        return provider_transaction_id;
    }

    public int getStatus() {
        return status;
    }

    public int getAmount() {
        return amount;
    }

    public String getTransaction_uuid() {
        return transaction_uuid;
    }
}
