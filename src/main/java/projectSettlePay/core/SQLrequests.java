package projectSettlePay.core;

public abstract class SQLrequests {

    public static final String getTransaction = "SELECT x.* FROM public.transactions x WHERE id = %s";
}
