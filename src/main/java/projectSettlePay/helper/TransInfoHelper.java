package projectSettlePay.helper;

import org.apache.log4j.Logger;
import projectSettlePay.core.DataBase;

import java.sql.ResultSet;

public interface TransInfoHelper {

    Logger logger = Logger.getLogger(TransInfoHelper.class);

    default String getChildId(String id){
        DataBase dataBase = null;
        String child_id = "";
        try{
            dataBase = new DataBase(DataBase.DataBaseName.APIPAY_STAGE_1);
            ResultSet res = dataBase.selectSql("SELECT id FROM public.transactions where parent_id = "+id);
            res.next();
            child_id = String.valueOf(res.getLong(1));
        }catch (Throwable e){
            logger.error(e);
        }finally {
            dataBase.closeConn();
        }
        return child_id;
    }
}
