package projectSettlePayTests.back;

import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import projectSettlePay.BaseTest;
import projectSettlePay.back.logics.AquairingLogic;
import projectSettlePay.back.logics.H2HLogic;
import projectSettlePay.back.logics.ILogicServices;
import projectSettlePay.back.Protocol;
import projectSettlePay.back.logics.P2PLogic;
import projectSettlePay.core.DataBase;
import projectSettlePay.helper.MD5hash;

import java.sql.SQLException;

@Test
public class CreateProtocolNew extends BaseTest {
    Protocol protocol = new Protocol();
    int protocol_id = 145;
    String provider_services$name = "Munzen_payout";
    String schema = "{\n" +
            "    \"schema\": [\n" +
            "        {\n" +
            "            \"label\": \"Base Url\",\n" +
            "            \"name\": \"base_url\",\n" +
            "            \"required\": true,\n" +
            "            \"type\": \"text\",\n" +
            "            \"placeholder\": \"https://...\",\n" +
            "            \"value\": \"\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"label\": \"Control key\",\n" +
            "            \"name\": \"control_key\",\n" +
            "            \"required\": true,\n" +
            "            \"type\": \"text\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"label\": \"Endpoint id\",\n" +
            "            \"name\": \"endpoint_id\",\n" +
            "            \"required\": true,\n" +
            "            \"type\": \"text\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"label\": \"Login\",\n" +
            "            \"name\": \"login\",\n" +
            "            \"required\": true,\n" +
            "            \"type\": \"text\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"label\": \"Private Key\",\n" +
            "            \"name\": \"private_key\",\n" +
            "            \"required\": true,\n" +
            "            \"type\": \"text\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"label\": \"Expired Minutes\",\n" +
            "            \"name\": \"expired_minutes\",\n" +
            "            \"required\": false,\n" +
            "            \"type\": \"int\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"label\": \"Expired Hours\",\n" +
            "            \"name\": \"expired_hours\",\n" +
            "            \"required\": false,\n" +
            "            \"type\": \"int\"\n" +
            "        }\n" +
            "    ]\n" +
            "}";


    String protocol_versions$path = "protocols.ProviderConnector.v1.protocol_payout";
    ILogicServices servicesProtocol = new H2HLogic();
    int providers_id = 5182;
    String provider_services$currency = "UZS";
    String gateway_logic = "logics.services.default_service.v1.logic";
    String points$name = "Точка стейдж 3";
    String accounts$name = "Аккаунт стейдж 3";
    boolean pay_in = false;

    public void makeConnector() throws SQLException {
        // добавить протокол ид из МД файла в public.providers x (конектор)
        protocol.connector_protocolId(protocol_id,provider_services$name);
    }
    // добавить креды в provider_credentials - получаю ид

    public void insert_schema() throws SQLException {
        protocol.add_schema(schema,provider_services$name);
    }

    public void update_connector_protocolId(){
        //обновляю запись в providers - provider_credentials_id (конектор)
        protocol.change_connector_protocolId(1208,protocol_id);
    }
    public void getProtocolID() throws SQLException {
        //создаем-связку протокола
        protocol.protocol_id(protocol_versions$path);
        makeConnector();
        System.out.println(String.format("Имя - %s%nProtocol_id - %s%nConnector_id - %s",protocol_versions$path.replace("protocols.","")+"_"+provider_services$name,protocol.getProtocol_versions$id(),protocol.getConnector_Id()));
    }

    //добавить запись в providers - сказать имя+protocol_id из protocol_versions + ссылку на стейдж Коннектора + id из providersКонектора = получаю providers ид

    public void makeProtocol() throws SQLException {

        protocol.provider_services(providers_id, provider_services$name, provider_services$currency, true);

        protocol.services(protocol_versions$path.replace("protocols.","")+"_"+provider_services$name,gateway_logic,servicesProtocol);

        protocol.wallet_services(protocol.getServices$id());

        protocol.points_id(points$name);

        System.out.println(MD5hash.getHash(protocol.getPoints$id()+protocol.getApi_token()+1));

        protocol.points_services(protocol.getPoints$id(), protocol.getServices$id());

        protocol.service_commission_lines(protocol.getServices$id());

        protocol.commission_line_elements(protocol.getService_commission_lines$ids(), pay_in);

        protocol.service_provider_services(protocol.getServices$id(), protocol.getProvider_services$id());

        protocol.account(accounts$name);

        protocol.wallets_account(protocol.getAccounts$id(),provider_services$currency,pay_in);
    }
    @AfterTest
    void closeConn(){
        protocol.getDataApiPay().closeConn();
        protocol.getDataConn().closeConn();
    }
}
