package br.org.fepb.api.repository;

import br.org.fepb.api.domain.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {

    public Set<Departamento> findByEventos_Id(Long id);

}
