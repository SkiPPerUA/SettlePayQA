package projectSettlePayTests.back.providersTests.GetaPay;

import org.testng.annotations.Test;
import projectSettlePay.BaseTest;
import projectSettlePay.back.providers.GetaPay;
import projectSettlePay.core.DataBase;
import projectSettlePay.helper.BodyHelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Test
public class GetaPayPayOut extends BaseTest {

    GetaPay getaPay;
    String body;
    public void successPayOut() {
        try {
            getaPay = new GetaPay(GetaPay.GetaPayBody.defaultBody(false));
            getaPay.pay_out();
            getaPay.callback("payout_completed");
        }finally {
            showAgoraURL(getaPay.getId());
        }
    }

    public void negativePayOut() {
        getaPay = new GetaPay(GetaPay.GetaPayBody.defaultBody(false));
        getaPay.pay_out();
        getaPay.callback("payout_failed");
        showAgoraURL(getaPay.getId());
    }

    public void tests_summ(){
        List<Long> amounts = amount_cases(new Long[]{49999l,10000001l,50000l,10000000l,50001l});
        for (Long x: amounts){
            body = String.format("{\n" +
                    "    \"auth\": {\n" +
                    "        \"debug\": true,\n" +
                    "        \"point\": 2759,\n" +
                    "        \"key\": 1,\n" +
                    "        \"hash\": \"e5a095b16b8a2106380686b23c5570ac\"\n" +
                    "    },\n" +
                    "    \"locale\": \"en\",\n" +
                    "    \"external_order_id\": \"954345387\",\n" +
                    "    \"external_customer_id\": \"5d8aac94-7784-45cf-a212-84bae39600ed\",\n" +
                    "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                    "    \"account_id\": 2765,\n" +
                    "    \"wallet_id\": 3234,\n" +
                    "    \"service_id\": 4461,\n" +
                    "    \"amount\": %s,\n" +
                    "    \"amount_currency\": \"INR\",\n" +
                    "\t  \"fields\": {\n" +
                    "        \"AccountNo\": \"398602010875308\",\n" +
                    "        \"Remark\": \"description\",\n" +
                    "        \"AccHolderName\": \"Ayush Tyagi\",\n" +
                    "        \"BeneficiaryMobile\": \"+917042088572\",\n" +
                    "        \"BeneficiaryEmail\": \"AyushTyagi89@gmail.com\",\n" +
                    "        \"first_name\": \"Ayush\",\n" +
                    "        \"last_name\": \"Tyagi\",\n" +
                    "        \"IFSC\": \"UBIN0539864\"\n" +
                    "    } \n" +
                    "}",x);
            try {
                System.out.print(String.format("Amount {%s} ",x));
                getaPay = new GetaPay(body);
                getaPay.pay_out();
                getaPay.callback("payout_completed");
            }finally {
                showAgoraURL(getaPay.getId());
            }
        }
    }

    public void negative_currency(){
        DataBase dataBase = null;
        try {
            dataBase = new DataBase(APIPAY_STAGE_1);
            dataBase.updateSql("UPDATE public.provider_services set currency = 'UAH' where \"name\" = 'Getapay_pay_out'");
            getaPay = new GetaPay("{\n" +
                    "    \"auth\": {\n" +
                    "        \"debug\": true,\n" +
                    "        \"point\": 2759,\n" +
                    "        \"key\": 1,\n" +
                    "        \"hash\": \"e5a095b16b8a2106380686b23c5570ac\"\n" +
                    "    },\n" +
                    "    \"locale\": \"en\",\n" +
                    "    \"external_order_id\": \"954345387\",\n" +
                    "    \"external_customer_id\": \"5d8aac94-7784-45cf-a212-84bae39600ed\",\n" +
                    "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                    "    \"account_id\": 2765,\n" +
                    "    \"wallet_id\": "+BodyHelper.find_wallet_id(2765, "UAH")+",\n" +
                    "    \"service_id\": 4461,\n" +
                    "    \"amount\": 50000,\n" +
                    "    \"amount_currency\": \"UAH\",\n" +
                    "\t  \"fields\": {\n" +
                    "        \"AccountNo\": \"398602010875308\",\n" +
                    "        \"Remark\": \"description\",\n" +
                    "        \"AccHolderName\": \"Ayush Tyagi\",\n" +
                    "        \"BeneficiaryMobile\": \"+917042088572\",\n" +
                    "        \"BeneficiaryEmail\": \"AyushTyagi89@gmail.com\",\n" +
                    "        \"first_name\": \"Ayush\",\n" +
                    "        \"last_name\": \"Tyagi\",\n" +
                    "        \"IFSC\": \"UBIN0539864\"\n" +
                    "    } \n" +
                    "}");
            getaPay.pay_out();
            getaPay.callback("payout_completed");
        }finally {
            showAgoraURL(getaPay.getId());
            dataBase.updateSql("UPDATE public.provider_services set currency = 'INR' where \"name\" = 'Getapay_pay_out'");
            dataBase.closeConn();
        }
    }

