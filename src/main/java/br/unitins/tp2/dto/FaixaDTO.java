package br.unitins.tp2.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record FaixaDTO(
    @NotBlank(message = "O campo nome deve ser informado.")
    @Size(max = 60, message = "O campo nome deve possuir no máximo 60 caracteres.")
    String nome,
    String descricao,
    Integer idModalidade,
    Double preco,
    Integer estoque
) { }
