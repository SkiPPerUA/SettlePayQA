package projectSettlePay.core;

public class SQLrequests {

    public static final String getTransaction = "SELECT x.* FROM public.transactions x WHERE id = %s";
}
