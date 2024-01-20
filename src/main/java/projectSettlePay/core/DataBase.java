package projectSettlePay.core;

import java.sql.*;
import org.apache.log4j.Logger;
public class DataBase {

    private static final Logger logger = Logger.getLogger(DataBase.class);
    Connection connection = null;
    Statement st = null;
    String name = "";
    public DataBase(String name) {
        this.name = name;
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            connection = DriverManager.getConnection(Config.DB_URL+name, Config.USER, Config.PASSWORD);
        } catch (SQLException e) {
            logger.error("Connection Failed - "+e);
        }
    }

    public String getName() {
        return name;
    }

    public ResultSet selectSql(String sql){
        try {
            return getStatement().executeQuery(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateSql(String sql){
        try {
            getStatement().executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Statement getStatement(){
        if(st == null) {
            try {
                return connection.createStatement();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }else {
            return st;
        }
    }

    public void closeConn(){
        try {
            connection.close();
        } catch (SQLException e) {
            logger.error("Connection not closed - "+e);
        }
    }

    public interface DataBaseName{
        String CONN_STAGE_1 = "apipay_new_stage1";
    }
}
