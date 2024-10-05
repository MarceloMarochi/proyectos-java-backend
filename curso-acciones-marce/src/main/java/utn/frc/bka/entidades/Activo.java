package utn.frc.bka.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "activos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Activo {
    @Id
    // Esto sería como un autoincrement
    // @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "activo_generator")
    // @SequenceGenerator(name = "activo_generator", sequenceName = "activos_seq", allocationSize = 1)
    @Column(name = "activo_id")
    private Long activo_id;

    @Column(name = "nombre_activo", nullable = false)
    private String nombreActivo;

    @Column(name = "tipo", nullable = false)
    private String tipo;

    // Relacion uno a muchos (un activo tiene muchas transacciones)
    @OneToMany(mappedBy = "activo")
    private List<Transaccion> transacciones;

    public Activo(String linea) {
        String[] valores = linea.split(",");
        this.activo_id = Long.parseLong(valores[0]);
        this.nombreActivo = valores[1];
        this.tipo = valores[2];

    }

    @Override
    public String toString() {
        return "Activo{" +
                "activo_id=" + activo_id +
                ", nombreActivo='" + nombreActivo + '\'' +
                ", tipo='" + tipo + '\'' +
                '}';
    }


}
