package py.com.personal.cditest.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import py.com.personal.bc.common.sockets.handler.BasicHandler;
import py.com.personal.cditest.business.AuthenticationBusiness;
import py.com.personal.cditest.business.CreditTransaction;
import py.com.personal.cditest.business.DebitTransaction;
import py.com.personal.cditest.service.payloads.TransactionPayload;

import javax.inject.Inject;

public class TransactionService implements BasicHandler{

    //TransactionService.acreditar~~~{"monto":10,"token":"-6516174473254642639"}
    //TransactionService.debitar~~~{"monto":10,"token":"-6516174473254642639"}

    //TransactionService.acreditar~~~{"monto":10,"token":"-5344336254334989699"}
    //TransactionService.debitar~~~{"monto":10,"token":"-5344336254334989699"}
    @Inject
    CreditTransaction creditTransaction;

    @Inject
    DebitTransaction debitTransaction;

    @Inject
    AuthenticationBusiness authenticationBusiness;

    @Inject
    ObjectMapper objectMapper;

    public String acreditar(String payload) throws Exception{

        try{
            TransactionPayload transactionPayload = objectMapper.readValue(payload, TransactionPayload.class);
            authenticationBusiness.activate(transactionPayload.getToken());
            creditTransaction.apply(transactionPayload.getMonto());
            return "Acreditacion exitosa";
        }catch(Exception e){
            System.out.println(e.getMessage());
            return "error";
        }

    }

    public String debitar(String payload) throws Exception{

        try{
            TransactionPayload transactionPayload = objectMapper.readValue(payload, TransactionPayload.class);
            authenticationBusiness.activate(transactionPayload.getToken());
            debitTransaction.apply(transactionPayload.getMonto());
            return "Debito exitoso";
        }catch(Exception e){
            System.out.println(e.getMessage());
            return "error";
        }

    }

}
