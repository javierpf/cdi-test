package py.com.personal.cditest.dao;

import py.com.personal.cditest.model.Permiso;
import py.com.personal.cditest.model.PermisoUsuario;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class PermisoDAO {

    @Inject
    Database database;

    public List<Permiso> getUserPermissions(String username){
        List<Permiso> permisos = new ArrayList<Permiso>();
        for(PermisoUsuario permisoUsuario : database.getPermisoUsuarios()){
            if(permisoUsuario.getUsuarioId().equals(username)){
                permisos.add(this.getById(permisoUsuario.getPermisoId()));
            }
        }
        return permisos;
    }

    public boolean hasPermission(String username, String permiso){
        boolean hasPermission = false;
        for(Permiso p : this.getUserPermissions(username)){
            if(p.getPermiso().equals(permiso)){
                hasPermission = true;
                break;
            }
        }
        return hasPermission;
    }

    public Permiso getById(String p){
        Permiso found = null;
        for(Permiso permiso : database.getPermisos()){
            if(permiso.getPermiso().equals(p)){
                found = permiso;
                break;
            }
        }
        return found;
    }
}