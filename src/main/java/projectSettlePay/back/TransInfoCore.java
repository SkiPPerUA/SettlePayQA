package projectSettlePay.back;

import org.apache.log4j.Logger;
import projectSettlePay.core.DataBase;

import java.sql.ResultSet;

import static projectSettlePay.core.DataBase.DataBaseName.APIPAY_STAGE_1;

public class TransInfoCore {

    private static Logger logger = Logger.getLogger(TransInfoCore.class);
    private String currency;
    private int status;
    private float amount;

    public TransInfoCore(long id){
        DataBase dataBase = new DataBase(APIPAY_STAGE_1);
        try {
            ResultSet result = dataBase.selectSql("SELECT x.* FROM public.transactions x WHERE id = '"+id+"'");
            result.next();
            currency = result.getString("amount_currency");
            status = result.getInt("status");
            amount = result.getFloat("amount");
        }catch (Throwable e){
            logger.error(e);
        }finally {
            dataBase.closeConn();
        }
    }

    public String getCurrency() {
        return currency;
    }

    public int getStatus() {
        return status;
    }

    public float getAmount() {
        return amount;
    }

}
