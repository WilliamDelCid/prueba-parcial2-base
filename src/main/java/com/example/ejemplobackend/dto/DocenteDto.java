package com.example.ejemplobackend.dto;

import jakarta.validation.constraints.*;

public record DocenteDto(
        @NotNull(message = "No puede agregar un nombre nulo")
        @NotBlank(message = "No puede agregar el nombre vacio")
        String nombre,
        @NotNull(message = "No puede agregar el numeroUnico nulo")
        @NotBlank(message = "No puede agregar el numeroUnico vacio")
        String numeroUnico,
        @NotNull(message = "No puede agregar la especialidad nulo")
        @NotBlank(message = "No puede agregar la especialidad vacia")
        String especialidad,
        @NotNull(message = "No puede agregar una institucion nulo")
        @NotBlank(message = "No puede agregar la institucion vacia")
        String institucion,
        @NotNull(message = "No puede agregar un correo nulo")
        @NotBlank(message = "No puede agregar el correo vacio")
        @Email(message = "Ingrese un tipo de correo valido")
        String correo,
        @Pattern(regexp = "\\+503 \\d{8}", message = "El formato del número de teléfono debe ser +503 seguido de 8 dígitos")
        @NotNull(message = "No numero telefonico no puede ser nulo")
        @NotBlank(message = "No puede agregar el telefono vacio")
        @Size(min = 13, max = 13, message = "El número de teléfono debe tener exactamente 13 caracteres")
        String telefono
) {
}
