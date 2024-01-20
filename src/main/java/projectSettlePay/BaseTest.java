package projectSettlePay;

import org.apache.log4j.Logger;
import org.testng.annotations.BeforeMethod;
import projectSettlePay.core.DataBase;
import java.lang.reflect.Method;

public abstract class BaseTest implements DataBase.DataBaseName{

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
}
