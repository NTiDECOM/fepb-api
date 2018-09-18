package br.org.fepb.api.repository;

import br.org.fepb.api.domain.ConfiguracaoEvento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfiguracaoEventoRepository extends JpaRepository<ConfiguracaoEvento, Long> {

    ConfiguracaoEvento findByCodigo(String codigo);

}
