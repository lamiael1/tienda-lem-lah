package org.example.tiendalemlah.common.entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "eventos_seguridad")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventoSeguridad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime fechaHora;

    @Column(nullable = false, length = 150)
    private String nombreUsuario;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private TipoEvento tipo;
}