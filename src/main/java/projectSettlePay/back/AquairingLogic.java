package projectSettlePay.back;

import projectSettlePay.helper.CurrentData;

public class AquairingLogic implements ILogicServices{
    @Override
    public String getMethod() {
        return null;
    }

    @Override
    public String getSQL(String provider_services$name) {
        return "insert into public.services(created_at,updated_at,status,type,name,slug,comment,sandbox_logic_id,production_logic_id,group_id,min_amount,max_amount,commission_group_id,commission_template_id,is_api_method_confirm_allowed,api_forms_slug,confirm_timeout,currency_required,disable_cascading,is_api_method_update_allowed,cascading_choice_method,is_api_method_pay_allowed,retry_enable,currency,require_payment_info,payer_tel,use_external_commission,applepay_merchant_identifier,googlepay_merchant_identifier,is_moto,is_p2p,method_id,allow_moto_retry,option_reject_skip_auth,check_user_trust,parent_id,children_priority_level,receipt_fields_settings,department_id,finmon_type,processing_logic_settings,applepay_merchant_id,googlepay_merchant_id,is_3ds,use_public_offer)" +
                "values ('"+ CurrentData.get()+"','"+CurrentData.get()+"',1,2,'"+provider_services$name+"','"+provider_services$name+"_slug',null,%s,%s,1,0,10000000,3,1,true,null,1800,true,false,false,null,true,false,null,false,null,false,null,null,false,false,null,false,false,false,null,null,null,null,null,null,null,null,null,false)";
    }
}
