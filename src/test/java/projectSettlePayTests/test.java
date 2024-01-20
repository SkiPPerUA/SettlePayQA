package projectSettlePayTests;

import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import io.restassured.http.ContentType;
import org.checkerframework.checker.units.qual.A;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import projectSettlePay.BaseTest;
import projectSettlePay.back.*;
import projectSettlePay.core.*;
import projectSettlePay.helper.MD5hash;
import projectSettlePay.helper.UuidGenerate;

import java.sql.*;
import java.time.Duration;
import java.util.Optional;
import java.util.UUID;
import java.util.function.BinaryOperator;

import static io.restassured.RestAssured.given;

@Test
public class test extends BaseTest {

long [] num = {110100000469280l,110100000469382l,
        110100000469304l,110100000469246l,110100000469268l,110100000469358l,110100000469342l,110100000469392l,110100000469384l,
        110100000469400l,110100000469428l,110100000469418l,110100000469436l,110100000469450l,
};

int expectedStatus = 2;
    public void testLog(){
        for (long nunm: num){
            long check = nunm+1;
            System.out.print(check);
            String st = given()
                    .contentType(ContentType.JSON)
                    .header("Authorization","Bearer 1eVS1ABi6IpMZxDLvz884b0rBab51X")
                    .when()
                    .get("https://preprod-agora2.backofficeweb.info/v0/transactions/"+check+"/log/?limit=50&offset=0")
                    .then().extract().response().asString();
            try {
                Assert.assertTrue(st.contains("Response without bank_id or amount should be without minimum units."));
                System.out.println();
            }catch (Throwable e){
                System.out.println(" - НЕ успех");
            }
        }
    }

    public void testStatus() throws SQLException {
        System.out.println();
        for (long number: num){
            long check = number+1;
            System.out.print(check);
            ResultSet res = getDataBase(CONN_STAGE_1).selectSql(String.format(SQLrequests.getTransaction,check));
            res.next();
            int actStatus = res.getInt("status");
            try {
                Assert.assertTrue(actStatus == expectedStatus);
                System.out.println();
            }catch (Throwable e){
                System.out.println(" - НЕ успех, актуальный статус = "+actStatus);
            }

        }
    }

}
