package br.org.fepb.api.repository;

import br.org.fepb.api.domain.Cidade;
import br.org.fepb.api.domain.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long> {

    List<Cidade> findAllByEstado(Estado estado);

}
