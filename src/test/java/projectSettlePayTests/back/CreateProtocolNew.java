package projectSettlePayTests.back;

import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import projectSettlePay.back.ILogicServices;
import projectSettlePay.back.P2PLogic;
import projectSettlePay.back.Protocol;
import projectSettlePay.helper.MD5hash;

import java.sql.SQLException;

@Test
public class CreateProtocolNew {
    Protocol protocol = new Protocol("apipay_new_stage1");
    int protocol_id = 118;
    String name_protocolId = "NameProtocol";


    String protocol_versions$path = "protocols.ProviderConnector.v1.protocol_acquiring";
    ILogicServices servicesProtocol = new P2PLogic();
    int providers_id = 5164;
    String provider_services$name = "TestVladTEST123";
    String provider_services$currency = "UAH";
    String gateway_logic = "logics.services.wallet_topup_via_p2p_form.v1.logic";
    String points$name = "Точка стейдж 7";
    String accounts$name = "Аккаунт стейдж 7";
    boolean forMerchant = false;

    public void makeConnector() throws SQLException {
        // добавить протокол ид из МД файла в public.providers x (конектор)
        protocol.connector_protocolId(protocol_id,name_protocolId);
    }

    // добавить креды в provider_credentials - получаю ид

    public void update_connector_protocolId(){
        //обновляю запись в providers - provider_credentials_id (конектор)
        protocol.change_connector_protocolId(1034,protocol_id);
    }
    public void getProtocolID() throws SQLException {
        //создаем-связку протокола
        protocol.protocol_id(protocol_versions$path);
        makeConnector();
        System.out.println(String.format("Имя - %s%nProtocol_id - %s%nConnector_id - %s",protocol_versions$path.replace("protocols.",""),protocol.getProtocol_versions$id(),protocol.getConnector_Id()));
    }

    //добавить запись в providers - сказать имя+protocol_id из protocol_versions + ссылку на стейдж Коннектора + id из providersКонектора = получаю providers ид

    public void makeApiPay() throws SQLException {

        protocol.provider_services(providers_id, provider_services$name, provider_services$currency, true);

        protocol.services(protocol_versions$path.replace("protocols.",""),gateway_logic,servicesProtocol);

        protocol.wallet_services(protocol.getServices$id());

        protocol.points_id(points$name);

        System.out.println(MD5hash.getHash(protocol.getPoints$id()+protocol.getApi_token()+1));

        protocol.points_services(protocol.getPoints$id(), protocol.getServices$id());

        protocol.service_commission_lines(protocol.getServices$id());

        protocol.commission_line_elements(protocol.getService_commission_lines$ids());

        protocol.service_provider_services(protocol.getServices$id(), protocol.getProvider_services$id());

        protocol.account(accounts$name);

        protocol.wallets_account(protocol.getAccounts$id(),provider_services$currency,forMerchant);
    }
    @AfterTest
    void closeConn(){
        protocol.getDataApiPay().closeConn();
        protocol.getDataConn().closeConn();
    }
}