    public void test_without_fields(){
        Map<String, String> fields = new HashMap<>();
        fields.put("AccountNo","398602010875308");
        fields.put("Remark","description");
        fields.put("AccHolderName","user_name");
        fields.put("BeneficiaryMobile","+911111112222");
        fields.put("BeneficiaryEmail","userEmail@das.das");
        fields.put("first_name","first_name");
        fields.put("last_name","last_name");
        fields.put("IFSC","UBIN0539864");
        List<String> cases = without_fields_cases(fields);
        for (String x: cases){
            body = String.format("{\n" +
                    "    \"auth\": {\n" +
                    "        \"debug\": true,\n" +
                    "        \"point\": 2759,\n" +
                    "        \"key\": 1,\n" +
                    "        \"hash\": \"e5a095b16b8a2106380686b23c5570ac\"\n" +
                    "    },\n" +
                    "    \"locale\": \"en\",\n" +
                    "    \"external_order_id\": \"954345387\",\n" +
                    "    \"external_customer_id\": \"5d8aac94-7784-45cf-a212-84bae39600ed\",\n" +
                    "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                    "    \"account_id\": 2765,\n" +
                    "    \"wallet_id\": 3234,\n" +
                    "    \"service_id\": 4461,\n" +
                    "    \"amount\": 50000,\n" +
                    "    \"amount_currency\": \"INR\",\n" +
                    "\t  \"fields\": %s "+
                    "}",x);
            System.out.print(String.format("Fields %s ",x));
            try {
                getaPay = new GetaPay(body);
                getaPay.pay_out();
            }finally {
                showAgoraURL(getaPay.getId());
            }
        }
    }

    @Test(dataProvider = "string_cases")
    public void test_field_AccountNo(String data){
        try {
            getaPay = new GetaPay("{\n" +
                    "    \"auth\": {\n" +
                    "        \"debug\": true,\n" +
                    "        \"point\": 2759,\n" +
                    "        \"key\": 1,\n" +
                    "        \"hash\": \"e5a095b16b8a2106380686b23c5570ac\"\n" +
                    "    },\n" +
                    "    \"locale\": \"en\",\n" +
                    "    \"external_order_id\": \"954345387\",\n" +
                    "    \"external_customer_id\": \"5d8aac94-7784-45cf-a212-84bae39600ed\",\n" +
                    "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                    "    \"account_id\": 2765,\n" +
                    "    \"wallet_id\": 3234,\n" +
                    "    \"service_id\": 4461,\n" +
                    "    \"amount\": 50000,\n" +
                    "    \"amount_currency\": \"INR\",\n" +
                    "\t  \"fields\": {\n" +
                    "        \"AccountNo\": "+data+",\n" +
                    "        \"Remark\": \"description\",\n" +
                    "        \"AccHolderName\": \"Ayush Tyagi\",\n" +
                    "        \"BeneficiaryMobile\": \"+917042088572\",\n" +
                    "        \"BeneficiaryEmail\": \"AyushTyagi89@gmail.com\",\n" +
                    "        \"first_name\": \"Ayush\",\n" +
                    "        \"last_name\": \"Tyagi\",\n" +
                    "        \"IFSC\": \"UBIN0539864\"\n" +
                    "    } \n" +
                    "}");
            getaPay.pay_out();
            getaPay.callback("payout_completed");
        }finally {
            showAgoraURL(getaPay.getId());
        }
    }

