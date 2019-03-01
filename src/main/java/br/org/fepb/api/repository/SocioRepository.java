package br.org.fepb.api.repository;

import br.org.fepb.api.domain.Socio;
import br.org.fepb.api.enumeration.CategoriaContribuicaoEnum;
import br.org.fepb.api.enumeration.MetodoContribuicaoEnum;
import br.org.fepb.api.enumeration.ModalidadeAssociacaoEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface SocioRepository extends JpaRepository<Socio, Long> {

    List<Socio> findAllByOrderByPessoaNome();
    List<Socio> findAllByCategoriaAssociacaoAndModalidadeAssociacaoOrderByPessoaNome(
        CategoriaContribuicaoEnum categoriaContribuicaoEnum,
        ModalidadeAssociacaoEnum modalidadeAssociacaoEnum);

    @Query(value = "SELECT DISTINCT A.* " +
            "FROM socios A " +
            "INNER JOIN pessoas B " +
            "ON A.id_pessoa = B.id " +
            "WHERE A.id NOT IN ( " +
            "SELECT id_socio " +
            "FROM historico_contribuicao " +
            "WHERE data_pagamento BETWEEN :dataInicio AND :dataFim) " +
            "AND a.categoria_associacao IN (:categorias) " +
            "AND A.modalidade_associacao IN (:modalidades) ", nativeQuery = true)
    List<Socio> buscarInadimplentes(
                                    @Param("modalidades") List<Integer> modalidades,
                                    @Param("categorias") List<Integer> categorias,
                                    @Param("dataInicio") Date dataInicio,
                                    @Param("dataFim") Date dataFim);

}
