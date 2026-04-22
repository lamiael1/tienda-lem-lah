package org.example.tiendalemlah.common.entities;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

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
}
