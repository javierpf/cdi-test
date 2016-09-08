package py.com.personal.cditest.dao.voltdb;

import org.jboss.weld.environment.se.contexts.ThreadScoped;
import org.voltdb.client.Client;
import org.voltdb.client.ClientConfig;
import org.voltdb.client.ClientFactory;

import javax.enterprise.context.ApplicationScoped;

/**
 * Created by amadeoa on 07/09/2016.
 */
@ApplicationScoped
public class VoltClient {
    private Client client;

    private VoltClient(){
        this.client = null;
        ClientConfig config = null;

        try {
            config = new ClientConfig("voltdb","voltdb");
            client = ClientFactory.createClient(config);

            client.createConnection("vdcsis-voltdb1.sis.personal.net.py");
            client.createConnection("vdcsis-voltdb2.sis.personal.net.py");
            client.createConnection("vdcsis-voltdb3.sis.personal.net.py");
        } catch (java.io.IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public Client getClient(){
        return client;
    }
}
