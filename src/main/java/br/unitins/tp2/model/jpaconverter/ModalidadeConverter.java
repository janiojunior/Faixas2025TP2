package br.unitins.tp2.model.jpaconverter;

import br.unitins.tp2.model.Modalidade;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class ModalidadeConverter implements AttributeConverter<Modalidade, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Modalidade prioridade) {
        return prioridade == null ? null : prioridade.getId();
    }

    @Override
    public Modalidade convertToEntityAttribute(Integer id) {
        return Modalidade.valueOf(id);
    }
    
}
