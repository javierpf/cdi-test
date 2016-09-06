package py.com.personal.cditest.dao;


import py.com.personal.cditest.model.Cuenta;

import java.util.List;

public interface CuentaDAO {

    public Cuenta getMainUserAccount(String username);

    public List<Cuenta> getUserAccounts(String username);

    public Cuenta getAccountById(Integer numeroCuenta);
}
