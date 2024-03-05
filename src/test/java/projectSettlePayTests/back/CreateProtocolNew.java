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
    int protocol_id = 50;
    String provider_services$name = "Trio_PIX_payout";
    String schema = "{\n" +
            "    \"provider_slug\": \"trio_pay_in\",\n" +
            "    \"schema\": [\n" +
            "        {\n" +
            "            \"label\": \"Client Secret\",\n" +
            "            \"name\": \"client_secret\",\n" +
            "            \"required\": true,\n" +
            "            \"type\": \"string\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"label\": \"Base Url\",\n" +
            "            \"name\": \"base_url\",\n" +
            "            \"required\": true,\n" +
            "            \"type\": \"string\",\n" +
            "            \"value\": \"https://sandbox.trio.com.br/\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"label\": \"Client ID\",\n" +
            "            \"name\": \"client_id\",\n" +
            "            \"required\": true,\n" +
            "            \"type\": \"uuid\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"label\": \"Pix Key ID\",\n" +
            "            \"name\": \"pix_key_id\",\n" +
            "            \"required\": true,\n" +
            "            \"type\": \"uuid\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"label\": \"Virtual Account ID\",\n" +
            "            \"name\": \"virtual_account_id\",\n" +
            "            \"required\": true,\n" +
            "            \"type\": \"uuid\"\n" +
            "        }\n" +
            "    ]\n" +
            "}";


    String protocol_versions$path = "protocols.ProviderConnector.v1.protocol_payout";
    ILogicServices servicesProtocol = new AquairingLogic();
    int providers_id = 5185;
    String provider_services$currency = "BRL";
    String gateway_logic = "logics.services.default_service.v1.logic";
    String points$name = "Точка стейдж 1";
    String accounts$name = "Аккаунт стейдж 1";
    boolean pay_in = false;

    public void makeConnector() throws SQLException {
        // добавить протокол ид из МД файла в public.providers x (конектор)
        protocol.connector_protocolId(protocol_id,provider_services$name);
        protocol.add_schema(schema,provider_services$name);
    }
    // добавить креды в provider_credentials - получаю ид

    public void update_connector_protocolId(){
        //обновляю запись в providers - provider_credentials_id (конектор)
        protocol.change_connector_protocolId(103,protocol_id);
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
