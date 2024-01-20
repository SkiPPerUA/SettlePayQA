package projectSettlePayTests.back;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import projectSettlePay.BaseTest;
import projectSettlePay.core.DataBase;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

@Test
public class CreateProtocol extends BaseTest {

    String dataBase = "apipay_new_stage1";
    String protocol_versions$path = "protocols.ProviderConnector.v1.protocol_acquiringTEST";
    String provider_services$name = "TestVladTEST";
    String provider_services$currency = "UAH";
    int provider_id = 118;
    String points$name = "Точка стейдж 7";
    String accounts$name = "Аккаунт стейдж 7";

    public void protocol() throws SQLException {
        //Поиск protocol_id
        res = data.selectSql("SELECT x.protocol_id FROM public.protocol_versions x\n" +
                "WHERE \"path\" = '"+protocol_versions$path+"'");
        if (res.next()){
            protocol_versions$id = res.getInt(1);
            System.out.println("protocol_versions$id = "+protocol_versions$id);
        }else {
            data.updateSql("insert into public.protocol_versions(path)\n" +
                    "values ('"+protocol_versions$path+"')");
            res = data.selectSql("SELECT x.id FROM public.protocol_versions x\n" +
                    "WHERE \"path\" = '"+protocol_versions$path+"'");
            res.next();
            int protocol_versions$id = res.getInt(1);
            System.out.println("protocol_versions$id = "+protocol_versions$id);

            data.updateSql("insert into public.protocols(name,sandbox_version_id,production_version_id)\n" +
                    "values ('"+protocol_versions$path+"',"+protocol_versions$id+","+protocol_versions$id+")");
            res = data.selectSql("SELECT x.id FROM public.protocols x\n" +
                    "WHERE \"name\" = '"+protocol_versions$path+"'");
            res.next();
            int protocols$id = res.getInt(1);
            System.out.println("protocols$id = "+protocols$id);

            data.updateSql("update public.protocol_versions set protocol_id="+protocols$id+" where \"path\" = '"+protocol_versions$path+"'");
        }

        //Создание provider_services
        data.updateSql("insert into public.provider_services(provider_id, name, currency, currency_required, is_moto)\n" +
                "values ("+provider_id+", '"+provider_services$name+"', '"+provider_services$currency+"', true, false)");

        res = data.selectSql("SELECT id FROM public.provider_services WHERE name = '"+provider_services$name+"'");
        res.next();
        provider_services$id = res.getInt(1);
        System.out.println("provider_services$id = "+provider_services$id);

        //Создание services
        data.updateSql("insert into public.services(status, type, name, slug,sandbox_logic_id,production_logic_id,group_id,min_amount,max_amount,commission_group_id,commission_template_id,api_forms_slug,\n" +
                "confirm_timeout,currency_required,is_api_method_pay_allowed)\n" +
                "values (1,2,'"+provider_services$name+"','"+provider_services$name+"_slug',2,2,2,1,200000,3,1,'cards',1800,true,true)");
        res = data.selectSql("SELECT id FROM public.services WHERE name = '"+provider_services$name+"'");
        res.next();
        services$id = res.getInt(1);
        System.out.println("services$id = "+services$id);

        //Создание wallet_services
        data.updateSql("insert into public.account_wallet_type_services(type_id, service_id, status)\n" +
                "values (1,"+services$id+",1)");
        res = data.selectSql("SELECT id FROM public.account_wallet_type_services WHERE service_id = '"+services$id+"'");
        res.next();
        wallet_type_services$id = res.getInt(1);
        System.out.println("wallet_type_services$id = "+wallet_type_services$id);

        //Поиск points_id
        res = data.selectSql("SELECT id FROM public.points WHERE \"name\" = '"+ points$name +"'");
        if (res.next()){
            points$id = res.getInt(1);
            System.out.println("points$id = "+points$id);
        }else {
            System.out.println("Нужно создать");
            Assert.fail();
        }

        //Создани points_services
        data.updateSql("insert into public.point_services(point_id, service_id, status, use_scoring,retry_enable,transparency_token_required,use_antifraud,finmon_enabled)\n" +
                "values ("+points$id+","+services$id+",1,false,false,false,false,false)");
        res = data.selectSql("SELECT id FROM public.point_services WHERE service_id = '"+services$id+"'");
        res.next();
        point_services$id = res.getInt(1);
        System.out.println("point_services$id = "+point_services$id);

        //Создание service_commission_lines
        data.updateSql("insert into public.service_commission_lines(template_id,service_id,active)\n" +
                "values (1,"+services$id+",true);");
        data.updateSql("insert into public.service_commission_lines(template_id,service_id,active)\n" +
                "values (2,"+services$id+",true);");
        res = data.selectSql("SELECT id FROM public.service_commission_lines WHERE service_id = '"+services$id+"'");
        service_commission_lines$ids = new LinkedList<>();
        while (res.next()){
            service_commission_lines$ids.add(res.getInt(1));
            System.out.println("service_commission_lines$id = "+res.getInt(1));
        }

        //Создание commission_line_elements
        data.updateSql("insert into public.service_commission_line_elements(line_id,type_id,commission_percent,commission_amount,priority,base_type,result_rounding,result_rounding_scale)\n" +
                "values ("+service_commission_lines$ids.get(0)+",'calc_company_reward',0,0,2,'formula',0,4);");
        data.updateSql("insert into public.service_commission_line_elements(line_id,type_id,commission_percent,commission_amount,priority,base_type,result_type,result_rounding,result_rounding_scale)\n" +
                "values ("+service_commission_lines$ids.get(0)+",'buying_price',0,0,1,'customer_amount','final_amount',0,4);");
        data.updateSql("insert into public.service_commission_line_elements(line_id,type_id,commission_percent,commission_amount,priority,base_type,result_type,result_rounding,result_rounding_scale)\n" +
                "values ("+service_commission_lines$ids.get(1)+",'customer',0,0,2,'amount','final_amount',1,2);");
        data.updateSql("insert into public.service_commission_line_elements(line_id,type_id,commission_percent,commission_amount,priority,base_type,result_type,result_rounding,result_rounding_scale,wallet_from)\n" +
                "values ("+service_commission_lines$ids.get(1)+",'point',0,0,1,'amount','final_amount',0,4,3);");
        data.updateSql("insert into public.service_commission_line_elements(line_id,type_id,commission_percent,commission_amount,priority,base_type,result_type,result_rounding,result_rounding_scale)\n" +
                "values ("+service_commission_lines$ids.get(1)+",'provider',0,0,3,'amount','final_amount',1,2);");

        //Создание provider_services
        data.updateSql("insert into public.service_provider_services(service_id,provider_service_id,status,priority)\n" +
                "values ("+services$id+","+provider_services$id+",1,1);");

        //Создание account
        res = data.selectSql("SELECT id FROM public.accounts WHERE \"name\" = '"+accounts$name+"'");
        if (res.next()){
            accounts$id = res.getInt(1);
            System.out.println("accounts$id = "+accounts$id);
        }else {
            System.out.println("Нужно создать");
            Assert.fail();
        }

        //Создание wallets_account
        data.updateSql("insert into public.account_wallets(account_id,type_id,status,currency,balance,balance_hold,balance_min,name,rolling_reserve_amount)\n" +
                "values ("+accounts$id+",1,1,'"+provider_services$currency+"',2000000,0,0,'"+provider_services$name+"',0);");
    }
    @BeforeTest
    void openBD(){
        data = new DataBase(dataBase);
    }

