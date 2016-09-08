package py.com.personal.cditest.dao.voltdb;

import org.voltdb.VoltTable;
import org.voltdb.client.*;
import py.com.personal.cditest.dao.UsuarioDAO;
import py.com.personal.cditest.model.Usuario;

import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import java.io.IOException;

/**
 * Created by amadeoa on 07/09/2016.
 */
@Alternative
public class UsuarioDAOVoltDB implements UsuarioDAO {

    @Inject
    VoltClient clientFactory;

    @Override
    public Usuario getUser(String username) {
        Client client = clientFactory.getClient();
        Usuario usuario = null;

        try {
            ClientResponse response = null;
            synchronized (client){
                response = client.callProcedure("CJ_USUARIOS.select", username);
            }

            if ( response.getStatus() != ClientResponse.SUCCESS ){
                return null;
            }

            VoltTable vt = response.getResults()[0];
            vt.advanceRow();
            usuario = new Usuario(vt.getString(0), vt.getString(1));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ProcCallException e) {
            e.printStackTrace();
        }

        return usuario;
    }
}
