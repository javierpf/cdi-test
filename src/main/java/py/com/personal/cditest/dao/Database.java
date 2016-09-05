package py.com.personal.cditest.dao;

import py.com.personal.cditest.model.*;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import java.util.*;

@ApplicationScoped
public class Database {

    private List<Usuario> usuarios;
    private List<Permiso> permisos;
    private List<PermisoUsuario> permisoUsuarios;
    private List<Cuenta> cuentas;
    private List<CuentaUsuario> cuentaUsuarios;
    private List<ApiKey> apiKeys;

    @PostConstruct
    private void init(){

        usuarios = new ArrayList<Usuario>();
        usuarios.add(new Usuario("juan", "hola"));
        usuarios.add(new Usuario("jose", "chau"));

        permisos = new ArrayList<Permiso>();
        permisos.add(new Permiso("Permiso1"));
        permisos.add(new Permiso("Permiso2"));
        permisos.add(new Permiso("Permiso3"));
        permisos.add(new Permiso("Permiso4"));
        permisos.add(new Permiso("Permiso5"));
        permisos.add(new Permiso("Permiso6"));

        permisoUsuarios = new ArrayList<PermisoUsuario>();
        permisoUsuarios.add(new PermisoUsuario("Permiso1", "juan"));
        permisoUsuarios.add(new PermisoUsuario("Permiso2", "juan"));
        permisoUsuarios.add(new PermisoUsuario("Permiso3", "juan"));
        permisoUsuarios.add(new PermisoUsuario("Permiso4", "jose"));
        permisoUsuarios.add(new PermisoUsuario("Permiso5", "jose"));
        permisoUsuarios.add(new PermisoUsuario("Permiso6", "jose"));

        cuentas = new ArrayList<Cuenta>();
        cuentas.add(new Cuenta(1, 30, true));
        cuentas.add(new Cuenta(2, 10, true));

        cuentaUsuarios = new ArrayList<CuentaUsuario>();
        cuentaUsuarios.add(new CuentaUsuario(1, "juan"));
        cuentaUsuarios.add(new CuentaUsuario(2, "jose"));

        this.apiKeys = new ArrayList<>();

    }





    public List<Usuario> getUsuarios() {
        return Collections.synchronizedList(usuarios);
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public List<Cuenta> getCuentas() {
        return Collections.synchronizedList(cuentas);
    }

    public void setCuentas(List<Cuenta> cuentas) {
        this.cuentas = cuentas;
    }

    public List<CuentaUsuario> getCuentaUsuarios() {
        return Collections.synchronizedList(cuentaUsuarios);
    }

    public void setCuentaUsuarios(List<CuentaUsuario> cuentaUsuarios) {
        this.cuentaUsuarios = cuentaUsuarios;
    }

    public void setPermisos(List<Permiso> permisos) {
        this.permisos = permisos;
    }

    public List<PermisoUsuario> getPermisoUsuarios() {
        return Collections.synchronizedList(permisoUsuarios);
    }

    public void setPermisoUsuarios(List<PermisoUsuario> permisoUsuarios) {
        this.permisoUsuarios = permisoUsuarios;
    }

    public List<Permiso> getPermisos() {
        return Collections.synchronizedList(permisos);
    }

    public List<ApiKey> getApiKeys() {
        return Collections.synchronizedList(apiKeys);
    }

    public void setApiKeys(List<ApiKey> apiKeys) {
        this.apiKeys = apiKeys;
    }

}
