package py.com.personal.cditest.dao.voltdb;

import org.voltdb.VoltTable;
import org.voltdb.VoltType;
import org.voltdb.client.Client;
import org.voltdb.client.ClientResponse;
import org.voltdb.client.ProcCallException;
import py.com.personal.cditest.dao.CuentaDAO;
import py.com.personal.cditest.model.Cuenta;
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
public class CuentaDAOVoltDB implements CuentaDAO {
    @Inject
    VoltClient clientFactory;

    @Override
    public Cuenta getMainUserAccount(String username) {
        Client client = clientFactory.getClient();
        Cuenta cuenta = null;

        try {
            ClientResponse response = null;
            synchronized (client){
                response = client.callProcedure("GetMainAccount", username);
            }

            if ( response.getStatus() != ClientResponse.SUCCESS ){
                return null;
            }

            VoltTable vt = response.getResults()[0];
            vt.resetRowPosition();

            Integer numeroCuenta = (Integer) vt.get(0, VoltType.INTEGER);
            Integer saldo = (Integer) vt.get(1, VoltType.INTEGER);
            cuenta = new Cuenta( numeroCuenta, saldo, true);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ProcCallException e) {
            e.printStackTrace();
        }

        return cuenta;
    }

    @Override
    public List<Cuenta> getUserAccounts(String username) {
        Client client = clientFactory.getClient();
        List<Cuenta> cuentas = null;

        try {
            ClientResponse response = null;
            synchronized (client){
                response = client.callProcedure("GetCuentasXUsuario", username);
            }

            if ( response.getStatus() != ClientResponse.SUCCESS ){
                return cuentas;
            }

            VoltTable vt = response.getResults()[0];
            vt.resetRowPosition();

            while ( vt.advanceRow() ){
                Integer numeroCuenta = (Integer) vt.get(0, VoltType.INTEGER);
                Integer saldo = (Integer) vt.get(1, VoltType.INTEGER);
                Integer principal = (Integer) vt.get(2, VoltType.INTEGER);

                cuentas.add( new Cuenta( numeroCuenta, saldo, principal == 1) );
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ProcCallException e) {
            e.printStackTrace();
        }

        return cuentas;
    }

    @Override
    public Cuenta getAccountById(Integer numeroCuenta) {
        Client client = clientFactory.getClient();
        Cuenta cuenta = null;

        try {
            ClientResponse response = null;
            synchronized (client){
                response = client.callProcedure("GetCuenta", numeroCuenta);
            }

            if ( response.getStatus() != ClientResponse.SUCCESS ){
                return cuenta;
            }

            VoltTable vt = response.getResults()[0];
            Integer saldo = (Integer) vt.get(1, VoltType.INTEGER);
            Integer principal = (Integer) vt.get(2, VoltType.INTEGER);

            cuenta = new Cuenta(numeroCuenta, saldo, principal == 1);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ProcCallException e) {
            e.printStackTrace();
        }

        return cuenta;
    }
}