    @Test(dataProvider = "string_cases")
    public void test_field_Remark(String data){
        try {
            getaPay = new GetaPay("{\n" +
                    "    \"auth\": {\n" +
                    "        \"debug\": true,\n" +
                    "        \"point\": 2759,\n" +
                    "        \"key\": 1,\n" +
                    "        \"hash\": \"e5a095b16b8a2106380686b23c5570ac\"\n" +
                    "    },\n" +
                    "    \"locale\": \"en\",\n" +
                    "    \"external_order_id\": \"954345387\",\n" +
                    "    \"external_customer_id\": \"5d8aac94-7784-45cf-a212-84bae39600ed\",\n" +
                    "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                    "    \"account_id\": 2765,\n" +
                    "    \"wallet_id\": 3234,\n" +
                    "    \"service_id\": 4461,\n" +
                    "    \"amount\": 50000,\n" +
                    "    \"amount_currency\": \"INR\",\n" +
                    "\t  \"fields\": {\n" +
                    "        \"AccountNo\": \"398602010875308\",\n" +
                    "        \"Remark\": "+data+",\n" +
                    "        \"AccHolderName\": \"Ayush Tyagi\",\n" +
                    "        \"BeneficiaryMobile\": \"+917042088572\",\n" +
                    "        \"BeneficiaryEmail\": \"AyushTyagi89@gmail.com\",\n" +
                    "        \"first_name\": \"Ayush\",\n" +
                    "        \"last_name\": \"Tyagi\",\n" +
                    "        \"IFSC\": \"UBIN0539864\"\n" +
                    "    } \n" +
                    "}");
            getaPay.pay_out();
            getaPay.callback("payout_completed");
        }finally {
            showAgoraURL(getaPay.getId());
        }
    }

    @Test(dataProvider = "string_cases")
    public void test_field_BeneficiaryMobile(String data){
        try {
            getaPay = new GetaPay("{\n" +
                    "    \"auth\": {\n" +
                    "        \"debug\": true,\n" +
                    "        \"point\": 2759,\n" +
                    "        \"key\": 1,\n" +
                    "        \"hash\": \"e5a095b16b8a2106380686b23c5570ac\"\n" +
                    "    },\n" +
                    "    \"locale\": \"en\",\n" +
                    "    \"external_order_id\": \"954345387\",\n" +
                    "    \"external_customer_id\": \"5d8aac94-7784-45cf-a212-84bae39600ed\",\n" +
                    "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                    "    \"account_id\": 2765,\n" +
                    "    \"wallet_id\": 3234,\n" +
                    "    \"service_id\": 4461,\n" +
                    "    \"amount\": 50000,\n" +
                    "    \"amount_currency\": \"INR\",\n" +
                    "\t  \"fields\": {\n" +
                    "        \"AccountNo\": \"398602010875308\",\n" +
                    "        \"Remark\": \"description\",\n" +
                    "        \"AccHolderName\": \"Ayush Tyagi\",\n" +
                    "        \"BeneficiaryMobile\": "+data+",\n" +
                    "        \"BeneficiaryEmail\": \"AyushTyagi89@gmail.com\",\n" +
                    "        \"first_name\": \"Ayush\",\n" +
                    "        \"last_name\": \"Tyagi\",\n" +
                    "        \"IFSC\": \"UBIN0539864\"\n" +
                    "    } \n" +
                    "}");
            getaPay.pay_out();
            getaPay.callback("payout_completed");
        }finally {
            showAgoraURL(getaPay.getId());
        }
    }

