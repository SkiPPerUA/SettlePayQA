package projectSettlePay.back.providers;

import java.util.List;

public interface IProviders {

     String getBody();

     int getCore();

     int getConn();

     void setCore(int core);

     String getResponce();

     String getId();

     static List<Pay_in> getAllPayIn(){
          return List.of(new OnePay(OnePay.OnePayBody.defaultBody(true)));
     }

     static List<Pay_out> getAllPayOut(){
          return List.of(new OnePay(OnePay.OnePayBody.defaultBody(false)),
                  new MoneyGram(MoneyGram.MoneyGramBody.defaultBody()));
     }

}
