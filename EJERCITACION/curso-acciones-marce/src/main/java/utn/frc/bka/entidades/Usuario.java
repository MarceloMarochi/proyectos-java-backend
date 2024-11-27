package utn.frc.bka.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    @Id
    // Esto sería como un autoincrement
    // @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "activo_generator")
    // @SequenceGenerator(name = "activo_generator", sequenceName = "activos_seq", allocationSize = 1)
    @Column(name = "usuario_id")
    private Long usuario_id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @OneToMany(mappedBy = "usuario")
    private List<Transaccion> transacciones;

    public Usuario(String linea) {
        String[] valores = linea.split(",");
        this.usuario_id = Long.parseLong(valores[0]);
        this.nombre = valores[1];
        this.email = valores[2];

    }


    @Override
    public String toString() {
        return "Usuario{" +
                "usuario_id=" + usuario_id +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
