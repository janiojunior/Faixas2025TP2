package br.unitins.tp2.dto;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotNull;

public final class EstadoDTO {

    @NotNull(message = "O nome deve ser informado (back).")
    @Length(min = 2, max = 60, message = "O nome deve conter entre 2 e 60 caracteres.")
    private final String nome;

    @NotNull(message = "A sigla deve ser informada (back).")
    @Length(min = 2, max = 2, message = "A sigla deve ter 2 caracteres.")
    private final String sigla;

    private final long idRegiao;

    public EstadoDTO(String nome, String sigla, long idRegiao) {
        this.nome = nome;
        this.sigla = sigla;
        this.idRegiao = idRegiao;
    }

    public long getIdRegiao() {
        return idRegiao;
    }

    public String getNome() {
        return nome;
    }

    public String getSigla() {
        return sigla;
    }
}
