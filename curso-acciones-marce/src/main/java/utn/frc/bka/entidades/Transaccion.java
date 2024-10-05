package utn.frc.bka.entidades;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaccion {
    @Id
    @Column(name = "transaccion_id")
    private Long transaccionId;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "activo_id", nullable = false)
    private Activo activo;

    @Column(name = "tipo_transaccion", nullable = false)
    private String tipoTransaccion;

    @Column(name = "cantidad", nullable = false)
    private int cantidad;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;


    // Constructor para asignar con split
    public Transaccion(String linea, Usuario usuario, Activo activo) {
        String[] valores = linea.split(",");
        this.transaccionId = Long.parseLong(valores[0]);
        this.usuario = usuario;
        this.activo = activo;
        this.tipoTransaccion = valores[3];
        this.cantidad = Integer.parseInt(valores[4]);
        this.fecha = LocalDate.parse(valores[5]);

    }


    @Override
    public String toString() {
        return "Transaccion{" +
                "transaccionId=" + transaccionId +
                ", usuario=" + usuario.toString() +
                ", activo=" + activo.toString() +
                ", tipoTransaccion='" + tipoTransaccion + '\'' +
                ", cantidad=" + cantidad +
                ", fecha=" + fecha +
                '}';
    }
}