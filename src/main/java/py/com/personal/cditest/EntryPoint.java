package py.com.personal.cditest;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import org.jboss.weld.environment.se.events.ContainerInitialized;
import py.com.personal.bc.common.sockets.SocketServer;
import py.com.personal.cditest.business.AuthenticationBusiness;
import py.com.personal.cditest.business.CreditTransaction;
import py.com.personal.cditest.business.DebitTransaction;
import py.com.personal.cditest.business.Transaction;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

public class EntryPoint{


    @Inject
    AuthenticationBusiness authenticationBusiness;

    @Inject
    @Any
    Instance<Transaction> transactionsInjector;

    @Inject
    SocketServer socketServer;


    public void init(@Observes ContainerInitialized containerInitialized) throws Exception{


        try{
            transactionsInjector.select(DebitTransaction.class).get().apply(10);
        }catch (Exception e){
            System.out.println(e.getMessage());
            //Deberia ocurrir esta excepcion
        }

        authenticationBusiness.login("juan", "hola");
        try {
            transactionsInjector.select(DebitTransaction.class).get().apply(10);
            transactionsInjector.select(CreditTransaction.class).get().apply(100);
        }catch (Exception e){
            //Deberia decirnos que no tenemos el monto adecuado
            System.out.println(e.getMessage());
        }

        socketServer.run();


    }

}