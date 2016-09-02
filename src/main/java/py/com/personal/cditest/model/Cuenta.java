package py.com.personal.cditest.model;


public class Cuenta {

    Integer numeroCuenta;
    Integer saldo;
    private Boolean principal;

    public Cuenta(Integer numeroCuenta, Integer saldo, Boolean principal){
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldo;
        this.principal = principal;
    }

    public Integer getSaldo() {
        return saldo;
    }

    public void setSaldo(Integer saldo) {
        this.saldo = saldo;
    }

    public Integer getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(Integer numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public Boolean getPrincipal() {
        return principal;
    }

    public void setPrincipal(Boolean principal) {
        this.principal = principal;
    }
}
