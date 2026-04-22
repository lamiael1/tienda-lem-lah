package org.example.tiendalemlah.common.entities;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="productos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Producto {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, length=13)
    private String codigoEan;

    @Column(nullable=false, length=200)
    private String nombre;

    @Column(nullable=false, length=4000)
    private String descripcion;

    @Column(length=500)
    private String imagen;

    @Column(nullable=false)
    private Double precio;

    @Column(nullable=false)
    private Integer descuento;

    @ManyToOne(optional=false)
    @JoinColumn(name="marca_id")
    private Marca marca;

    @ManyToMany
    @JoinTable(
            name="producto_categoria",
            joinColumns=@JoinColumn(name="producto_id"),
            inverseJoinColumns=@JoinColumn(name="categoria_id")
    )
    private Set<Categoria> categorias = new HashSet<>();

}
