package br.unitins.tp2.repository;

import java.util.List;

import br.unitins.tp2.model.Municipio;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MunicipioRepository implements PanacheRepository<Municipio> {

    public List<Municipio> findByNome(String nome) {
        if (nome == null)
            return null;
        return find("UPPER(nome) LIKE ?1 ", "%" + nome.toUpperCase() + "%").list();
    }

    public List<Municipio> findAll2() {
        return find("SELECT c FROM Municipio c ORDER BY c.nome ").list();
    }

}
