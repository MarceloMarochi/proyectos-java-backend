package utnfc.isi.backend.parcial.entities;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "Cuentas")
public class Cuenta {

    @Id
    @Column(name = "cuenta _id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cuenta_id;

    @Column(name = "numero_cuenta")
    private String numero_cuenta;

    @ManyToOne
    @JoinColumn(name = "empleado_id")
    private Empleado empleado;

    @Column(name = "numero_tarjeta")
    private String numero_tarjeta;

    @Column(name = "vencimiento_tarjeta")
    private String vencimiento_tarjeta;

    @OneToOne
    @JoinColumn(name = "emprea_tarjeta_id")
    private Operador_Tarjeta operadorTarjeta;

    @OneToOne
    @JoinColumn(name = "moneda_id")
    private Moneda moneda;

    @Column(name = "saldo")
    private double saldo;


    public Cuenta() {
    }

    public Cuenta(int cuenta_id, String numero_cuenta, Empleado empleado, String numero_tarjeta, String vencimiento_tarjeta, double saldo) {
        this.cuenta_id = cuenta_id;
        this.numero_cuenta = numero_cuenta;
        this.empleado = empleado;
        this.numero_tarjeta = numero_tarjeta;
        this.vencimiento_tarjeta = vencimiento_tarjeta;
        this.saldo = saldo;
    }

    public int getCuenta_id() {
        return cuenta_id;
    }

    public void setCuenta_id(int cuenta_id) {
        this.cuenta_id = cuenta_id;
    }

    public String getNumero_cuenta() {
        return numero_cuenta;
    }

    public void setNumero_cuenta(String numero_cuenta) {
        this.numero_cuenta = numero_cuenta;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public String getNumero_tarjeta() {
        return numero_tarjeta;
    }

    public void setNumero_tarjeta(String numero_tarjeta) {
        this.numero_tarjeta = numero_tarjeta;
    }

    public String getVencimiento_tarjeta() {
        return vencimiento_tarjeta;
    }

    public void setVencimiento_tarjeta(String vencimiento_tarjeta) {
        this.vencimiento_tarjeta = vencimiento_tarjeta;
    }

    public Operador_Tarjeta getOperadorTarjeta() {
        return operadorTarjeta;
    }

    public void setOperadorTarjeta(Operador_Tarjeta operadorTarjeta) {
        this.operadorTarjeta = operadorTarjeta;
    }

    public Moneda getMoneda() {
        return moneda;
    }

    public void setMoneda(Moneda moneda) {
        this.moneda = moneda;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public double conversionSaldoUSD() {
        return saldo * moneda.getConversion_usd();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cuenta cuenta = (Cuenta) o;
        return cuenta_id == cuenta.cuenta_id && Double.compare(saldo, cuenta.saldo) == 0 && Objects.equals(numero_cuenta, cuenta.numero_cuenta) && Objects.equals(empleado, cuenta.empleado) && Objects.equals(numero_tarjeta, cuenta.numero_tarjeta) && Objects.equals(vencimiento_tarjeta, cuenta.vencimiento_tarjeta) && Objects.equals(operadorTarjeta, cuenta.operadorTarjeta) && Objects.equals(moneda, cuenta.moneda);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cuenta_id, numero_cuenta, empleado, numero_tarjeta, vencimiento_tarjeta, operadorTarjeta, moneda, saldo);
    }

    @Override
    public String toString() {
        return "Cuenta{" +
                "cuenta_id=" + cuenta_id +
                ", numero_cuenta=" + numero_cuenta +
                ", empleado=" + empleado.toString() +
                ", numero_tarjeta=" + numero_tarjeta +
                ", vencimiento_tarjeta='" + vencimiento_tarjeta + '\'' +
                ", operadorTarjeta=" + operadorTarjeta +
                ", moneda=" + moneda +
                ", saldo=" + saldo +
                '}';
    }
}
