package br.unitins.tp2.dto;

import br.unitins.tp2.model.Faixa;
import br.unitins.tp2.model.Modalidade;

public record FaixaResponseDTO (
    Long id,
    String nome,
    String descricao,
    Modalidade modalidade,
    Double preco,
    Integer estoque,
    String nomeImagem
) {

    public static FaixaResponseDTO valueOf(Faixa faixa) {
        return new FaixaResponseDTO(
            faixa.getId(), 
            faixa.getNome(),
            faixa.getDescricao(),
            faixa.getModalidade(),
            faixa.getPreco(),
            faixa.getEstoque(),
            faixa.getNomeImagem()
        );
    }

}
