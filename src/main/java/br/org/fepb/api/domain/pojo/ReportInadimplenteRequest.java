package br.org.fepb.api.domain.pojo;

import br.org.fepb.api.enumeration.CategoriaContribuicaoEnum;
import br.org.fepb.api.enumeration.ModalidadeAssociacaoEnum;

import java.util.List;

public class ReportInadimplenteRequest {
    List<ModalidadeAssociacaoEnum> modalidadesAssociacao;
    List<CategoriaContribuicaoEnum> categoriasAssociacao;

    public List<ModalidadeAssociacaoEnum> getModalidadesAssociacao() {
        return modalidadesAssociacao;
    }

    public void setModalidadesAssociacao(List<ModalidadeAssociacaoEnum> modalidadesAssociacao) {
        this.modalidadesAssociacao = modalidadesAssociacao;
    }

    public List<CategoriaContribuicaoEnum> getCategoriasAssociacao() {
        return categoriasAssociacao;
    }

    public void setCategoriasAssociacao(List<CategoriaContribuicaoEnum> categoriasAssociacao) {
        this.categoriasAssociacao = categoriasAssociacao;
    }
}
