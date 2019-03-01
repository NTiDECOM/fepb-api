package br.org.fepb.api.repository;

import br.org.fepb.api.domain.Socio;
import br.org.fepb.api.enumeration.CategoriaContribuicaoEnum;
import br.org.fepb.api.enumeration.MetodoContribuicaoEnum;
import br.org.fepb.api.enumeration.ModalidadeAssociacaoEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SocioRepository extends JpaRepository<Socio, Long> {

    List<Socio> findAllByOrderByPessoaNome();
    List<Socio> findAllByCategoriaAssociacaoAndModalidadeAssociacaoOrderByPessoaNome(
        CategoriaContribuicaoEnum categoriaContribuicaoEnum,
        ModalidadeAssociacaoEnum modalidadeAssociacaoEnum);

}