    @Test(dataProvider = "emailNegative_cases")
    public void test_field_BeneficiaryEmail(String data){
        try {
            getaPay = new GetaPay("{\n" +
                    "    \"auth\": {\n" +
                    "        \"debug\": true,\n" +
                    "        \"point\": 2759,\n" +
                    "        \"key\": 1,\n" +
                    "        \"hash\": \"e5a095b16b8a2106380686b23c5570ac\"\n" +
                    "    },\n" +
                    "    \"locale\": \"en\",\n" +
                    "    \"external_order_id\": \"954345387\",\n" +
                    "    \"external_customer_id\": \"5d8aac94-7784-45cf-a212-84bae39600ed\",\n" +
                    "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                    "    \"account_id\": 2765,\n" +
                    "    \"wallet_id\": 3234,\n" +
                    "    \"service_id\": 4461,\n" +
                    "    \"amount\": 50000,\n" +
                    "    \"amount_currency\": \"INR\",\n" +
                    "\t  \"fields\": {\n" +
                    "        \"AccountNo\": \"398602010875308\",\n" +
                    "        \"Remark\": \"description\",\n" +
                    "        \"AccHolderName\": \"Ayush Tyagi\",\n" +
                    "        \"BeneficiaryMobile\": \"+917042088572\",\n" +
                    "        \"BeneficiaryEmail\": "+data+",\n" +
                    "        \"first_name\": \"Ayush\",\n" +
                    "        \"last_name\": \"Tyagi\",\n" +
                    "        \"IFSC\": \"UBIN0539864\"\n" +
                    "    } \n" +
                    "}");
            getaPay.pay_out();
            getaPay.callback("payout_completed");
        }finally {
            showAgoraURL(getaPay.getId());
        }
    }

    @Test(dataProvider = "string_cases")
    public void test_field_first_name(String data){
        try {
            getaPay = new GetaPay("{\n" +
                    "    \"auth\": {\n" +
                    "        \"debug\": true,\n" +
                    "        \"point\": 2759,\n" +
                    "        \"key\": 1,\n" +
                    "        \"hash\": \"e5a095b16b8a2106380686b23c5570ac\"\n" +
                    "    },\n" +
                    "    \"locale\": \"en\",\n" +
                    "    \"external_order_id\": \"954345387\",\n" +
                    "    \"external_customer_id\": \"5d8aac94-7784-45cf-a212-84bae39600ed\",\n" +
                    "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                    "    \"account_id\": 2765,\n" +
                    "    \"wallet_id\": 3234,\n" +
                    "    \"service_id\": 4461,\n" +
                    "    \"amount\": 50000,\n" +
                    "    \"amount_currency\": \"INR\",\n" +
                    "\t  \"fields\": {\n" +
                    "        \"AccountNo\": \"398602010875308\",\n" +
                    "        \"Remark\": \"description\",\n" +
                    "        \"AccHolderName\": \"Ayush Tyagi\",\n" +
                    "        \"BeneficiaryMobile\": \"+917042088572\",\n" +
                    "        \"BeneficiaryEmail\": \"AyushTyagi89@gmail.com\"\n" +
                    "        \"first_name\": "+data+",\n" +
                    "        \"last_name\": \"Tyagi\",\n" +
                    "        \"IFSC\": \"UBIN0539864\"\n" +
                    "    } \n" +
                    "}");
            getaPay.pay_out();
            getaPay.callback("payout_completed");
        }finally {
            showAgoraURL(getaPay.getId());
        }
    }

    @Test(dataProvider = "string_cases")
    public void test_field_last_name(String data){
        try {
            getaPay = new GetaPay("{\n" +
                    "    \"auth\": {\n" +
                    "        \"debug\": true,\n" +
                    "        \"point\": 2759,\n" +
                    "        \"key\": 1,\n" +
                    "        \"hash\": \"e5a095b16b8a2106380686b23c5570ac\"\n" +
                    "    },\n" +
                    "    \"locale\": \"en\",\n" +
                    "    \"external_order_id\": \"954345387\",\n" +
                    "    \"external_customer_id\": \"5d8aac94-7784-45cf-a212-84bae39600ed\",\n" +
                    "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                    "    \"account_id\": 2765,\n" +
                    "    \"wallet_id\": 3234,\n" +
                    "    \"service_id\": 4461,\n" +
                    "    \"amount\": 50000,\n" +
                    "    \"amount_currency\": \"INR\",\n" +
                    "\t  \"fields\": {\n" +
                    "        \"AccountNo\": \"398602010875308\",\n" +
                    "        \"Remark\": \"description\",\n" +
                    "        \"AccHolderName\": \"Ayush Tyagi\",\n" +
                    "        \"BeneficiaryMobile\": \"+917042088572\",\n" +
                    "        \"BeneficiaryEmail\": \"AyushTyagi89@gmail.com\"\n" +
                    "        \"first_name\": \"Ayush\",\n" +
                    "        \"last_name\": "+data+",\n" +
                    "        \"IFSC\": \"UBIN0539864\"\n" +
                    "    } \n" +
                    "}");
            getaPay.pay_out();
            getaPay.callback("payout_completed");
        }finally {
            showAgoraURL(getaPay.getId());
        }
    }

