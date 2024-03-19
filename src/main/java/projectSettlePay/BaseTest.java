package projectSettlePay;

import org.apache.log4j.Logger;
import org.testng.annotations.BeforeMethod;
import projectSettlePay.core.DataBase;
import projectSettlePay.helper.TestCases;
import projectSettlePay.helper.TransInfoHelper;

import java.lang.reflect.Method;

public abstract class BaseTest implements DataBase.DataBaseName, TestCases, TransInfoHelper {

    protected static Logger logger = Logger.getLogger("Test");

    private DataBase dataBase;

    @BeforeMethod
    public void startMethod(Method method){
        logger.info("======================     "+method.getName()+"     ======================");
    }

    protected static void logTestCase(String testCase){
        logger.info("Test case - "+testCase);
    }

    protected DataBase getDataBase(String name){
        if (dataBase == null){
            dataBase = new DataBase(name);
        } else if (dataBase != null && !dataBase.getName().equals(name)) {
            dataBase.closeConn();
            dataBase = new DataBase(name);
        }
        return dataBase;
    }

    protected void closeCon(){
        if (dataBase != null) {
            dataBase.closeConn();
        };
    }

    protected String getAgoraURL(String trans_id){
        return "https://preprod-agora2.backofficeweb.info/transactions/transactions/"+getChildId(trans_id);
    }

    protected void showAgoraURL(String trans_id){
        System.out.println(Thread.currentThread().getStackTrace()[2].getMethodName()+" --> "+getAgoraURL(trans_id));
    }
}
