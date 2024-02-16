package projectSettlePay.back;

import org.testng.Assert;
import projectSettlePay.back.logics.ILogicServices;
import projectSettlePay.core.DataBase;
import projectSettlePay.helper.Data;
import org.apache.log4j.Logger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class Protocol {

    Logger logger = Logger.getLogger(Protocol.class);
    private DataBase dataApiPay;
    private DataBase dataConn;
    private int protocol_versions$id;
    private int provider_services$id;
    private int services$id;
    private int wallet_type_services$id;
    private int points$id;
    private int point_services$id;
    private int accounts$id;
    private List<Integer> service_commission_lines$ids;
    private ResultSet res;
    private int connector_Id;
    private int sandbox_logic_id;
    private int method_type = 0;
    private String api_token = "";

    public Protocol(String bd_name){
        dataApiPay = new DataBase(bd_name);
        dataConn = new DataBase("provider_connector_service_stage");
    }

    public void protocol_id(String protocol_versions$path) throws SQLException {
        res = dataApiPay.selectSql("SELECT x.protocol_id FROM public.protocol_versions x\n" +
                "WHERE \"path\" = '"+protocol_versions$path+"' order by protocol_id ASC");
        if (res.next()){
            protocol_versions$id = res.getInt(1);
            logger.info("protocol_versions$id = "+protocol_versions$id);
        }else {
            dataApiPay.updateSql("insert into public.protocol_versions(created_at, updated_at, path)\n" +
                    "values ('"+ Data.getCurrentTime() +"','"+ Data.getCurrentTime()+"','"+protocol_versions$path+"')");
            res = dataApiPay.selectSql("SELECT x.id FROM public.protocol_versions x\n" +
                    "WHERE \"path\" = '"+protocol_versions$path+"'");
            res.next();
            int protocol_versions$id = res.getInt(1);
            logger.info("protocol_versions$id = "+protocol_versions$id);

            dataApiPay.updateSql("insert into public.protocols(name,sandbox_version_id,production_version_id)\n" +
                    "values ('"+protocol_versions$path+"',"+protocol_versions$id+","+protocol_versions$id+")");
            res = dataApiPay.selectSql("SELECT x.id FROM public.protocols x\n" +
                    "WHERE \"name\" = '"+protocol_versions$path+"'");
            res.next();
            int protocols$id = res.getInt(1);
            logger.info("protocols$id = "+protocols$id);

            dataApiPay.updateSql("update public.protocol_versions set protocol_id="+protocols$id+" where \"path\" = '"+protocol_versions$path+"'");
        }
    }

    public void provider_services(int provider_id, String provider_services$name, String provider_services$currency, boolean currency_required) throws SQLException {
        dataApiPay.updateSql("insert into public.provider_services(created_at, updated_at, provider_id, name, currency, currency_required, is_moto)\n" +
                "values ('"+ Data.getCurrentTime() +"','"+ Data.getCurrentTime()+"',"+provider_id+", '"+provider_services$name+"', '"+provider_services$currency+"', "+currency_required+", false)");

        res = dataApiPay.selectSql("SELECT id FROM public.provider_services WHERE name = '"+provider_services$name+"'");
        res.next();
        provider_services$id = res.getInt(1);
        logger.info("provider_services$id = "+provider_services$id);
    }

    public void services(String provider_services$name, String gateway_logic, ILogicServices services) throws SQLException {
        find_sandbox_logic_id(gateway_logic);
        if (!services.toString().contains("AquairingLogic") || !services.toString().contains("PayoutLogic")) {
            find_method_type(services.getMethod());
        }
        String sql = services.getSQL(provider_services$name);
        insertServices(provider_services$name,String.format(sql,sandbox_logic_id,sandbox_logic_id,method_type));
    }

    private void insertServices(String provider_services$name, String sql) throws SQLException {
        dataApiPay.updateSql(sql);
        res = dataApiPay.selectSql("SELECT id FROM public.services WHERE name = '"+provider_services$name+"' order by id desc");
        res.next();
        services$id = res.getInt(1);
        System.out.println("Services_id = "+services$id);
    }

    private void find_method_type(String method) throws SQLException {
        res = dataApiPay.selectSql("SELECT x.id FROM public.payment_method_types x where slug = '"+method+"'");
        if (res.next()){
            method_type = res.getInt(1);
            logger.info("method_type "+method_type);
        }else {
            Assert.fail(method + " не найден");
        }

    }
    private void find_sandbox_logic_id(String gateway_logic) throws SQLException {
        res = dataApiPay.selectSql("SELECT x.id FROM public.service_logics x WHERE \"path\" = '"+gateway_logic+"'");
        if (res.next()) {
            sandbox_logic_id = res.getInt(1);
            logger.info("sandbox_logic_id "+sandbox_logic_id);
        }else {
            dataApiPay.updateSql("insert into public.service_logics(created_at, updated_at,name,path) values ('"+ Data.getCurrentTime() +"','"+ Data.getCurrentTime()+"',"+gateway_logic+","+gateway_logic+")");
            find_sandbox_logic_id(gateway_logic);
        }
    }

    public void wallet_services(int services$id) throws SQLException {
        dataApiPay.updateSql("insert into public.account_wallet_type_services(type_id, service_id, status)\n" +
                "values (1,"+services$id+",1)");
    }

    public void points_id(String points$name) throws SQLException {
        res = dataApiPay.selectSql("SELECT * FROM public.points WHERE \"name\" = '"+ points$name +"'");
        if (res.next()){
            points$id = res.getInt("id");
            api_token = res.getString("api_token");
            System.out.println("Point = "+points$id);
        }else {
            Assert.fail(points$name + " не найден");
        }
    }

    public void points_services(int points$id, int services$id) throws SQLException {
        dataApiPay.updateSql("insert into public.point_services(point_id, service_id, status, use_scoring,retry_enable,transparency_token_required,use_antifraud,finmon_enabled)\n" +
                "values ("+points$id+","+services$id+",1,false,false,false,false,false)");
    }

    public void service_commission_lines(int services$id) throws SQLException {
        dataApiPay.updateSql("insert into public.service_commission_lines(template_id,service_id,active)\n" +
                "values (1,"+services$id+",true);");
        dataApiPay.updateSql("insert into public.service_commission_lines(template_id,service_id,active)\n" +
                "values (2,"+services$id+",true);");
        res = dataApiPay.selectSql("SELECT id FROM public.service_commission_lines WHERE service_id = '"+services$id+"'");
        service_commission_lines$ids = new LinkedList<>();
        while (res.next()){
            service_commission_lines$ids.add(res.getInt(1));
            logger.info("service_commission_lines$id = "+res.getInt(1));
        }
    }

    public void commission_line_elements(List<Integer> service_commission_lines$ids, boolean pay_in){
        dataApiPay.updateSql("insert into public.service_commission_line_elements(line_id,type_id,commission_percent,commission_amount,priority,base_type,result_rounding,result_rounding_scale)\n" +
                "values ("+service_commission_lines$ids.get(0)+",'calc_company_reward',0,0,2,'formula',0,4);");
        dataApiPay.updateSql("insert into public.service_commission_line_elements(line_id,type_id,commission_percent,commission_amount,priority,base_type,result_type,result_rounding,result_rounding_scale)\n" +
                "values ("+service_commission_lines$ids.get(0)+",'buying_price',0,0,1,'customer_amount','final_amount',0,4);");
        dataApiPay.updateSql("insert into public.service_commission_line_elements(line_id,type_id,commission_percent,commission_amount,priority,base_type,result_type,result_rounding,result_rounding_scale)\n" +
                "values ("+service_commission_lines$ids.get(1)+",'customer',0,0,2,'amount','final_amount',1,2);");
            if (pay_in) {
                dataApiPay.updateSql("insert into public.service_commission_line_elements(line_id,type_id,commission_percent,commission_amount,priority,base_type,result_type,result_rounding,result_rounding_scale,wallet_from)\n" +
                        "values (" + service_commission_lines$ids.get(1) + ",'point',0,0,1,'amount','final_amount',0,4,3);");
            }else {
                dataApiPay.updateSql("insert into public.service_commission_line_elements(line_id,type_id,commission_percent,commission_amount,priority,base_type,result_type,result_rounding,result_rounding_scale,wallet_to)\n" +
                        "values (" + service_commission_lines$ids.get(1) + ",'point',0,0,1,'amount','final_amount',0,4,3);");
            }
        dataApiPay.updateSql("insert into public.service_commission_line_elements(line_id,type_id,commission_percent,commission_amount,priority,base_type,result_type,result_rounding,result_rounding_scale)\n" +
                "values ("+service_commission_lines$ids.get(1)+",'provider',0,0,3,'amount','final_amount',1,2);");
    }

    public void service_provider_services(int services$id, int provider_services$id){
        dataApiPay.updateSql("insert into public.service_provider_services(service_id,provider_service_id,status,priority)\n" +
                "values ("+services$id+","+provider_services$id+",1,1);");
    }

    public void account(String accounts$name) throws SQLException {
        res = dataApiPay.selectSql("SELECT id FROM public.accounts WHERE \"name\" = '"+accounts$name+"'");
        if (res.next()){
            accounts$id = res.getInt(1);
            System.out.println("Account_id = "+accounts$id);
        }else {
            System.out.println("Нужно создать");
            Assert.fail();
        }
    }

    public void wallets_account(int accounts$id, String provider_services$currency, boolean forMerchant) throws SQLException {
        String request = "";
        if (forMerchant){
            request = "SELECT x.id FROM public.account_wallets x WHERE account_id = "+accounts$id+" and \"name\" like '%"+provider_services$currency+" for%'";
        }else {
            request = "SELECT x.id FROM public.account_wallets x WHERE account_id = "+accounts$id+" and \"name\" like '%"+provider_services$currency+"'";
        }
        res = dataApiPay.selectSql(request);
        if (res.next()){
            int account_wallets$id = res.getInt(1);
            System.out.println("Wallets_id = "+account_wallets$id);
        }else {
            Assert.fail(request + " нет записи");
        }
    }

    public void connector_protocolId(int protocol_id, String name) throws SQLException {
        res = dataConn.selectSql("SELECT id FROM public.providers where protocol_id  = '"+protocol_id+"'");
        if (res.next()){
            connector_Id = res.getInt(1);
            logger.info("connector_Id = "+connector_Id);
        }else {
            dataConn.updateSql("insert into public.providers(created_at, updated_at, is_active, protocol_id, gateway_id, name)\n" +
                    "values ('"+ Data.getCurrentTime() +"','"+ Data.getCurrentTime()+"',true,"+protocol_id+","+protocol_id+",'"+name+"')");
            connector_protocolId(protocol_id,name);
        }
    }

    public void change_connector_protocolId(int provider_credentials_id, int protocol_id){
        dataConn.updateSql("update public.providers set provider_credentials_id = "+provider_credentials_id+" where protocol_id  = '"+protocol_id+"'");
    }

    public DataBase getDataApiPay() {
        return dataApiPay;
    }

    public int getProtocol_versions$id() {
        return protocol_versions$id;
    }

    public int getProvider_services$id() {
        return provider_services$id;
    }

    public int getServices$id() {
        return services$id;
    }

    public int getPoints$id() {
        return points$id;
    }

    public int getAccounts$id() {
        return accounts$id;
    }

    public List<Integer> getService_commission_lines$ids() {
        return service_commission_lines$ids;
    }

    public DataBase getDataConn() {
        return dataConn;
    }

    public int getConnector_Id() {
        return connector_Id;
    }

    public String getApi_token() {
        return api_token;
    }
}
