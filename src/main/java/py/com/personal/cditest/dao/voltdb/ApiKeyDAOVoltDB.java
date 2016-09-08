package py.com.personal.cditest.dao.voltdb;

import org.voltdb.VoltTable;
import org.voltdb.client.Client;
import org.voltdb.client.ClientResponse;
import org.voltdb.client.ProcCallException;
import py.com.personal.cditest.dao.ApiKeyDAO;
import py.com.personal.cditest.model.ApiKey;
import py.com.personal.cditest.model.Usuario;

import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import java.io.IOException;

/**
 * Created by amadeoa on 07/09/2016.
 */
@Alternative
public class ApiKeyDAOVoltDB implements ApiKeyDAO {

    @Inject
    VoltClient clientFactory;

    @Override
    public void insert(ApiKey apiKey) {
        Client client = clientFactory.getClient();
        Usuario usuario = null;

        try {
            ClientResponse response = null;
            synchronized (client){
                response = client.callProcedure("CJ_APIKEY.insert", apiKey.getUsuario().getUsername(), apiKey.getToken());
            }

            if ( response.getStatus() != ClientResponse.SUCCESS ){
                return;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ProcCallException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(String token) {
        Client client = clientFactory.getClient();

        try {
            ClientResponse response = null;
            synchronized (client){
                response = client.callProcedure("albRemoveToken", token);
            }

            if ( response.getStatus() != ClientResponse.SUCCESS ){
                return;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ProcCallException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ApiKey find(String token) {
        Client client = clientFactory.getClient();
        ApiKey apiKey = null;

        try {
            ClientResponse response = null;
            synchronized (client){
                response = client.callProcedure("albGetApiKey", token);
            }

            if ( response.getStatus() != ClientResponse.SUCCESS ){
                return null;
            }

            VoltTable vt = response.getResults()[0];
            vt.advanceRow();

            apiKey = new ApiKey(vt.getString(0), new Usuario(vt.getString(1), vt.getString(2)));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ProcCallException e) {
            e.printStackTrace();
        }

        return apiKey;
    }
}