    @AfterTest
    void closeConn(){
        data.closeConn();
    }

    int protocol_versions$id;
    int provider_services$id;
    int services$id;
    int wallet_type_services$id;
    int points$id;
    int point_services$id;
    int accounts$id;
    List<Integer> service_commission_lines$ids;
    DataBase data;
    ResultSet res;

//    SELECT x.* FROM public.protocol_versions x
//    WHERE "path" = 'protocols.ProviderConnector.v1.protocol_acquiring'
//
//    insert into public.provider_services(provider_id, name, currency, currency_required, is_moto)
//    values (118, 'TestVlad', 'UAH', true, false)
//    SELECT * FROM public.provider_services WHERE name = 'TestVlad'
//
//    insert into public.services(status, type, name, slug,sandbox_logic_id,production_logic_id,group_id,min_amount,max_amount,commission_group_id,commission_template_id,api_forms_slug,
//                                confirm_timeout,currency_required,is_api_method_pay_allowed)
//    values (1,2,'TestVlad','TestVlad_slug',2,2,2,1,200000,3,1,'cards',1800,true,true)
//    SELECT * FROM public.services WHERE name = 'TestVlad'
//
//    insert into public.account_wallet_type_services(type_id, service_id, status)
//    values (1,4109,1)
//    SELECT * FROM public.account_wallet_type_services WHERE service_id = '4109'
//
//    SELECT x.* FROM public.points x WHERE "name" = 'Точка стейдж 7' --2759
//
//    insert into public.point_services(point_id, service_id, status, use_scoring,retry_enable,transparency_token_required,use_antifraud,finmon_enabled)
//    values (2759,4109,1,false,false,false,false,false)
//    SELECT * FROM public.point_services WHERE service_id = '4109'
//
//    insert into public.service_commission_lines(template_id,service_id,active)
//    values (1,4109,true);
//    insert into public.service_commission_lines(template_id,service_id,active)
//    values (2,4109,true);
//    SELECT * FROM public.service_commission_lines where service_id = 4109
//
//    insert into public.service_commission_line_elements(line_id,type_id,commission_percent,commission_amount,priority,base_type,result_rounding,result_rounding_scale)
//    values (4921,'calc_company_reward',0,0,2,'formula',0,4);
//    insert into public.service_commission_line_elements(line_id,type_id,commission_percent,commission_amount,priority,base_type,result_type,result_rounding,result_rounding_scale)
//    values (4921,'buying_price',0,0,1,'customer_amount','final_amount',0,4);
//    insert into public.service_commission_line_elements(line_id,type_id,commission_percent,commission_amount,priority,base_type,result_type,result_rounding,result_rounding_scale)
//    values (4922,'customer',0,0,2,'amount','final_amount',1,2);
//    insert into public.service_commission_line_elements(line_id,type_id,commission_percent,commission_amount,priority,base_type,result_type,result_rounding,result_rounding_scale,wallet_from)
//    values (4922,'point',0,0,1,'amount','final_amount',0,4,3);
//    insert into public.service_commission_line_elements(line_id,type_id,commission_percent,commission_amount,priority,base_type,result_type,result_rounding,result_rounding_scale)
//    values (4922,'provider',0,0,3,'amount','final_amount',1,2);
//    SELECT * FROM public.service_commission_line_elements where line_id in (2,13,4921,4922)
//
//
//    insert into public.service_provider_services(service_id,provider_service_id,status,priority)
//    values (4109,5296,1,1);
//    SELECT * from public.service_provider_services where service_id = 4109
//
//    insert into public.account_wallets(account_id,type_id,status,currency,balance,balance_hold,balance_min,name,rolling_reserve_amount)
//    values ((SELECT id FROM public.accounts WHERE "name" = 'Аккаунт стейдж 7'),1,1,'UAH',2000000,0,0,'VladTEst',0);
//    SELECT * from public.account_wallets where "name" = 'TestVlad123'
}
