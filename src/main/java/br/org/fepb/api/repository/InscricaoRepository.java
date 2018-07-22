package br.org.fepb.api.repository;

import br.org.fepb.api.domain.Inscricao;
import br.org.fepb.api.domain.Oficina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InscricaoRepository extends JpaRepository<Inscricao, Long> {
    Integer countAllByOficinaAndTrabalhadorFalse(Oficina oficina);
}
