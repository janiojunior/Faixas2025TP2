package br.unitins.tp2.repository;

import br.unitins.tp2.model.Faixa;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class FaixaRepository implements PanacheRepository<Faixa> {

    public PanacheQuery<Faixa> findByNome(String nome) {
        if (nome == null)
            return null;
        return find("UPPER(nome) LIKE ?1 ", "%" + nome.toUpperCase() + "%");
    }

}
