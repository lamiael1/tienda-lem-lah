package org.example.tiendalemlah.common.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "categorias")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100, unique = true)
    private String nombre;

    @Column(length = 2000)
    private String descripcion;

    @Column(length = 500)
    private String imagen;

    @Column(length = 50)
    private String tipo;

    @ManyToMany(mappedBy = "categorias", fetch = FetchType.EAGER)
    private Set<Producto> productos = new HashSet<>();

    // equals/hashCode basado SOLO en id para evitar ConcurrentModificationException.
    // @Data de Lombok usa todos los campos incluidas las colecciones,
    // lo que provoca un bucle infinito cuando Hibernate carga las relaciones.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Categoria other)) return false;
        return id != null && id.equals(other.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Categoria{id=" + id + ", nombre='" + nombre + "', tipo='" + tipo + "'}";
    }
}