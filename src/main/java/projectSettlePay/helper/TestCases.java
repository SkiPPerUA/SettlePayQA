package projectSettlePay.helper;

import org.json.JSONObject;
import org.testng.annotations.DataProvider;
import java.util.*;

public interface TestCases {

    List<Long> base_amounts = List.of(-100l,0l,9999999999999l,-1l);

    @DataProvider
    default Object [][] string_cases(){
        return new Object[][] {{"\"\""},{"\"enWords\""},{"\"руСимволы\""},{null}};
    }

    @DataProvider
    default Object [][] emailNegative_cases(){return new Object[][] {{""},{"fdsd@fdsd"},{"fsdsfd.fsd"},{"fsdsfd@f.d"},{"fsd fsd@ffsd.fsd"},{"fsd!#$@#fsd@ffsd.fsd"}};}

    @DataProvider
    default Object [][] negativeCard_cases(){
        return new Object[][] {{"4111111111111112"},{"7111111111111112"},{"41111111111111112"},{"411111111111112"}};
    }

    default ArrayList amount_cases(Long[] list){
        List<Long> x = new ArrayList<>(base_amounts);
        List<Long> y = List.of(list);
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
