package projectSettlePay.helper;

import org.testng.annotations.DataProvider;

public interface DataProviders {

    @DataProvider
    default Object [][] string_cases(){
        return new Object[][] {{"\"\""},{null},{"\"enWords\""},{"\"руСимволы\""}};
    }

    @DataProvider
    default Object [][] negativeAmount_cases(){
        return new Object[][] {{0},{-10}};
    }

    @DataProvider
    default Object [][] negativeCard_cases(){
        return new Object[][] {{"4111111111111112"},{"7111111111111112"},{"41111111111111112"},{"411111111111112"}};
    }
}
