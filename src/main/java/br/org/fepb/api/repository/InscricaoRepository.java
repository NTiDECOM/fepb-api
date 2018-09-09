package br.org.fepb.api.repository;

import br.org.fepb.api.domain.Inscricao;
import br.org.fepb.api.domain.Oficina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface InscricaoRepository extends JpaRepository<Inscricao, Long> {
    Integer countAllByOficinaAndTrabalhadorFalse(Oficina oficina);
    List<Inscricao> findAllByOficinaOrderByPessoaNome(Oficina oficina);
    List<Inscricao> findAllByOrderByPessoaNome();
    List<Inscricao> findByTrabalhadorAndFlagEmailAutorizacaoAndPessoaDataNascimentoAfter(Boolean trabalhador, Boolean flagEmailAutorizacao, Date dataNascimento);
}
