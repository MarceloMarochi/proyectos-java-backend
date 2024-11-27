package org.example.entities;

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
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario_generator")
    //@SequenceGenerator(name = "usuario_generator", sequenceName = "usuarios_seq", allocationSize = 1)
    @Column(name = "usuario_id")
    private Long usuarioId;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @OneToMany(mappedBy = "usuario")
    private List<Transaccion> transacciones;

    public Usuario(String linea) {
        String[] valores = linea.split(",");
        this.usuarioId = Long.parseLong(valores[0]);
        this.nombre = valores[1];
        this.email = valores[2];
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "userId=" + usuarioId+
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
