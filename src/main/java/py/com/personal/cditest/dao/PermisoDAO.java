package py.com.personal.cditest.dao;

import py.com.personal.cditest.model.Permiso;

import java.util.List;

public interface PermisoDAO {

    public List<Permiso> getUserPermissions(String username);

    public boolean hasPermission(String username, String permiso);

    public Permiso getById(String p);

}
