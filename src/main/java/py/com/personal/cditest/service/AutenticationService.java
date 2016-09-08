package py.com.personal.cditest.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import py.com.personal.bc.common.sockets.handler.BasicHandler;
import py.com.personal.cditest.business.AuthenticationBusiness;
import py.com.personal.cditest.service.payloads.LoginPayload;
import py.com.personal.cditest.service.payloads.LogoutPayload;

import javax.inject.Inject;

public class AutenticationService implements BasicHandler{

    //AutenticationService.login~~~{"username":"juan","password":"hola"}
    //AutenticationService.login~~~{"username":"jose","password":"chau"}
    @Inject
    AuthenticationBusiness authenticationBusiness;

    @Inject
    ObjectMapper objectMapper;

    public String login(String payload) throws Exception{
        try {
            LoginPayload loginPayload = objectMapper.readValue(payload, LoginPayload.class);
            return authenticationBusiness.login(loginPayload.getUsername(), loginPayload.getPassword());
        }catch (Exception e){
            System.out.println(e.getMessage());
            return "error";
        }
    }

    //AutenticationService.logout~~~{"token":"-6516174473254642639"}
    public String logout(String payload) throws Exception{
        try {
            LogoutPayload logoutPayload = objectMapper.readValue(payload, LogoutPayload.class);
            authenticationBusiness.logout(logoutPayload.getToken());
            return "Logout exitoso";
        }catch (Exception e){
            System.out.println(e.getMessage());
            return "error";
        }
    }
}


package py.com.personal.bc.falcon.test.transacciones;

        import org.voltdb.SQLStmt;
        import org.voltdb.VoltTable;
        import org.voltdb.VoltType;

        import py.com.personal.bc.falcon.db.transactions.TransactionalSelect;

public class TestTransaccionConditionalLoad extends TestTransaccionLoad {
    public final SQLStmt conditionalSelectSQL = new SQLStmt(
            "select codigo, valor "
                    + "  from test_transacciones "
                    + " where valor <= ? ");

    public VoltTable run(String claveParticion, long idTransaccion, long duracion, int valor) throws Exception {
		/*
		 * duracion > 0 = bloquear
		 */

        VoltTable results = load( idTransaccion, duracion, conditionalSelectSQL, valor );

        return results;
    }
}