package br.org.fepb.api.repository;

import br.org.fepb.api.domain.Socio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SocioRepository extends JpaRepository<Socio, Long> {

    List<Socio> findAllByOrderByPessoaNome();

}
