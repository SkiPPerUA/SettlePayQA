package projectSettlePayTests.back;

import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import projectSettlePay.BaseTest;
import projectSettlePay.back.logics.AquairingLogic;
import projectSettlePay.back.logics.H2HLogic;
import projectSettlePay.back.logics.ILogicServices;
import projectSettlePay.back.Protocol;
import projectSettlePay.helper.MD5hash;

import java.sql.SQLException;

@Test
public class CreateProtocolNew extends BaseTest {
    Protocol protocol = new Protocol();
    int protocol_id = 160;
    String provider_services$name = "Getapay_pay_out";
    String schema = "{\n" +
            "    \"schema\": [\n" +
            "        {\n" +
            "            \"label\": \"Base Url\",\n" +
            "            \"name\": \"base_url\",\n" +
            "            \"required\": true,\n" +
            "            \"type\": \"text\",\n" +
            "            \"placeholder\": \"https://...\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"label\": \"Project Id\",\n" +
            "            \"name\": \"project_id\",\n" +
            "            \"required\": true,\n" +
            "            \"type\": \"text\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"label\": \"Secret Key\",\n" +
            "            \"name\": \"secret_key\",\n" +
            "            \"required\": true,\n" +
            "            \"type\": \"text\"\n" +
            "        },\n" +
            "         {\n" +
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


    int provider_credential_id = 1411;
    String protocol_versions$path = "protocols.ProviderConnector.v1.protocol_payout";
    ILogicServices servicesProtocol = new H2HLogic();
    int providers_id = 5561;
    String provider_services$currency = "INR";
    String gateway_logic = "logics.services.default_service.v1.logic";
    String stage = "7";
    boolean pay_in = false;

    public void makeConnector() throws SQLException {
        // добавить протокол ид и схему из МД файла в public.providers x (конектор)
        protocol.connector_protocolId(protocol_id,provider_services$name);
        protocol.add_schema(schema,provider_services$name);
        //создаем-связку протокола
        protocol.protocol_id(protocol_versions$path);
        System.out.println(String.format("Имя - %s%nProtocol_id - %s%nConnector_id - %s",protocol_versions$path.replace("protocols.","")+"_"+provider_services$name,protocol.getProtocol_versions$id(),protocol.getConnector_Id()));
        //добавить запись в providers - сказать имя+protocol_id из protocol_versions + ссылку на стейдж Коннектора + id из providersКонектора = получаю providers ид

    }

    public void makeProtocol() throws SQLException {
        //обновляю запись в providers - provider_credentials_id (конектор)
        protocol.change_connector_protocolId(provider_credential_id,protocol_id);

        protocol.provider_services(providers_id, provider_services$name, provider_services$currency, true);

        protocol.services(protocol_versions$path.replace("protocols.","")+"_"+provider_services$name,gateway_logic,servicesProtocol);

        protocol.wallet_services(protocol.getServices$id());

        protocol.points_id(stage);

        System.out.println(MD5hash.getHash(protocol.getPoints$id()+protocol.getApi_token()+1));

        protocol.points_services(protocol.getPoints$id(), protocol.getServices$id());

        protocol.service_commission_lines(protocol.getServices$id());

        protocol.commission_line_elements(protocol.getService_commission_lines$ids(), pay_in);

        protocol.service_provider_services(protocol.getServices$id(), protocol.getProvider_services$id());

        protocol.account(stage);

        protocol.wallets_account(protocol.getAccounts$id(),provider_services$currency,pay_in);
    }
    @AfterTest
    void closeConn(){
        protocol.getDataApiPay().closeConn();
        protocol.getDataConn().closeConn();
    }
}
