package py.com.personal.cditest.model;


public class CuentaUsuario {

    private String usuarioId;
    private Integer cuentaId;


    public CuentaUsuario(Integer cuentaId, String usuarioId){
        this.usuarioId = usuarioId;
        this.cuentaId = cuentaId;
    }

    public String getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(String usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Integer getCuentaId() {
        return cuentaId;
    }

    public void setCuentaId(Integer cuentaId) {
        this.cuentaId = cuentaId;
    }

}
