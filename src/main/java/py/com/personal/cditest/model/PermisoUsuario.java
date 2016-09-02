package py.com.personal.cditest.model;


public class PermisoUsuario {

    private String usuarioId;
    private String permisoId;

    public PermisoUsuario(String permisoId, String usuarioId){
        this.permisoId = permisoId;
        this.usuarioId = usuarioId;
    }

    public String getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(String usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getPermisoId() {
        return permisoId;
    }

    public void setPermisoId(String permisoId) {
        this.permisoId = permisoId;
    }
}
