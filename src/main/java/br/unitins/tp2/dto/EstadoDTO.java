package br.unitins.tp2.dto;

public final class EstadoDTO {
    private final String nome;
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
