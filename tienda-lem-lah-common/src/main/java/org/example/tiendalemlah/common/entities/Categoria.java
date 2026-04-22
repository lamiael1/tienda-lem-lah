package org.example.tiendalemlah.common.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "categorias")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, length=100, unique=true)
    private String nombre;

    @Column(length=2000)
    private String descripcion;

    @Column(length=500)
    private String imagen;

    @Column(length=50)
    private String tipo;
}
