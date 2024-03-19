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
    int protocol_id = 161;
    String provider_services$name = "Payport_PayOut_HPP";
    String schema = "{\n" +
            "    \"schema\": [\n" +
            "        {\n" +
            "            \"label\": \"base_url\",\n" +
            "            \"name\": \"base_url\",\n" +
            "            \"required\": true,\n" +
            "            \"type\": \"text\",\n" +
            "            \"value\": \"\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"label\": \"merchant_api_key\",\n" +
            "            \"name\": \"merchant_api_key\",\n" +
            "            \"required\": true,\n" +
            "            \"type\": \"text\",\n" +
            "            \"value\": \"\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"label\": \"exact_currency\",\n" +
            "            \"name\": \"exact_currency\",\n" +
            "            \"required\": false,\n" +
            "            \"type\": \"boolean\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"label\": \"currency2currency\",\n" +
            "            \"name\": \"currency2currency\",\n" +
            "            \"required\": true,\n" +
            "            \"type\": \"boolean\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"label\": \"merchant_expense\",\n" +
            "            \"name\": \"merchant_expense\",\n" +
            "            \"required\": false,\n" +
            "            \"type\": \"int\"\n" +
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


    int provider_credential_id = 1378;
    String protocol_versions$path = "protocols.ProviderConnector.v1.protocol_acquiring";
    ILogicServices servicesProtocol = new AquairingLogic();
    int providers_id = 5556;
    String provider_services$currency = "BDT";
    String gateway_logic = "logics.services.wallet_topup_via_provider_form_h2h_lite.v1.logic";
    String stage = "6";
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
