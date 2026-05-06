package org.example.tiendalemlah.web.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistroDTO {
    private String nombre;
    private String apellidos;
    private String email;
    private String telefono;
    private String password;
    private boolean aceptaCondiciones;
}