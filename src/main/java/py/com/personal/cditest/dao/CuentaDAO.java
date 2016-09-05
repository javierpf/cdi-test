package py.com.personal.cditest.dao;


import py.com.personal.cditest.model.Cuenta;
import py.com.personal.cditest.model.CuentaUsuario;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class CuentaDAO {

    @Inject
    Database database;


    public Cuenta getMainUserAccount(String username) {
        Cuenta found = null;
        for (Cuenta cuenta : this.getUserAccounts(username)) {
            if (cuenta.getPrincipal()) {
                found = cuenta;
            }
        }
        return found;
    }

    public List<Cuenta> getUserAccounts(String username) {
        List<Cuenta> usersAccounts = new ArrayList<Cuenta>();
        synchronized (database.getCuentaUsuarios()) {
            for (CuentaUsuario cuentaUsuario : database.getCuentaUsuarios()) {
                if (cuentaUsuario.getUsuarioId().equals(username)) {
                    usersAccounts.add(this.getAccountById(cuentaUsuario.getCuentaId()));
                }
            }
        }
        return usersAccounts;
    }

    public Cuenta getAccountById(Integer numeroCuenta) {
        Cuenta found = null;
        synchronized (database.getCuentas()) {
            for (Cuenta cuenta : database.getCuentas()) {
                if (cuenta.getNumeroCuenta().equals(numeroCuenta)) {
                    found = cuenta;
                }
            }
        }
        return found;
    }
}
