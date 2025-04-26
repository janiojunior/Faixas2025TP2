package br.unitins.tp2.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MunicipioDTO(
    @NotBlank(message = "O campo nome deve ser informado.")
    String nome,

    @NotNull(message = "O campo idEstado deve ser informado.")
    Long idEstado
) { }