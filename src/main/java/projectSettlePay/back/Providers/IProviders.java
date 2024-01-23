package projectSettlePay.back.Providers;

public interface IProviders {

     void pay_in();

     void pay_out();

     String getBody();

     int getCore();

     void setCore(int core);

     String getResponce();

     String getPayURL();

     String getId();

}
