package projectSettlePay.back.Providers;

import java.util.List;

public interface IProviders {

     void pay_in();

     void pay_out();

     String getBody();

     int getCore();

     int getConn();

     void setCore(int core);

     String getResponce();

     String getPayURL();

     String getId();

     static List<IProviders> getAllPayIn(){
          return List.of(new OnePay(OnePay.OnePayBody.defaultBody(true)),
          new Anymoney(Anymoney.AnymoneyBody.defaultBody(true)),
          new P2Pay(P2Pay.P2PayBody.defaultBody(true)),
          new Paycord(Paycord.PaycordBody.defaultBody(true)),
          new TwoPayler(TwoPayler.TwoPaylerBody.defaultBody(true)),
          new Xpay365(Xpay365.Xpay365Body.defaultBody(true)));
     }

}
