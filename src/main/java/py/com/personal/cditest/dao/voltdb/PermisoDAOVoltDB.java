package py.com.personal.cditest.dao.voltdb;

import org.voltdb.VoltTable;
import org.voltdb.client.Client;
import org.voltdb.client.ClientResponse;
import org.voltdb.client.ProcCallException;
import py.com.personal.cditest.dao.PermisoDAO;
import py.com.personal.cditest.model.Permiso;
import py.com.personal.cditest.model.Usuario;

import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import java.io.IOException;
import java.util.List;

/**
 * Created by amadeoa on 07/09/2016.
 */
@Alternative
public class PermisoDAOVoltDB implements PermisoDAO {
    @Inject
    VoltClient clientFactory;

    @Override
    public List<Permiso> getUserPermissions(String username) {
        Client client = clientFactory.getClient();
        List<Permiso> permisos = null;

        try {
            ClientResponse response = null;
            synchronized (client){
                response = client.callProcedure("albGetPermisosXUsuario", username);
            }

            if ( response.getStatus() != ClientResponse.SUCCESS ){
                return permisos;
            }

            VoltTable vt = response.getResults()[0];
            vt.resetRowPosition();

            while ( vt.advanceRow() ){
                permisos.add( new Permiso( vt.getString(0)) );
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ProcCallException e) {
            e.printStackTrace();
        }

        return permisos;
    }

    @Override
    public boolean hasPermission(String username, String permiso) {
        Client client = clientFactory.getClient();
        List<Permiso> permisos = getUserPermissions(username);

        return permisos.contains(permiso);
    }

    @Override
    public Permiso getById(String permiso) {
        Client client = clientFactory.getClient();
        List<Permiso> permisos = null;

        try {
            ClientResponse response = null;
            synchronized (client){
                response = client.callProcedure("albGetPermiso", permiso);
            }

            if ( response.getStatus() != ClientResponse.SUCCESS ){
                return null;
            }

            VoltTable vt = response.getResults()[0];
            vt.resetRowPosition();

            while ( vt.advanceRow() ){
                return new Permiso( vt.getString(0));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ProcCallException e) {
            e.printStackTrace();
        }

        return null;
    }
}
