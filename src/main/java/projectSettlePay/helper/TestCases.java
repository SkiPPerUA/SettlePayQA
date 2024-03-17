package projectSettlePay.helper;

import org.json.JSONObject;
import org.testng.annotations.DataProvider;
import java.util.*;

public interface TestCases {

    List<Integer> base_amounts = List.of(-100,0,999999999);

    @DataProvider
    default Object [][] string_cases(){
        return new Object[][] {{"\"\""},{"\"enWords\""},{"\"руСимволы\""},{null}};
    }

    @DataProvider
    default Object [][] emailNegative_cases(){return new Object[][] {{""},{"fdsd@fdsd"},{"fsdsfd.fsd"},{"fsdsfd@f.d"},{"fsd fsd@ffsd.fsd"},{"fsd!#$@#fsd@ffsd.fsd"}};}
    @DataProvider
    default Object [][] negativeAmount_cases(){
        return new Object[][] {{0},{-10}};
    }

    @DataProvider
    default Object [][] negativeCard_cases(){
        return new Object[][] {{"4111111111111112"},{"7111111111111112"},{"41111111111111112"},{"411111111111112"}};
    }

    default ArrayList amount_cases(Integer[] list){
        List<Integer> x = new ArrayList<>(base_amounts);
        List<Integer> y = List.of(list);
        x.addAll(y);
        return (ArrayList) x;
    }

    default ArrayList without_fields_cases(Map<String, String> fields){
        List<String> result = new ArrayList<>();
        Set<String> setKeys = fields.keySet();
        for(String k: setKeys){
            Map<String,String> test = new HashMap<>(fields);
            test.remove(k);
            JSONObject jsonObject = new JSONObject(test);
            result.add(jsonObject.toString());
        }
        return (ArrayList) result;
    }
}