    @Test(dataProvider = "string_cases")
    public void test_field_IFSC(String data){
        try {
            getaPay = new GetaPay("{\n" +
                    "    \"auth\": {\n" +
                    "        \"debug\": true,\n" +
                    "        \"point\": 2759,\n" +
                    "        \"key\": 1,\n" +
                    "        \"hash\": \"e5a095b16b8a2106380686b23c5570ac\"\n" +
                    "    },\n" +
                    "    \"locale\": \"en\",\n" +
                    "    \"external_order_id\": \"954345387\",\n" +
                    "    \"external_customer_id\": \"5d8aac94-7784-45cf-a212-84bae39600ed\",\n" +
                    "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                    "    \"account_id\": 2765,\n" +
                    "    \"wallet_id\": 3234,\n" +
                    "    \"service_id\": 4461,\n" +
                    "    \"amount\": 50000,\n" +
                    "    \"amount_currency\": \"INR\",\n" +
                    "\t  \"fields\": {\n" +
                    "        \"AccountNo\": \"398602010875308\",\n" +
                    "        \"Remark\": \"description\",\n" +
                    "        \"AccHolderName\": \"Ayush Tyagi\",\n" +
                    "        \"BeneficiaryMobile\": \"+917042088572\",\n" +
                    "        \"BeneficiaryEmail\": \"AyushTyagi89@gmail.com\"\n" +
                    "        \"first_name\": \"Ayush\",\n" +
                    "        \"last_name\": \"Tyagi\",\n" +
                    "        \"IFSC\": "+data+"\n" +
                    "    } \n" +
                    "}");
            getaPay.pay_out();
            getaPay.callback("payout_completed");
        }finally {
            showAgoraURL(getaPay.getId());
        }
    }

    @Test(dataProvider = "string_cases")
    public void test_field_description(String data){
        try {
            getaPay = new GetaPay("{\n" +
                    "    \"auth\": {\n" +
                    "        \"debug\": true,\n" +
                    "        \"point\": 2759,\n" +
                    "        \"key\": 1,\n" +
                    "        \"hash\": \"e5a095b16b8a2106380686b23c5570ac\"\n" +
                    "    },\n" +
                    "    \"locale\": \"en\",\n" +
                    "    \"external_order_id\": \"954345387\",\n" +
                    "    \"external_customer_id\": \"5d8aac94-7784-45cf-a212-84bae39600ed\",\n" +
                    "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                    "    \"account_id\": 2765,\n" +
                    "    \"wallet_id\": 3234,\n" +
                    "    \"service_id\": 4461,\n" +
                    "    \"amount\": 50000,\n" +
                    "    \"description\": "+data+",\n" +
                    "    \"amount_currency\": \"INR\",\n" +
                    "\t  \"fields\": {\n" +
                    "        \"AccountNo\": \"398602010875308\",\n" +
                    "        \"Remark\": \"description\",\n" +
                    "        \"AccHolderName\": \"Ayush Tyagi\",\n" +
                    "        \"BeneficiaryMobile\": \"+917042088572\",\n" +
                    "        \"BeneficiaryEmail\": \"AyushTyagi89@gmail.com\"\n" +
                    "        \"first_name\": \"Ayush\",\n" +
                    "        \"last_name\": \"Tyagi\",\n" +
                    "        \"IFSC\": \"UBIN0539864\"\n" +
                    "    } \n" +
                    "}");
            getaPay.pay_out();
            getaPay.callback("payout_completed");
        }finally {
            showAgoraURL(getaPay.getId());
        }
    }
}
